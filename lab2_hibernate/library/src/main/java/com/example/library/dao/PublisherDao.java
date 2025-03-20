package com.example.library.dao;

import com.example.library.entity.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Publisher save(Publisher publisher) {
        // Возвращаем Managed Entity
        return (Publisher) getSession().merge(publisher);
    }

    public Publisher findById(Long id) {
        return getSession().get(Publisher.class, id);
    }

    public List<Publisher> findAll() {
        return getSession().createQuery("from Publisher", Publisher.class).list();
    }

    public void delete(Publisher publisher) {
        getSession().remove(publisher);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }
}