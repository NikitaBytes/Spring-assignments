package com.example.library.dao;

import com.example.library.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Получает текущую сессию Hibernate.
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Сохраняет или обновляет автора.
     */
    public Author save(Author author) {
        // ВАЖНО: возвращаем именно результат merge, чтобы
        // у объекта был сгенерированный ID (если это новый автор).
        return (Author) getSession().merge(author);
    }

    /**
     * Ищет автора по ID.
     */
    public Author findById(Long id) {
        return getSession().get(Author.class, id);
    }

    /**
     * Получает список всех авторов.
     */
    public List<Author> findAll() {
        return getSession().createQuery("from Author", Author.class).list();
    }

    /**
     * Удаляет автора.
     */
    public void delete(Author author) {
        getSession().remove(author);
    }

    /**
     * Проверяет, существует ли автор по заданному ID.
     */
    public boolean existsById(Long id) {
        return findById(id) != null;
    }
}