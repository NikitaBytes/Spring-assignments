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

/**
 * Интеграционные тесты для Spring Boot приложения "Library".
 * Тесты проверяют CRUD-операции для справочных сущностей (Author, Publisher, Category),
 * а также для книг и библиотек.
 *
 * Тесты выполняются в определённом порядке с помощью аннотации @TestMethodOrder(OrderAnnotation.class),
 * чтобы сначала создать справочные данные, затем книгу и библиотеку, а затем проверить операции обновления, получения и удаления.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class LibraryApplicationTests {

    // Используем TestRestTemplate для отправки HTTP-запросов к REST API приложения
    @Autowired
    private TestRestTemplate restTemplate;

    // Статические переменные для сохранения созданных ID,
    // которые используются в последующих тестах для проверки корректности работы CRUD-операций.
    private static Long createdAuthorId;
    private static Long createdPublisherId;
    private static Long createdCategoryId1;
    private static Long createdCategoryId2;
    private static Long createdBookId;
    private static Long createdLibraryId;

    /**
     * Тест создания автора.
     * Отправляет POST-запрос на /api/reference/authors с данными автора.
     * Проверяет, что ответ имеет статус OK и возвращает объект AuthorDTO с установленным ID.
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
     * Тест создания издателя.
     * Отправляет POST-запрос на /api/reference/publishers с данными издателя.
     * Проверяет, что ответ имеет статус OK и возвращает объект PublisherDTO с установленным ID.
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
     * Тест создания категорий.
     * Создаются две категории: "Фантастика" и "Приключения".
     * Проверяется статус ответа и устанавливаются ID для последующего использования.
     */
    @Test
    @Order(3)
    public void testCreateCategories() {
        // Создаем первую категорию
        CategoryDTO cat1 = new CategoryDTO();
        cat1.setName("Фантастика");
        ResponseEntity<CategoryDTO> response1 = restTemplate.postForEntity(
                "/api/reference/categories", cat1, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode(), "Status code for category creation (cat1) is not OK");
        CategoryDTO createdCat1 = response1.getBody();
        Assertions.assertNotNull(createdCat1, "Response body is null for category creation (cat1)");
        Assertions.assertNotNull(createdCat1.getId(), "Created category1 ID is null");
        createdCategoryId1 = createdCat1.getId();

        // Создаем вторую категорию
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
     * Тест создания книги.
     * Перед созданием книги проверяются, что справочные данные (автор, издатель, категории) созданы.
     * Отправляется POST-запрос на /api/books с передачей всех необходимых ID.
     * Проверяется, что созданная книга имеет корректные связи.
     */
    @Test
    @Order(4)
    public void testCreateBook() {
        // Проверяем, что справочные данные созданы
        Assertions.assertNotNull(createdAuthorId, "Created author ID is null");
        Assertions.assertNotNull(createdPublisherId, "Created publisher ID is null");
        Assertions.assertNotNull(createdCategoryId1, "Created category1 ID is null");
        Assertions.assertNotNull(createdCategoryId2, "Created category2 ID is null");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Новая книга");
        bookDTO.setAuthorId(createdAuthorId);
        bookDTO.setPublisherId(createdPublisherId);
        // Передаём идентификаторы категорий в виде Set, чтобы избежать дублирования
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
        Assertions.assertTrue(createdBook.getCategoryIds().contains(createdCategoryId1),
                "Created book does not contain category1");
        Assertions.assertTrue(createdBook.getCategoryIds().contains(createdCategoryId2),
                "Created book does not contain category2");

        createdBookId = createdBook.getId();
    }

    /**
     * Тест получения книги по ID.
     * Отправляет GET-запрос на /api/books/{id} и проверяет, что полученные данные соответствуют ожиданиям.
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
     * Тест обновления книги.
     * Отправляет PUT-запрос на /api/books/{id} для обновления данных книги,
     * изменяя название и набор категорий.
     * Проверяет, что данные обновились корректно.
     */
    @Test
    @Order(6)
    public void testUpdateBook() {
        BookDTO updatedData = new BookDTO();
        updatedData.setTitle("Обновленная книга");
        updatedData.setAuthorId(createdAuthorId);
        updatedData.setPublisherId(createdPublisherId);
        // Меняем набор категорий: оставляем только первую категорию
        updatedData.setCategoryIds(new HashSet<>(Arrays.asList(createdCategoryId1)));

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
     * Тест удаления книги.
     * Отправляет DELETE-запрос на /api/books/{id} и затем пытается получить книгу,
     * ожидая, что она не найдется.
     */
    @Test
    @Order(7)
    public void testDeleteBook() {
        restTemplate.delete("/api/books/" + createdBookId);
        ResponseEntity<BookDTO> response = restTemplate.getForEntity(
                "/api/books/" + createdBookId, BookDTO.class);
        // Ожидаем, что после удаления книга не найдена и возвращается статус 404
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Book still exists after deletion");
    }

    /**
     * Тест создания библиотеки.
     * Отправляет POST-запрос на /api/libraries с именем библиотеки и списком идентификаторов книг.
     * Проверяет, что библиотека успешно создается и возвращает корректные данные.
     */
    @Test
    @Order(8)
    public void testCreateLibrary() {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName("Моя библиотека");
        // Если книга была удалена, передаем пустой список, иначе передаем созданный идентификатор книги
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
     * Тест получения списка библиотек.
     * Отправляет GET-запрос на /api/libraries и проверяет, что возвращается непустой список.
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
     * Тест получения библиотеки по ID.
     * Отправляет GET-запрос на /api/libraries/{id} и проверяет, что возвращаемый объект соответствует ожидаемому.
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