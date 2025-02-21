package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.entity.Publisher;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import com.example.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service // Помечаем этот класс как сервис (бизнес-логика)
public class BookService {

    @Autowired
    private BookRepository bookRepository; // Репозиторий для работы с книгами
    
    @Autowired
    private AuthorRepository authorRepository; // Репозиторий для работы с авторами
    
    @Autowired
    private PublisherRepository publisherRepository; // Репозиторий для работы с издателями
    
    @Autowired
    private CategoryRepository categoryRepository; // Репозиторий для работы с категориями

    /**
     * Создает книгу, проверяя, что authorId и publisherId не равны null.
     * Также проверяет, существуют ли связанные сущности.
     *
     * @param bookDTO данные книги из запроса
     * @return сохраненная книга в виде BookDTO
     * @throws IllegalArgumentException если authorId или publisherId равны null,
     *         или если соответствующая сущность не найдена.
     */
    public BookDTO createBook(BookDTO bookDTO) {
        // Проверяем, переданы ли ID автора и издателя
        if (bookDTO.getAuthorId() == null) {
            throw new IllegalArgumentException("Author ID must not be null");
        }
        if (bookDTO.getPublisherId() == null) {
            throw new IllegalArgumentException("Publisher ID must not be null");
        }

        // Находим автора по ID
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + bookDTO.getAuthorId()));

        // Находим издателя по ID
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found with id: " + bookDTO.getPublisherId()));

        // Создаём новый объект книги
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setPublisher(publisher);

        // Устанавливаем категории книги (если есть)
        Set<Category> categories = new HashSet<>();
        if (bookDTO.getCategoryIds() != null) {
            for (Long catId : bookDTO.getCategoryIds()) {
                Category category = categoryRepository.findById(catId)
                        .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + catId));
                categories.add(category);
            }
        }
        book.setCategories(categories);

        // Сохраняем книгу в базе данных
        Book saved = bookRepository.save(book);
        
        // Преобразуем в DTO и возвращаем
        return convertToDTO(saved);
    }

    /**
     * Получает книгу по ID.
     *
     * @param id идентификатор книги
     * @return объект BookDTO, если книга найдена
     */
    public BookDTO getBook(Long id) {
        return bookRepository.findById(id)
                .map(this::convertToDTO) // Преобразуем в DTO
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id)); // Если книга не найдена, выбрасываем ошибку
    }

    /**
     * Получает список всех книг.
     *
     * @return список объектов BookDTO
     */
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO) // Преобразуем каждую найденную книгу в DTO
                .collect(Collectors.toList());
    }

    /**
     * Обновляет существующую книгу.
     *
     * @param id      идентификатор книги
     * @param bookDTO данные для обновления
     * @return обновленный объект BookDTO
     * @throws IllegalArgumentException если книга, автор, издатель или категория не найдены
     */
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        return bookRepository.findById(id).map(book -> {
            // Обновляем название книги
            book.setTitle(bookDTO.getTitle());

            // Обновляем автора
            Author author = authorRepository.findById(bookDTO.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + bookDTO.getAuthorId()));
            book.setAuthor(author);

            // Обновляем издателя
            Publisher publisher = publisherRepository.findById(bookDTO.getPublisherId())
                    .orElseThrow(() -> new IllegalArgumentException("Publisher not found with id: " + bookDTO.getPublisherId()));
            book.setPublisher(publisher);

            // Обновляем категории
            Set<Category> categories = new HashSet<>();
            if (bookDTO.getCategoryIds() != null) {
                for (Long catId : bookDTO.getCategoryIds()) {
                    Category category = categoryRepository.findById(catId)
                            .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + catId));
                    categories.add(category);
                }
            }
            book.setCategories(categories);

            // Сохраняем обновлённую книгу
            Book updated = bookRepository.save(book);

            // Преобразуем в DTO и возвращаем
            return convertToDTO(updated);
        }).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id)); // Если книги нет, выбрасываем ошибку
    }

    /**
     * Удаляет книгу по ID.
     *
     * @param id идентификатор книги
     * @throws IllegalArgumentException если книга не найдена
     */
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    /**
     * Преобразует сущность Book в DTO.
     *
     * @param book объект сущности Book
     * @return объект BookDTO
     */
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
        dto.setPublisherId(book.getPublisher() != null ? book.getPublisher().getId() : null);
        
        // Преобразуем Set<Category> в Set<Long> (список ID категорий)
        if (book.getCategories() != null) {
            Set<Long> catIds = book.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            dto.setCategoryIds(catIds);
        }

        return dto;
    }
}