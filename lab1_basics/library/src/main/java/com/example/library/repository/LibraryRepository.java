package com.example.library.repository;

import com.example.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Library (Библиотека).
 * Этот интерфейс расширяет JpaRepository, предоставляя стандартные CRUD-операции для работы с библиотеками.
 */
@Repository // Аннотация Spring, указывающая, что этот интерфейс является компонентом доступа к данным.
public interface LibraryRepository extends JpaRepository<Library, Long> {
    // JpaRepository<Library, Long> автоматически предоставляет методы:
    // - save() — сохранение библиотеки
    // - findById() — поиск библиотеки по ID
    // - findAll() — получение списка всех библиотек
    // - deleteById() — удаление библиотеки по ID
}