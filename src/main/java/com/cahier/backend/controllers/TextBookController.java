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
                textBook.getClasseId(),
                textBook.getSignatures()
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
                tb.getClasseId(),
                tb.getSignatures()
        ))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TextBookDTO> createTextBook(@RequestBody TextBookDTO textBookDTO) {
        if (textBookDTO.getSignatures() == null || textBookDTO.getSignatures().size() != 2) {
            return ResponseEntity.badRequest().build();
        }

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
        textBook.setSignatures(textBookDTO.getSignatures());

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
                savedTextBook.getClasseId(),
                savedTextBook.getSignatures()
        ));
    }
}
