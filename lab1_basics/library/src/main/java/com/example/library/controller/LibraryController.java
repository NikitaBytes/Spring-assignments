package com.example.library.controller;

import com.example.library.dto.LibraryDTO;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // Создание библиотеки
    @PostMapping
    public LibraryDTO createLibrary(@RequestBody LibraryDTO dto) {
        return libraryService.createLibrary(dto);
    }

    // Получение библиотеки по id
    @GetMapping("/{id}")
    public LibraryDTO getLibrary(@PathVariable Long id) {
        return libraryService.getLibrary(id);
    }

    // Получение списка всех библиотек
    @GetMapping
    public List<LibraryDTO> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    // Обновление библиотеки
    @PutMapping("/{id}")
    public LibraryDTO updateLibrary(@PathVariable Long id, @RequestBody LibraryDTO dto) {
        return libraryService.updateLibrary(id, dto);
    }

    // Удаление библиотеки
    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
    }
}