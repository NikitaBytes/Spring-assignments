package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для сущности Author (Автор).
 * Используется Spring Data JPA для упрощения работы с базой данных.
 */
@Repository  // Аннотация указывает, что этот интерфейс является компонентом Spring и должен быть управляемым бином.
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // JpaRepository<Author, Long> предоставляет базовые CRUD-операции:
    // - save() - сохранение сущности
    // - findById() - поиск по ID
    // - findAll() - получение всех авторов
    // - deleteById() - удаление по ID
}