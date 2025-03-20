package com.example.library.dto;

import java.util.Set;

/**
 * DTO для сущности Book.
 * Используется для передачи данных о книге через REST API,
 * без раскрытия внутренней структуры JPA-сущности Book.
 *
 * Поля:
 * - id: уникальный идентификатор книги, генерируется базой данных.
 * - title: название книги.
 * - authorId: идентификатор автора, связанного с книгой (связь Many-to-One).
 * - publisherId: идентификатор издателя, связанного с книгой (связь Many-to-One).
 * - categoryIds: множество идентификаторов категорий, к которым относится книга (связь Many-to-Many).
 */
public class BookDTO {
    private Long id;
    private String title;
    // Передаём идентификаторы связанных сущностей вместо самих сущностей
    private Long authorId;
    private Long publisherId;
    private Set<Long> categoryIds;

    /**
     * Конструктор по умолчанию, необходимый для десериализации JSON в объект BookDTO.
     */
    public BookDTO() {}

    /**
     * Конструктор с параметрами для создания объекта BookDTO.
     *
     * @param id           уникальный идентификатор книги
     * @param title        название книги
     * @param authorId     идентификатор автора
     * @param publisherId  идентификатор издателя
     * @param categoryIds  множество идентификаторов категорий
     */
    public BookDTO(Long id, String title, Long authorId, Long publisherId, Set<Long> categoryIds) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryIds = categoryIds;
    }

    // Геттеры и сеттеры для доступа и изменения полей

    /**
     * Возвращает идентификатор книги.
     *
     * @return id книги
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор книги.
     *
     * @param id уникальный идентификатор книги
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название книги.
     *
     * @return название книги
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название книги.
     *
     * @param title название книги
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает идентификатор автора.
     *
     * @return authorId
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * Устанавливает идентификатор автора.
     *
     * @param authorId идентификатор автора
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * Возвращает идентификатор издателя.
     *
     * @return publisherId
     */
    public Long getPublisherId() {
        return publisherId;
    }

    /**
     * Устанавливает идентификатор издателя.
     *
     * @param publisherId идентификатор издателя
     */
    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * Возвращает множество идентификаторов категорий.
     *
     * @return множество categoryIds
     */
    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    /**
     * Устанавливает множество идентификаторов категорий.
     *
     * @param categoryIds множество categoryIds
     */
    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}