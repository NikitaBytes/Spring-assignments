package com.example.library.dao;

import com.example.library.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Book save(Book book) {
        // Возвращаем результат merge(...), чтобы у книги был реальный ID.
        return (Book) getSession().merge(book);
    }

    public Book findById(Long id) {
        return getSession().get(Book.class, id);
    }

    public List<Book> findAll() {
        return getSession().createQuery("from Book", Book.class).list();
    }

    public void delete(Book book) {
        getSession().remove(book);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }
}