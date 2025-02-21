package com.example.library.dto;

/**
 * DTO (Data Transfer Object) для сущности Category.
 * Используется для передачи данных о категории книг через REST API.
 *
 * Поля:
 * - id: уникальный идентификатор категории, генерируется базой данных.
 * - name: название категории.
 */
public class CategoryDTO {
    private Long id;  // Уникальный идентификатор категории
    private String name;  // Название категории

    /**
     * Конструктор по умолчанию, необходимый для десериализации JSON в объект CategoryDTO.
     */
    public CategoryDTO() {}

    /**
     * Конструктор с параметрами для создания объекта CategoryDTO.
     *
     * @param id   уникальный идентификатор категории
     * @param name название категории
     */
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры для доступа и изменения полей

    /**
     * Возвращает идентификатор категории.
     *
     * @return id категории
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор категории.
     *
     * @param id уникальный идентификатор категории
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название категории.
     *
     * @return название категории
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название категории.
     *
     * @param name название категории
     */
    public void setName(String name) {
        this.name = name;
    }
}