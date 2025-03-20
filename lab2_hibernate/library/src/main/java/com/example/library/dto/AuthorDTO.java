package com.example.library.dto;

/**
 * DTO для сущности Author.
 * Этот класс используется для передачи данных об авторе через REST API,
 * не раскрывая внутреннюю сущность Author, что позволяет изолировать
 * бизнес-логику от представления данных.
 *
 * Поля:
 * - id: уникальный идентификатор автора (генерируется базой данных)
 * - name: имя автора
 *
 * Дополнительно можно добавить список идентификаторов книг, написанных автором,
 * если потребуется передавать эту информацию.
 */
public class AuthorDTO {

    // Уникальный идентификатор автора
    private Long id;
    
    // Имя автора
    private String name;

    /**
     * Конструктор по умолчанию.
     * Необходим для десериализации JSON в объект AuthorDTO.
     */
    public AuthorDTO() {}

    /**
     * Конструктор с параметрами для создания объекта AuthorDTO.
     *
     * @param id   идентификатор автора
     * @param name имя автора
     */
    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры для доступа и модификации полей

    /**
     * Возвращает идентификатор автора.
     *
     * @return id автора
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор автора.
     *
     * @param id уникальный идентификатор автора
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает имя автора.
     *
     * @return имя автора
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя автора.
     *
     * @param name имя автора
     */
    public void setName(String name) {
        this.name = name;
    }
}