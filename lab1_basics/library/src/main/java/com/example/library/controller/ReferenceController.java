package com.example.library.controller;

// Импорт DTO-классов, используемых для передачи данных без прямого использования сущностей
import com.example.library.dto.AuthorDTO;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.CategoryDTO;
// Импорт сервиса, который содержит бизнес-логику для работы со справочными данными
import com.example.library.service.ReferenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // Внедрение сервиса, который обрабатывает CRUD-операции для справочных сущностей.
    @Autowired
    private ReferenceService referenceService;

    // ========== Методы для работы с сущностью Author ==========

    /**
     * Создание нового автора.
     * URL: POST /api/reference/authors
     * @param dto объект AuthorDTO, содержащий данные автора (например, имя)
     * @return созданный объект AuthorDTO с присвоенным ID
     */
    @PostMapping("/authors")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO dto) {
        return referenceService.createAuthor(dto);
    }

    /**
     * Получение автора по его ID.
     * URL: GET /api/reference/authors/{id}
     * @param id уникальный идентификатор автора
     * @return объект AuthorDTO, если автор найден, иначе null
     */
    @GetMapping("/authors/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return referenceService.getAuthor(id);
    }

    /**
     * Получение списка всех авторов.
     * URL: GET /api/reference/authors
     * @return список объектов AuthorDTO
     */
    @GetMapping("/authors")
    public List<AuthorDTO> getAllAuthors() {
        return referenceService.getAllAuthors();
    }

    /**
     * Обновление информации об авторе.
     * URL: PUT /api/reference/authors/{id}
     * @param id уникальный идентификатор автора для обновления
     * @param dto объект AuthorDTO с новыми данными
     * @return обновленный объект AuthorDTO
     */
    @PutMapping("/authors/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        return referenceService.updateAuthor(id, dto);
    }

    /**
     * Удаление автора по его ID.
     * URL: DELETE /api/reference/authors/{id}
     * @param id уникальный идентификатор автора
     */
    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        referenceService.deleteAuthor(id);
    }

    // ========== Методы для работы с сущностью Publisher ==========

    /**
     * Создание нового издателя.
     * URL: POST /api/reference/publishers
     * @param dto объект PublisherDTO, содержащий данные издателя (например, название)
     * @return созданный объект PublisherDTO с присвоенным ID
     */
    @PostMapping("/publishers")
    public PublisherDTO createPublisher(@RequestBody PublisherDTO dto) {
        return referenceService.createPublisher(dto);
    }

    /**
     * Получение издателя по его ID.
     * URL: GET /api/reference/publishers/{id}
     * @param id уникальный идентификатор издателя
     * @return объект PublisherDTO, если издатель найден, иначе null
     */
    @GetMapping("/publishers/{id}")
    public PublisherDTO getPublisher(@PathVariable Long id) {
        return referenceService.getPublisher(id);
    }

    /**
     * Получение списка всех издателей.
     * URL: GET /api/reference/publishers
     * @return список объектов PublisherDTO
     */
    @GetMapping("/publishers")
    public List<PublisherDTO> getAllPublishers() {
        return referenceService.getAllPublishers();
    }

    /**
     * Обновление информации об издателе.
     * URL: PUT /api/reference/publishers/{id}
     * @param id уникальный идентификатор издателя для обновления
     * @param dto объект PublisherDTO с новыми данными
     * @return обновленный объект PublisherDTO
     */
    @PutMapping("/publishers/{id}")
    public PublisherDTO updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO dto) {
        return referenceService.updatePublisher(id, dto);
    }

    /**
     * Удаление издателя по его ID.
     * URL: DELETE /api/reference/publishers/{id}
     * @param id уникальный идентификатор издателя
     */
    @DeleteMapping("/publishers/{id}")
    public void deletePublisher(@PathVariable Long id) {
        referenceService.deletePublisher(id);
    }

    // ========== Методы для работы с сущностью Category ==========

    /**
     * Создание новой категории.
     * URL: POST /api/reference/categories
     * @param dto объект CategoryDTO, содержащий данные категории (например, название)
     * @return созданный объект CategoryDTO с присвоенным ID
     */
    @PostMapping("/categories")
    public CategoryDTO createCategory(@RequestBody CategoryDTO dto) {
        return referenceService.createCategory(dto);
    }

    /**
     * Получение категории по ее ID.
     * URL: GET /api/reference/categories/{id}
     * @param id уникальный идентификатор категории
     * @return объект CategoryDTO, если категория найдена, иначе null
     */
    @GetMapping("/categories/{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        return referenceService.getCategory(id);
    }

    /**
     * Получение списка всех категорий.
     * URL: GET /api/reference/categories
     * @return список объектов CategoryDTO
     */
    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return referenceService.getAllCategories();
    }

    /**
     * Обновление информации о категории.
     * URL: PUT /api/reference/categories/{id}
     * @param id уникальный идентификатор категории для обновления
     * @param dto объект CategoryDTO с новыми данными
     * @return обновленный объект CategoryDTO
     */
    @PutMapping("/categories/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return referenceService.updateCategory(id, dto);
    }

    /**
     * Удаление категории по ее ID.
     * URL: DELETE /api/reference/categories/{id}
     * @param id уникальный идентификатор категории
     */
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        referenceService.deleteCategory(id);
    }
}