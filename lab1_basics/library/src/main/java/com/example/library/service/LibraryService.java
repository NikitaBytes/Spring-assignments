package com.example.library.service;

import com.example.library.dto.LibraryDTO;
import com.example.library.entity.Library;
import com.example.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Аннотация @Service помечает этот класс как сервисный компонент, обрабатывающий бизнес-логику
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository; // Внедрение репозитория для работы с библиотеками

    /**
     * Создает новую библиотеку на основе DTO.
     * 
     * @param dto Объект DTO, содержащий имя библиотеки и список идентификаторов книг.
     * @return Объект LibraryDTO с сохраненными данными.
     */
    public LibraryDTO createLibrary(LibraryDTO dto) {
        // Создаём новый объект Library из DTO
        Library library = new Library(dto.getName(), dto.getBookIds());
        
        // Сохраняем в базе данных
        Library saved = libraryRepository.save(library);
        
        // Преобразуем сохраненную сущность в DTO и возвращаем
        return convertToDTO(saved);
    }

    /**
     * Получает библиотеку по её ID.
     * 
     * @param id Идентификатор библиотеки.
     * @return Объект LibraryDTO, если найдено, иначе null.
     */
    public LibraryDTO getLibrary(Long id) {
        return libraryRepository.findById(id)
                .map(this::convertToDTO) // Если библиотека найдена, преобразуем её в DTO
                .orElse(null); // Если не найдена, возвращаем null
    }

    /**
     * Обновляет данные библиотеки по ID.
     * 
     * @param id  Идентификатор библиотеки, которую нужно обновить.
     * @param dto Новые данные для обновления (имя и список книг).
     * @return Обновленный объект LibraryDTO, если найдено, иначе null.
     */
    public LibraryDTO updateLibrary(Long id, LibraryDTO dto) {
        return libraryRepository.findById(id).map(library -> {
            // Обновляем имя библиотеки
            library.setName(dto.getName());
            
            // Обновляем список идентификаторов книг
            library.setBookIds(dto.getBookIds());

            // Сохраняем изменения в базе данных
            Library updated = libraryRepository.save(library);

            // Преобразуем обновленную сущность в DTO и возвращаем
            return convertToDTO(updated);
        }).orElse(null); // Если библиотека не найдена, возвращаем null
    }

    /**
     * Удаляет библиотеку по её ID.
     * 
     * @param id Идентификатор удаляемой библиотеки.
     */
    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }

    /**
     * Получает список всех библиотек.
     * 
     * @return Список объектов LibraryDTO.
     */
    public List<LibraryDTO> getAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(this::convertToDTO) // Преобразуем каждую найденную библиотеку в DTO
                .collect(Collectors.toList());
    }

    /**
     * Преобразует объект сущности Library в DTO.
     * 
     * @param library Объект библиотеки.
     * @return Объект LibraryDTO.
     */
    private LibraryDTO convertToDTO(Library library) {
        return new LibraryDTO(library.getId(), library.getName(), library.getBookIds());
    }
}