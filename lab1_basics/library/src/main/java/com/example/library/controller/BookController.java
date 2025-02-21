package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Endpoint для создания книги.
     * Принимает объект BookDTO, содержащий название, authorId, publisherId и список categoryIds.
     */
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    /**
     * Endpoint для получения книги по ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        try {
            // Пытаемся получить книгу по ID через сервис.
            BookDTO bookDTO = bookService.getBook(id);
            // Если книга не найдена (метод вернул null), возвращаем 404.
            if (bookDTO == null) {
                return ResponseEntity.notFound().build();
            }
            // Если книга найдена, возвращаем её с HTTP-статусом 200.
            return ResponseEntity.ok(bookDTO);
        } catch (Exception ex) {
            // В случае возникновения исключения (например, из-за проблем с загрузкой лениво-загруженных ассоциаций),
            // возвращаем 404 Not Found вместо 500 Internal Server Error.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint для получения списка всех книг.
     */
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Endpoint для обновления книги по ID.
     */
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    /**
     * Endpoint для удаления книги по ID.
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}