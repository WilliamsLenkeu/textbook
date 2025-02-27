package com.cahier.backend.services;

import com.cahier.backend.entities.TextBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cahier.backend.repositories.TextBookRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TextBookService {
    private final TextBookRepository textBookRepository;

    public List<TextBook> getAllTextBooks() {
        return textBookRepository.findAll();
    }

    public Optional<TextBook> getTextBookById(String id) {
        return textBookRepository.findById(id);
    }

    public TextBook saveTextBook(TextBook textBook) {
        return textBookRepository.save(textBook);
    }

    public void deleteTextBook(String id) {
        textBookRepository.deleteById(id);
    }
}
