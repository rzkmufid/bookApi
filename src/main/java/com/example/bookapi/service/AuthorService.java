package com.example.bookapi.service;

import com.example.bookapi.dto.AuthorDTO;
import com.example.bookapi.model.Author;

public interface AuthorService {
    Author addAuthor(Long bookId, Author author);

    Author maptoEntity(AuthorDTO authorDTO);

    AuthorDTO maptoDTO(Author author);
}
