package com.example.library.controller;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для управления справочными данными:
 * - Авторы (Author)
 * - Издатели (Publisher)
 * - Категории (Category)
 *
 * Все эндпоинты начинаются с /api/reference.
 */
@RestController
@RequestMapping("/api/reference")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    // ========== Методы для работы с сущностью Author ==========

    @PostMapping("/authors")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO dto) {
        return referenceService.createAuthor(dto);
    }

    @GetMapping("/authors/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return referenceService.getAuthor(id);
    }

    @GetMapping("/authors")
    public List<AuthorDTO> getAllAuthors() {
        return referenceService.getAllAuthors();
    }

    @PutMapping("/authors/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        return referenceService.updateAuthor(id, dto);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        referenceService.deleteAuthor(id);
    }

    // ========== Методы для работы с сущностью Publisher ==========

    @PostMapping("/publishers")
    public PublisherDTO createPublisher(@RequestBody PublisherDTO dto) {
        return referenceService.createPublisher(dto);
    }

    @GetMapping("/publishers/{id}")
    public PublisherDTO getPublisher(@PathVariable Long id) {
        return referenceService.getPublisher(id);
    }

    @GetMapping("/publishers")
    public List<PublisherDTO> getAllPublishers() {
        return referenceService.getAllPublishers();
    }

    @PutMapping("/publishers/{id}")
    public PublisherDTO updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO dto) {
        return referenceService.updatePublisher(id, dto);
    }

    @DeleteMapping("/publishers/{id}")
    public void deletePublisher(@PathVariable Long id) {
        referenceService.deletePublisher(id);
    }

    // ========== Методы для работы с сущностью Category ==========

    @PostMapping("/categories")
    public CategoryDTO createCategory(@RequestBody CategoryDTO dto) {
        return referenceService.createCategory(dto);
    }

    @GetMapping("/categories/{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        return referenceService.getCategory(id);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return referenceService.getAllCategories();
    }

    @PutMapping("/categories/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return referenceService.updateCategory(id, dto);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        referenceService.deleteCategory(id);
    }
}