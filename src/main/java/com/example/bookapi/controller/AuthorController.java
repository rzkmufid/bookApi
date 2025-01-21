package com.example.bookapi.controller;


import com.example.bookapi.dto.AuthorDTO;
import com.example.bookapi.model.Author;
import com.example.bookapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add/{bookId}")
    public AuthorDTO addAuthortoBook(@PathVariable Long bookId, @RequestBody AuthorDTO request) {
        final Author author = authorService.maptoEntity(request);
        final Author result = authorService.addAuthor(bookId, author);
        return authorService.maptoDTO(result);
    }
}
