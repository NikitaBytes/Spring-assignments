package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.dao.AuthorDao;
import com.example.library.dao.PublisherDao;
import com.example.library.dao.CategoryDao;
import com.example.library.dto.BookDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Author;
import com.example.library.entity.Publisher;
import com.example.library.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private CategoryDao categoryDao;

    /**
     * Создание книги.
     */
    public BookDTO createBook(BookDTO bookDTO) {
        if (bookDTO.getAuthorId() == null) {
            throw new IllegalArgumentException("Author ID must not be null");
        }
        if (bookDTO.getPublisherId() == null) {
            throw new IllegalArgumentException("Publisher ID must not be null");
        }

        Author author = authorDao.findById(bookDTO.getAuthorId());
        if (author == null) {
            throw new IllegalArgumentException("Author not found with id: " + bookDTO.getAuthorId());
        }

        Publisher publisher = publisherDao.findById(bookDTO.getPublisherId());
        if (publisher == null) {
            throw new IllegalArgumentException("Publisher not found with id: " + bookDTO.getPublisherId());
        }

        // Собираем сущность Book
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setPublisher(publisher);

        // Загружаем все категории
        Set<Category> categories = new HashSet<>();
        if (bookDTO.getCategoryIds() != null) {
            for (Long catId : bookDTO.getCategoryIds()) {
                Category category = categoryDao.findById(catId);
                if (category == null) {
                    throw new IllegalArgumentException("Category not found with id: " + catId);
                }
                categories.add(category);
            }
        }
        book.setCategories(categories);

        // ВАЖНО: пересохраняем результат, чтобы в book был реальный ID
        book = bookDao.save(book);

        return convertToDTO(book);
    }

    /**
     * Получение книги по ID.
     */
    public BookDTO getBook(Long id) {
        Book book = bookDao.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        return convertToDTO(book);
    }

    /**
     * Получение списка всех книг.
     */
    public List<BookDTO> getAllBooks() {
        return bookDao.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Обновление книги.
     */
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookDao.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }

        book.setTitle(bookDTO.getTitle());

        Author author = authorDao.findById(bookDTO.getAuthorId());
        if (author == null) {
            throw new IllegalArgumentException("Author not found with id: " + bookDTO.getAuthorId());
        }
        book.setAuthor(author);

        Publisher publisher = publisherDao.findById(bookDTO.getPublisherId());
        if (publisher == null) {
            throw new IllegalArgumentException("Publisher not found with id: " + bookDTO.getPublisherId());
        }
        book.setPublisher(publisher);

        Set<Category> categories = new HashSet<>();
        if (bookDTO.getCategoryIds() != null) {
            for (Long catId : bookDTO.getCategoryIds()) {
                Category category = categoryDao.findById(catId);
                if (category == null) {
                    throw new IllegalArgumentException("Category not found with id: " + catId);
                }
                categories.add(category);
            }
        }
        book.setCategories(categories);

        // Снова сохраняем и присваиваем
        book = bookDao.save(book);

        return convertToDTO(book);
    }

    /**
     * Удаление книги по ID.
     */
    public void deleteBook(Long id) {
        Book book = bookDao.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        bookDao.delete(book);
    }

    /**
     * Преобразование Book -> BookDTO.
     */
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
        dto.setPublisherId(book.getPublisher() != null ? book.getPublisher().getId() : null);
        if (book.getCategories() != null) {
            Set<Long> catIds = book.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            dto.setCategoryIds(catIds);
        }
        return dto;
    }
}