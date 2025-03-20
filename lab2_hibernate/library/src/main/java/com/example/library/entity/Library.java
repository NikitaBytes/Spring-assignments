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
    private Long id;

    // Название библиотеки
    private String name;

    /**
     * Список идентификаторов книг, находящихся в библиотеке.
     * Используется `@ElementCollection`, так как это коллекция примитивных значений (идентификаторов книг).
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "library_books", joinColumns = @JoinColumn(name = "library_id"))
    @Column(name = "book_id")
    private List<Long> bookIds;

    // ======== Конструкторы ========

    public Library() {}

    public Library(String name, List<Long> bookIds) {
        this.name = name;
        this.bookIds = bookIds;
    }

    // ======== Геттеры и сеттеры ========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}