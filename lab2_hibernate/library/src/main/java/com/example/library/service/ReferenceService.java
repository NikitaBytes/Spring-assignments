package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Publisher;
import com.example.library.entity.Category;
import com.example.library.dao.AuthorDao;
import com.example.library.dao.PublisherDao;
import com.example.library.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для управления авторами, издателями и категориями.
 */
@Service
@Transactional
public class ReferenceService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private CategoryDao categoryDao;

    // ===================== АВТОРЫ =====================

    public AuthorDTO createAuthor(AuthorDTO dto) {
        Author author = new Author(dto.getName());
        Author saved = authorDao.save(author);
        return new AuthorDTO(saved.getId(), saved.getName());
    }

    public AuthorDTO getAuthor(Long id) {
        Author author = authorDao.findById(id);
        return (author != null) ? new AuthorDTO(author.getId(), author.getName()) : null;
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorDao.findAll().stream()
                .map(a -> new AuthorDTO(a.getId(), a.getName()))
                .collect(Collectors.toList());
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO dto) {
        Author author = authorDao.findById(id);
        if (author != null) {
            author.setName(dto.getName());
            Author updated = authorDao.save(author);
            return new AuthorDTO(updated.getId(), updated.getName());
        }
        return null;
    }

    public void deleteAuthor(Long id) {
        Author author = authorDao.findById(id);
        if (author != null) {
            authorDao.delete(author);
        }
    }

    // ===================== ИЗДАТЕЛИ =====================

    public PublisherDTO createPublisher(PublisherDTO dto) {
        Publisher publisher = new Publisher(dto.getName());
        Publisher saved = publisherDao.save(publisher);
        return new PublisherDTO(saved.getId(), saved.getName());
    }

    public PublisherDTO getPublisher(Long id) {
        Publisher publisher = publisherDao.findById(id);
        return (publisher != null) ? new PublisherDTO(publisher.getId(), publisher.getName()) : null;
    }

    public List<PublisherDTO> getAllPublishers() {
        return publisherDao.findAll().stream()
                .map(p -> new PublisherDTO(p.getId(), p.getName()))
                .collect(Collectors.toList());
    }

    public PublisherDTO updatePublisher(Long id, PublisherDTO dto) {
        Publisher publisher = publisherDao.findById(id);
        if (publisher != null) {
            publisher.setName(dto.getName());
            Publisher updated = publisherDao.save(publisher);
            return new PublisherDTO(updated.getId(), updated.getName());
        }
        return null;
    }

    public void deletePublisher(Long id) {
        Publisher publisher = publisherDao.findById(id);
        if (publisher != null) {
            publisherDao.delete(publisher);
        }
    }

    // ===================== КАТЕГОРИИ =====================

    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = new Category(dto.getName());
        Category saved = categoryDao.save(category);
        return new CategoryDTO(saved.getId(), saved.getName());
    }

    public CategoryDTO getCategory(Long id) {
        Category category = categoryDao.findById(id);
        return (category != null) ? new CategoryDTO(category.getId(), category.getName()) : null;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryDao.findAll().stream()
                .map(c -> new CategoryDTO(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = categoryDao.findById(id);
        if (category != null) {
            category.setName(dto.getName());
            Category updated = categoryDao.save(category);
            return new CategoryDTO(updated.getId(), updated.getName());
        }
        return null;
    }

    public void deleteCategory(Long id) {
        Category category = categoryDao.findById(id);
        if (category != null) {
            categoryDao.delete(category);
        }
    }
}