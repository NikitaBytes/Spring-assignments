package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.CategoryDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Publisher;
import com.example.library.entity.Category;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.PublisherRepository;
import com.example.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервисный класс для управления справочной информацией: авторами, издателями и категориями книг.
 * 
 * Обрабатывает CRUD-операции (создание, чтение, обновление, удаление) для этих сущностей.
 */
@Service
public class ReferenceService {

    @Autowired
    private AuthorRepository authorRepository; // Репозиторий для работы с авторами
    
    @Autowired
    private PublisherRepository publisherRepository; // Репозиторий для работы с издателями
    
    @Autowired
    private CategoryRepository categoryRepository; // Репозиторий для работы с категориями

    // === Методы для Author (Авторов) ===

    /**
     * Создаёт нового автора в базе данных.
     *
     * @param dto DTO-объект с именем автора.
     * @return DTO с сохранёнными данными автора.
     */
    public AuthorDTO createAuthor(AuthorDTO dto) {
        // Создаём новый объект Author из DTO
        Author author = new Author(dto.getName());
        
        // Сохраняем в базе
        Author saved = authorRepository.save(author);
        
        // Возвращаем DTO с сохранёнными данными
        return new AuthorDTO(saved.getId(), saved.getName());
    }

    /**
     * Получает автора по его ID.
     *
     * @param id Идентификатор автора.
     * @return DTO с данными автора, если найден, иначе null.
     */
    public AuthorDTO getAuthor(Long id) {
        return authorRepository.findById(id)
                .map(a -> new AuthorDTO(a.getId(), a.getName())) // Преобразуем Author в AuthorDTO
                .orElse(null); // Если не найден, возвращаем null
    }

    /**
     * Получает список всех авторов.
     *
     * @return Список DTO всех авторов.
     */
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(a -> new AuthorDTO(a.getId(), a.getName())) // Преобразуем каждый объект Author в DTO
                .collect(Collectors.toList());
    }

    /**
     * Обновляет информацию об авторе по его ID.
     *
     * @param id  Идентификатор автора.
     * @param dto DTO с новыми данными автора.
     * @return Обновлённый объект AuthorDTO, если найден, иначе null.
     */
    public AuthorDTO updateAuthor(Long id, AuthorDTO dto) {
        return authorRepository.findById(id).map(author -> {
            author.setName(dto.getName());
            Author updated = authorRepository.save(author);
            return new AuthorDTO(updated.getId(), updated.getName());
        }).orElse(null);
    }

    /**
     * Удаляет автора по его ID.
     *
     * @param id Идентификатор автора.
     */
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    // === Методы для Publisher (Издателей) ===

    /**
     * Создаёт нового издателя.
     *
     * @param dto DTO-объект с именем издателя.
     * @return DTO с сохранёнными данными издателя.
     */
    public PublisherDTO createPublisher(PublisherDTO dto) {
        Publisher publisher = new Publisher(dto.getName());
        Publisher saved = publisherRepository.save(publisher);
        return new PublisherDTO(saved.getId(), saved.getName());
    }

    /**
     * Получает издателя по его ID.
     *
     * @param id Идентификатор издателя.
     * @return DTO с данными издателя, если найден, иначе null.
     */
    public PublisherDTO getPublisher(Long id) {
        return publisherRepository.findById(id)
                .map(p -> new PublisherDTO(p.getId(), p.getName()))
                .orElse(null);
    }

    /**
     * Получает список всех издателей.
     *
     * @return Список DTO всех издателей.
     */
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(p -> new PublisherDTO(p.getId(), p.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Обновляет информацию об издателе по его ID.
     *
     * @param id  Идентификатор издателя.
     * @param dto DTO с новыми данными издателя.
     * @return Обновлённый объект PublisherDTO, если найден, иначе null.
     */
    public PublisherDTO updatePublisher(Long id, PublisherDTO dto) {
        return publisherRepository.findById(id).map(publisher -> {
            publisher.setName(dto.getName());
            Publisher updated = publisherRepository.save(publisher);
            return new PublisherDTO(updated.getId(), updated.getName());
        }).orElse(null);
    }

    /**
     * Удаляет издателя по его ID.
     *
     * @param id Идентификатор издателя.
     */
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    // === Методы для Category (Категорий) ===

    /**
     * Создаёт новую категорию книг.
     *
     * @param dto DTO-объект с названием категории.
     * @return DTO с сохранёнными данными категории.
     */
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = new Category(dto.getName());
        Category saved = categoryRepository.save(category);
        return new CategoryDTO(saved.getId(), saved.getName());
    }

    /**
     * Получает категорию по её ID.
     *
     * @param id Идентификатор категории.
     * @return DTO с данными категории, если найдена, иначе null.
     */
    public CategoryDTO getCategory(Long id) {
        return categoryRepository.findById(id)
                .map(c -> new CategoryDTO(c.getId(), c.getName()))
                .orElse(null);
    }

    /**
     * Получает список всех категорий.
     *
     * @return Список DTO всех категорий.
     */
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryDTO(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Обновляет информацию о категории по её ID.
     *
     * @param id  Идентификатор категории.
     * @param dto DTO с новыми данными категории.
     * @return Обновлённый объект CategoryDTO, если найдена, иначе null.
     */
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(dto.getName());
            Category updated = categoryRepository.save(category);
            return new CategoryDTO(updated.getId(), updated.getName());
        }).orElse(null);
    }

    /**
     * Удаляет категорию по её ID.
     *
     * @param id Идентификатор категории.
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}