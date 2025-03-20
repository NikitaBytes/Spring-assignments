package com.example.library.service;

import com.example.library.dao.LibraryDao;  // Используем DAO вместо репозитория
import com.example.library.dto.LibraryDTO;
import com.example.library.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryService {

    @Autowired
    private LibraryDao libraryDao;  // DAO для работы с библиотеками

    /**
     * Создает новую библиотеку.
     */
    public LibraryDTO createLibrary(LibraryDTO dto) {
        Library library = new Library(dto.getName(), dto.getBookIds());
        Library saved = libraryDao.save(library);
        return convertToDTO(saved);
    }

    /**
     * Получает библиотеку по ID.
     */
    public LibraryDTO getLibrary(Long id) {
        Library library = libraryDao.findById(id);
        if (library == null) {
            throw new IllegalArgumentException("Library not found with id: " + id);
        }
        return convertToDTO(library);
    }

    /**
     * Обновляет библиотеку по ID.
     */
    public LibraryDTO updateLibrary(Long id, LibraryDTO dto) {
        Library library = libraryDao.findById(id);
        if (library == null) {
            throw new IllegalArgumentException("Library not found with id: " + id);
        }
        library.setName(dto.getName());
        library.setBookIds(dto.getBookIds());
        libraryDao.save(library);
        return convertToDTO(library);
    }

    /**
     * Удаляет библиотеку по ID.
     */
    public void deleteLibrary(Long id) {
        Library library = libraryDao.findById(id);
        if (library == null) {
            throw new IllegalArgumentException("Library not found with id: " + id);
        }
        libraryDao.delete(library);
    }

    /**
     * Получает список всех библиотек.
     */
    public List<LibraryDTO> getAllLibraries() {
        return libraryDao.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Преобразует сущность Library в DTO.
     */
    private LibraryDTO convertToDTO(Library library) {
        return new LibraryDTO(library.getId(), library.getName(), library.getBookIds());
    }
}