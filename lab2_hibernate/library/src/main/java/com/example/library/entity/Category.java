package com.example.library.entity;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Класс-сущность (Entity) Category (Категория).
 * Представляет категорию книг в базе данных и связывает её с книгами.
 */
@Entity  // Аннотация JPA, указывающая, что класс является сущностью и будет храниться в базе данных.
public class Category {

    @Id  // Указывает, что это поле является первичным ключом.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // Автоинкрементное значение первичного ключа, управляемое базой данных.
    private Long id;

    // Название категории
    private String name;

    /**
     * Связь "Многие ко многим" (Many-to-Many) с книгами.
     * Одна категория может содержать несколько книг, и одна книга может принадлежать нескольким категориям.
     */
    @ManyToMany(mappedBy = "categories")  
    // Связь управляется другой стороной в сущности `Book` через поле `categories`.
    private Set<Book> books;

    // ======== Конструкторы ========

    /**
     * Конструктор по умолчанию, необходимый для работы JPA.
     */
    public Category() {}

    /**
     * Конструктор с параметрами для создания объекта категории.
     *
     * @param name Название категории
     */
    public Category(String name) {
        this.name = name;
    }

    // ======== Геттеры и сеттеры ========

    /**
     * Получить ID категории.
     *
     * @return ID категории
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить ID категории.
     *
     * @param id Новый ID категории
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название категории.
     *
     * @return Название категории
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название категории.
     *
     * @param name Новое название категории
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить список книг, принадлежащих данной категории.
     *
     * @return Множество книг
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * Установить список книг, принадлежащих данной категории.
     *
     * @param books Новое множество книг
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}