package com.example.library.dto;

/**
 * DTO (Data Transfer Object) для сущности Publisher (Издатель).
 * Используется для передачи данных о издателе через REST API.
 *
 * Поля:
 * - id: уникальный идентификатор издателя, генерируется базой данных.
 * - name: название издательства.
 */
public class PublisherDTO {
    private Long id;  // Уникальный идентификатор издателя
    private String name;  // Название издательства

    /**
     * Конструктор по умолчанию, необходимый для десериализации JSON в объект PublisherDTO.
     */
    public PublisherDTO() {}

    /**
     * Конструктор с параметрами для создания объекта PublisherDTO.
     *
     * @param id   уникальный идентификатор издателя
     * @param name название издательства
     */
    public PublisherDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры для доступа и изменения полей

    /**
     * Возвращает идентификатор издателя.
     *
     * @return id издателя
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор издателя.
     *
     * @param id уникальный идентификатор издателя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название издательства.
     *
     * @return название издательства
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название издательства.
     *
     * @param name название издательства
     */
    public void setName(String name) {
        this.name = name;
    }
}