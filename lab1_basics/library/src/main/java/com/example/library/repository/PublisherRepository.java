package com.example.library.repository;

import com.example.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Publisher (Издатель).
 * Этот интерфейс наследует JpaRepository, предоставляя стандартные CRUD-операции для работы с издателями.
 */
@Repository // Аннотация Spring, указывающая, что этот интерфейс является компонентом доступа к данным.
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    // JpaRepository<Publisher, Long> автоматически предоставляет методы:
    // - save() — сохранение издателя
    // - findById() — поиск издателя по ID
    // - findAll() — получение списка всех издателей
    // - deleteById() — удаление издателя по ID
}