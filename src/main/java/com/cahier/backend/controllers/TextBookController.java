package com.cahier.backend.controllers;

import com.cahier.backend.dtos.TextBookDTO;
import com.cahier.backend.entities.TextBook;
import com.cahier.backend.services.TextBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/textbooks")
@RequiredArgsConstructor
public class TextBookController {
    private final TextBookService textBookService;

    @GetMapping
    public List<TextBookDTO> getAllTextBooks() {
        List<TextBook> textBooks = textBookService.getAllTextBooks();
        return textBooks.stream().map(textBook -> new TextBookDTO(
                textBook.getId(),
                textBook.getTitle(),
                textBook.getSubtitles(),
                textBook.getDate(),
                textBook.getHeureDebut(),
                textBook.getHeureFin(),
                textBook.getUeId(),
                textBook.getEnseignantId(),
                textBook.getDelegueId(),
                textBook.getClasseId()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextBookDTO> getTextBookById(@PathVariable String id) {
        Optional<TextBook> textBook = textBookService.getTextBookById(id);
        return textBook.map(tb -> ResponseEntity.ok(new TextBookDTO(
                tb.getId(),
                tb.getTitle(),
                tb.getSubtitles(),
                tb.getDate(),
                tb.getHeureDebut(),
                tb.getHeureFin(),
                tb.getUeId(),
                tb.getEnseignantId(),
                tb.getDelegueId(),
                tb.getClasseId()
        ))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TextBookDTO> createTextBook(@RequestBody TextBookDTO textBookDTO) {
        TextBook textBook = new TextBook();
        textBook.setTitle(textBookDTO.getTitle());
        textBook.setSubtitles(textBookDTO.getSubtitles());
        textBook.setDate(textBookDTO.getDate());
        textBook.setHeureDebut(textBookDTO.getHeureDebut());
        textBook.setHeureFin(textBookDTO.getHeureFin());
        textBook.setUeId(textBookDTO.getUeId());
        textBook.setEnseignantId(textBookDTO.getEnseignantId());
        textBook.setDelegueId(textBookDTO.getDelegueId());
        textBook.setClasseId(textBookDTO.getClasseId());

        TextBook savedTextBook = textBookService.saveTextBook(textBook);

        return ResponseEntity.ok(new TextBookDTO(
                savedTextBook.getId(),
                savedTextBook.getTitle(),
                savedTextBook.getSubtitles(),
                savedTextBook.getDate(),
                savedTextBook.getHeureDebut(),
                savedTextBook.getHeureFin(),
                savedTextBook.getUeId(),
                savedTextBook.getEnseignantId(),
                savedTextBook.getDelegueId(),
                savedTextBook.getClasseId()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTextBook(@PathVariable String id) {
        textBookService.deleteTextBook(id);
        return ResponseEntity.noContent().build();
    }
}
