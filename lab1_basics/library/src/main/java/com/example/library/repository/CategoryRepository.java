package com.example.library.repository;

import com.example.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Category (Категория).
 * Этот интерфейс расширяет JpaRepository, предоставляя базовые CRUD-операции для работы с категориями.
 */
@Repository // Аннотация Spring, указывающая, что этот интерфейс является компонентом доступа к данным.
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // JpaRepository<Category, Long> предоставляет стандартные методы:
    // - save() — сохранение категории
    // - findById() — поиск категории по ID
    // - findAll() — получение списка всех категорий
    // - deleteById() — удаление категории по ID
}