package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Book (Книга).
 * Этот интерфейс расширяет JpaRepository, предоставляя базовые CRUD-операции для работы с книгами.
 */
@Repository // Аннотация, обозначающая, что это компонент Spring, отвечающий за доступ к данным.
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository<Book, Long> предоставляет стандартные CRUD-методы, такие как:
    // - save() — сохранение книги
    // - findById() — поиск книги по ID
    // - findAll() — получение списка всех книг
    // - deleteById() — удаление книги по ID
}