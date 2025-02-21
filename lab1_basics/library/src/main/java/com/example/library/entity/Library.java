package com.example.library.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Класс-сущность (Entity) Library (Библиотека).
 * Представляет библиотеку, содержащую список книг.
 */
@Entity  // Аннотация JPA, обозначающая, что этот класс является сущностью и будет сохраняться в базе данных.
public class Library {

    @Id  // Указывает, что поле является первичным ключом.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // Автоинкрементное значение первичного ключа, управляемое базой данных.
    private Long id;

    // Название библиотеки
    private String name;

    /**
     * Список идентификаторов книг, находящихся в библиотеке.
     * Используется `@ElementCollection`, так как это коллекция примитивных значений (идентификаторов книг).
     */
    @ElementCollection  // Указывает, что это не обычное поле, а коллекция значений, хранящаяся в отдельной таблице.
    @CollectionTable(name = "library_books", joinColumns = @JoinColumn(name = "library_id"))
    // Определяет таблицу для хранения идентификаторов книг, связанных с библиотекой.
    @Column(name = "book_id")  
    // Указывает, что каждая строка в таблице `library_books` будет содержать `book_id`, связанный с `library_id`.
    private List<Long> bookIds;

    // ======== Конструкторы ========

    /**
     * Конструктор по умолчанию, необходимый для работы JPA.
     */
    public Library() {}

    /**
     * Конструктор с параметрами для создания объекта библиотеки.
     *
     * @param name Название библиотеки
     * @param bookIds Список идентификаторов книг в библиотеке
     */
    public Library(String name, List<Long> bookIds) {
        this.name = name;
        this.bookIds = bookIds;
    }

    // ======== Геттеры и сеттеры ========

    /**
     * Получить ID библиотеки.
     *
     * @return ID библиотеки
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить ID библиотеки.
     *
     * @param id Новый ID библиотеки
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название библиотеки.
     *
     * @return Название библиотеки
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название библиотеки.
     *
     * @param name Новое название библиотеки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить список идентификаторов книг, содержащихся в библиотеке.
     *
     * @return Список идентификаторов книг
     */
    public List<Long> getBookIds() {
        return bookIds;
    }

    /**
     * Установить список идентификаторов книг, содержащихся в библиотеке.
     *
     * @param bookIds Новый список идентификаторов книг
     */
    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}