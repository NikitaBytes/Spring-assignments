package com.example.library.dao;

import com.example.library.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Category save(Category category) {
        // Точно так же возвращаем результат merge(...).
        return (Category) getSession().merge(category);
    }

    public Category findById(Long id) {
        return getSession().get(Category.class, id);
    }

    public List<Category> findAll() {
        return getSession().createQuery("from Category", Category.class).list();
    }

    public void delete(Category category) {
        getSession().remove(category);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }
}