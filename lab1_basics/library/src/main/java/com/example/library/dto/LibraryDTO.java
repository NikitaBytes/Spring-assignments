package com.example.library.dto;

import java.util.List;

/**
 * DTO (Data Transfer Object) для сущности Library.
 * Используется для передачи данных о библиотеке через REST API.
 *
 * Поля:
 * - id: уникальный идентификатор библиотеки, генерируется базой данных.
 * - name: название библиотеки.
 * - bookIds: список идентификаторов книг, которые находятся в библиотеке.
 */
public class LibraryDTO {
    private Long id;  // Уникальный идентификатор библиотеки
    private String name;  // Название библиотеки
    private List<Long> bookIds;  // Список идентификаторов книг в библиотеке

    /**
     * Конструктор по умолчанию, необходимый для десериализации JSON в объект LibraryDTO.
     */
    public LibraryDTO() {}

    /**
     * Конструктор с параметрами для создания объекта LibraryDTO.
     *
     * @param id      уникальный идентификатор библиотеки
     * @param name    название библиотеки
     * @param bookIds список идентификаторов книг, находящихся в библиотеке
     */
    public LibraryDTO(Long id, String name, List<Long> bookIds) {
        this.id = id;
        this.name = name;
        this.bookIds = bookIds;
    }

    // Геттеры и сеттеры для доступа и изменения полей

    /**
     * Возвращает идентификатор библиотеки.
     *
     * @return id библиотеки
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор библиотеки.
     *
     * @param id уникальный идентификатор библиотеки
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название библиотеки.
     *
     * @return название библиотеки
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название библиотеки.
     *
     * @param name название библиотеки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает список идентификаторов книг, которые находятся в библиотеке.
     *
     * @return список идентификаторов книг
     */
    public List<Long> getBookIds() {
        return bookIds;
    }

    /**
     * Устанавливает список идентификаторов книг, находящихся в библиотеке.
     *
     * @param bookIds список идентификаторов книг
     */
    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}