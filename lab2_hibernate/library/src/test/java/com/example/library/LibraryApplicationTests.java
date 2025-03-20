package com.example.library;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.BookDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.dto.LibraryDTO;
import com.example.library.dto.PublisherDTO;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class LibraryApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    // Статические переменные для хранения созданных идентификаторов
    private static Long createdAuthorId;
    private static Long createdPublisherId;
    private static Long createdCategoryId1;
    private static Long createdCategoryId2;
    private static Long createdBookId;
    private static Long createdLibraryId;

    /**
     * 1. Тест создания автора.
     * Отправляет POST-запрос на /api/reference/authors и сохраняет ID созданного автора.
     */
    @Test
    @Order(1)
    public void testCreateAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Иванов");

        ResponseEntity<AuthorDTO> response = restTemplate.postForEntity(
                "/api/reference/authors", authorDTO, AuthorDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK for author creation");
        AuthorDTO created = response.getBody();
        Assertions.assertNotNull(created, "Response body is null for author creation");
        Assertions.assertNotNull(created.getId(), "Created author ID is null");
        createdAuthorId = created.getId();
    }

    /**
     * 2. Тест создания издателя.
     * Отправляет POST-запрос на /api/reference/publishers и сохраняет ID созданного издателя.
     */
    @Test
    @Order(2)
    public void testCreatePublisher() {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setName("Издательство 1");

        ResponseEntity<PublisherDTO> response = restTemplate.postForEntity(
                "/api/reference/publishers", publisherDTO, PublisherDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK for publisher creation");
        PublisherDTO created = response.getBody();
        Assertions.assertNotNull(created, "Response body is null for publisher creation");
        Assertions.assertNotNull(created.getId(), "Created publisher ID is null");
        createdPublisherId = created.getId();
    }

    /**
     * 3. Тест создания категорий.
     * Создает две категории и сохраняет их ID для дальнейших тестов.
     */
    @Test
    @Order(3)
    public void testCreateCategories() {
        // Первая категория
        CategoryDTO cat1 = new CategoryDTO();
        cat1.setName("Фантастика");
        ResponseEntity<CategoryDTO> response1 = restTemplate.postForEntity(
                "/api/reference/categories", cat1, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode(), "Status code for category creation (cat1) is not OK");
        CategoryDTO createdCat1 = response1.getBody();
        Assertions.assertNotNull(createdCat1, "Response body is null for category creation (cat1)");
        Assertions.assertNotNull(createdCat1.getId(), "Created category1 ID is null");
        createdCategoryId1 = createdCat1.getId();

        // Вторая категория
        CategoryDTO cat2 = new CategoryDTO();
        cat2.setName("Приключения");
        ResponseEntity<CategoryDTO> response2 = restTemplate.postForEntity(
                "/api/reference/categories", cat2, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode(), "Status code for category creation (cat2) is not OK");
        CategoryDTO createdCat2 = response2.getBody();
        Assertions.assertNotNull(createdCat2, "Response body is null for category creation (cat2)");
        Assertions.assertNotNull(createdCat2.getId(), "Created category2 ID is null");
        createdCategoryId2 = createdCat2.getId();
    }

    /**
     * 4. Тест создания книги.
     * Проверяет, что книга создается с корректными связями с автором, издателем и категориями.
     */
    @Test
    @Order(4)
    public void testCreateBook() {
        // Убедимся, что справочные данные созданы
        Assertions.assertNotNull(createdAuthorId, "Created author ID is null");
        Assertions.assertNotNull(createdPublisherId, "Created publisher ID is null");
        Assertions.assertNotNull(createdCategoryId1, "Created category1 ID is null");
        Assertions.assertNotNull(createdCategoryId2, "Created category2 ID is null");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Новая книга");
        bookDTO.setAuthorId(createdAuthorId);
        bookDTO.setPublisherId(createdPublisherId);
        // Передаем идентификаторы категорий в виде множества
        bookDTO.setCategoryIds(new HashSet<>(Arrays.asList(createdCategoryId1, createdCategoryId2)));

        ResponseEntity<BookDTO> response = restTemplate.postForEntity(
                "/api/books", bookDTO, BookDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK when creating book");
        BookDTO createdBook = response.getBody();
        Assertions.assertNotNull(createdBook, "Response body is null for book creation");
        Assertions.assertNotNull(createdBook.getId(), "Created book ID is null");
        Assertions.assertEquals(createdAuthorId, createdBook.getAuthorId(), "Author ID mismatch in created book");
        Assertions.assertEquals(createdPublisherId, createdBook.getPublisherId(), "Publisher ID mismatch in created book");
        Assertions.assertNotNull(createdBook.getCategoryIds(), "Category IDs are null in created book");
        Assertions.assertTrue(createdBook.getCategoryIds().contains(createdCategoryId1), "Created book does not contain category1");
        Assertions.assertTrue(createdBook.getCategoryIds().contains(createdCategoryId2), "Created book does not contain category2");

        createdBookId = createdBook.getId();
    }

    /**
     * 5. Тест получения книги по ID.
     * Проверяет, что по заданному ID возвращается книга с корректными данными.
     */
    @Test
    @Order(5)
    public void testGetBook() {
        ResponseEntity<BookDTO> response = restTemplate.getForEntity(
                "/api/books/" + createdBookId, BookDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK when getting book");
        BookDTO book = response.getBody();
        Assertions.assertNotNull(book, "Book is null when retrieved");
        Assertions.assertEquals(createdBookId, book.getId(), "Book ID mismatch on retrieval");
    }

    /**
     * 6. Тест обновления книги.
     * Изменяет название и набор категорий книги, проверяет корректность обновления.
     */
    @Test
    @Order(6)
    public void testUpdateBook() {
        BookDTO updatedData = new BookDTO();
        updatedData.setTitle("Обновленная книга");
        updatedData.setAuthorId(createdAuthorId);
        updatedData.setPublisherId(createdPublisherId);
        // Оставляем только первую категорию
        updatedData.setCategoryIds(new HashSet<>(Collections.singletonList(createdCategoryId1)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookDTO> entity = new HttpEntity<>(updatedData, headers);

        ResponseEntity<BookDTO> response = restTemplate.exchange(
                "/api/books/" + createdBookId, HttpMethod.PUT, entity, BookDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK when updating book");
        BookDTO updatedBook = response.getBody();
        Assertions.assertNotNull(updatedBook, "Updated book is null");
        Assertions.assertEquals("Обновленная книга", updatedBook.getTitle(), "Book title did not update");
        Assertions.assertNotNull(updatedBook.getCategoryIds(), "Updated category IDs are null");
        Assertions.assertEquals(1, updatedBook.getCategoryIds().size(), "Unexpected number of categories after update");
        Assertions.assertTrue(updatedBook.getCategoryIds().contains(createdCategoryId1), "Category1 not found in updated book");
    }

    /**
     * 7. Тест удаления книги.
     * Удаляет книгу по ID и затем проверяет, что книга больше не доступна (404 NOT_FOUND).
     */
    @Test
    @Order(7)
    public void testDeleteBook() {
        restTemplate.delete("/api/books/" + createdBookId);
        ResponseEntity<BookDTO> response = restTemplate.getForEntity(
                "/api/books/" + createdBookId, BookDTO.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Book still exists after deletion");
    }

    /**
     * 8. Тест создания библиотеки.
     * Создает библиотеку с указанным именем и списком идентификаторов книг.
     */
    @Test
    @Order(8)
    public void testCreateLibrary() {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName("Моя библиотека");
        // Если книга была удалена, можно передать пустой список
        List<Long> bookIds = (createdBookId != null) ? Arrays.asList(createdBookId) : Collections.emptyList();
        libraryDTO.setBookIds(bookIds);

        ResponseEntity<LibraryDTO> response = restTemplate.postForEntity(
                "/api/libraries", libraryDTO, LibraryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK when creating library");
        LibraryDTO createdLibrary = response.getBody();
        Assertions.assertNotNull(createdLibrary, "Library response is null");
        Assertions.assertNotNull(createdLibrary.getId(), "Library ID is null");
        createdLibraryId = createdLibrary.getId();
    }

    /**
     * 9. Тест получения списка библиотек.
     * Проверяет, что GET /api/libraries возвращает непустой массив библиотек.
     */
    @Test
    @Order(9)
    public void testGetLibraries() {
        ResponseEntity<LibraryDTO[]> response = restTemplate.getForEntity(
                "/api/libraries", LibraryDTO[].class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK when retrieving libraries");
        LibraryDTO[] libraries = response.getBody();
        Assertions.assertNotNull(libraries, "Libraries array is null");
        Assertions.assertTrue(libraries.length > 0, "No libraries found");
    }

    /**
     * 10. Тест получения библиотеки по ID.
     * Получает библиотеку по её ID и проверяет, что данные корректны.
     */
    @Test
    @Order(10)
    public void testGetLibraryById() {
        Assertions.assertNotNull(createdLibraryId, "Library ID is null");
        ResponseEntity<LibraryDTO> response = restTemplate.getForEntity(
                "/api/libraries/" + createdLibraryId, LibraryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not OK when retrieving library by ID");
        LibraryDTO library = response.getBody();
        Assertions.assertNotNull(library, "Library retrieved by ID is null");
        Assertions.assertEquals(createdLibraryId, library.getId(), "Library ID mismatch");
    }
}