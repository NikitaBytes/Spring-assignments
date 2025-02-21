package com.example.library.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Класс-сущность (Entity) Publisher (Издатель).
 * Представляет издательство, которое может выпускать книги.
 */
@Entity  // Обозначает, что этот класс является сущностью JPA и будет сохранен в базе данных.
public class Publisher {

    @Id  // Указывает, что поле `id` является первичным ключом.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // Автоматическая генерация ID (автоинкремент в базе данных).
    private Long id;

    // Название издательства
    private String name;

    /**
     * Связь One-to-Many: один издатель может издать много книг.
     * `mappedBy = "publisher"` указывает, что связь уже определена в сущности `Book`.
     * `cascade = CascadeType.ALL` означает, что операции с издателем (создание, удаление) распространяются на книги.
     * `orphanRemoval = true` автоматически удаляет книги, если они больше не связаны с издателем.
     */
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    // ======== Конструкторы ========

    /**
     * Конструктор по умолчанию, необходимый для работы JPA.
     */
    public Publisher() {}

    /**
     * Конструктор с параметрами для создания объекта издателя.
     *
     * @param name Название издательства
     */
    public Publisher(String name) {
        this.name = name;
    }

    // ======== Геттеры и сеттеры ========

    /**
     * Получить ID издателя.
     *
     * @return ID издателя
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить ID издателя.
     *
     * @param id Новый ID издателя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название издательства.
     *
     * @return Название издательства
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название издательства.
     *
     * @param name Новое название издательства
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить список книг, выпущенных этим издателем.
     *
     * @return Список книг, изданных этим издателем
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Установить список книг, выпущенных этим издателем.
     *
     * @param books Новый список книг
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}