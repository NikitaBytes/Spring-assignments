package com.example.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

/**
 * Класс-сущность (Entity) Author (Автор).
 * Представляет автора книги в базе данных.
 */
@Entity  // Аннотация указывает, что этот класс является сущностью JPA и сопоставляется с таблицей в базе данных.
public class Author {

    @Id  // Указывает, что поле является первичным ключом.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // Стратегия автоинкремента для генерации значений первичного ключа в базе данных.
    private Long id;  

    private String name;  // Имя автора

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    // Один автор может иметь множество книг (One-to-Many).
    // `mappedBy = "author"` - означает, что связь управляется полем `author` в сущности `Book`.
    // `cascade = CascadeType.ALL` - любые изменения автора каскадно применяются к его книгам.
    // `orphanRemoval = true` - если книга больше не связана с автором, она будет удалена из базы.
    private List<Book> books;

    /**
     * Конструктор по умолчанию, необходимый для работы JPA.
     */
    public Author() {}

    /**
     * Конструктор для создания объекта с именем автора.
     *
     * @param name Имя автора
     */
    public Author(String name) {
        this.name = name;
    }

    // Геттеры и сеттеры для доступа и изменения полей

    /**
     * Получить ID автора.
     *
     * @return ID автора
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить ID автора.
     *
     * @param id Новый ID автора
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить имя автора.
     *
     * @return Имя автора
     */
    public String getName() {
        return name;
    }

    /**
     * Установить имя автора.
     *
     * @param name Новое имя автора
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить список книг, написанных этим автором.
     *
     * @return Список книг
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Установить список книг для автора.
     *
     * @param books Новый список книг
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}