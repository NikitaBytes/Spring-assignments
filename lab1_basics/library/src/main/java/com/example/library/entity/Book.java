package com.example.library.entity;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Класс-сущность (Entity) Book (Книга).
 * Представляет книгу в базе данных и связывает её с автором, издателем и категориями.
 */
@Entity  // Аннотация JPA, указывающая, что класс является сущностью и будет храниться в базе данных.
public class Book {

    @Id  // Указывает, что это поле является первичным ключом.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    // Автоинкрементное значение первичного ключа, управляемое базой данных.
    private Long id;

    // Название книги
    private String title;

    // ======== Связи между сущностями ========

    /**
     * Связь "Многие к одному" (Many-to-One) с автором книги.
     * Каждая книга может быть написана только одним автором.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")  
    // Указывает на внешний ключ author_id в таблице book, который связывает книгу с автором.
    private Author author;

    /**
     * Связь "Многие к одному" (Many-to-One) с издателем книги.
     * Каждая книга издаётся только одним издателем.
     */
    @ManyToOne
    @JoinColumn(name = "publisher_id")  
    // Указывает на внешний ключ publisher_id в таблице book, который связывает книгу с издателем.
    private Publisher publisher;

    /**
     * Связь "Многие ко многим" (Many-to-Many) с категориями книги.
     * Одна книга может относиться к нескольким категориям, и одна категория может содержать несколько книг.
     */
    @ManyToMany
    @JoinTable(
        name = "book_category",  
        // Создаётся промежуточная таблица book_category для хранения связей между книгами и категориями.
        joinColumns = @JoinColumn(name = "book_id"),  
        // Связывает книгу с промежуточной таблицей через book_id.
        inverseJoinColumns = @JoinColumn(name = "category_id")  
        // Связывает категорию с промежуточной таблицей через category_id.
    )
    private Set<Category> categories;

    // ======== Конструкторы ========

    /**
     * Конструктор по умолчанию, необходимый для работы JPA.
     */
    public Book() {}

    /**
     * Конструктор с параметрами для создания объекта книги.
     *
     * @param title Название книги
     * @param author Автор книги
     * @param publisher Издатель книги
     * @param categories Категории, к которым относится книга
     */
    public Book(String title, Author author, Publisher publisher, Set<Category> categories) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.categories = categories;
    }

    // ======== Геттеры и сеттеры ========

    /**
     * Получить ID книги.
     *
     * @return ID книги
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить ID книги.
     *
     * @param id Новый ID книги
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название книги.
     *
     * @return Название книги
     */
    public String getTitle() {
        return title;
    }

    /**
     * Установить название книги.
     *
     * @param title Новое название книги
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Получить автора книги.
     *
     * @return Объект `Author`, представляющий автора книги
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Установить автора книги.
     *
     * @param author Новый автор книги
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Получить издателя книги.
     *
     * @return Объект `Publisher`, представляющий издателя книги
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Установить издателя книги.
     *
     * @param publisher Новый издатель книги
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Получить категории, к которым относится книга.
     *
     * @return Множество категорий книги
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Установить категории книги.
     *
     * @param categories Новое множество категорий
     */
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}