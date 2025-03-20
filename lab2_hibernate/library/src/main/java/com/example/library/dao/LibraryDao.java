package com.example.library.dao;

import com.example.library.entity.Library;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class LibraryDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Library save(Library library) {
        // Аналогично
        return (Library) getSession().merge(library);
    }

    public Library findById(Long id) {
        return getSession().get(Library.class, id);
    }

    public List<Library> findAll() {
        return getSession().createQuery("from Library", Library.class).list();
    }

    public void delete(Library library) {
        getSession().remove(library);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }
}