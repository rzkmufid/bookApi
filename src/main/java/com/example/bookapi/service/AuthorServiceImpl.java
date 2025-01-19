package com.example.bookapi.service;

import com.example.bookapi.dto.AuthorDTO;
import com.example.bookapi.model.Author;
import com.example.bookapi.model.Book;
import com.example.bookapi.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Long authorId, Author author) {
        final Book book = bookService.findById(authorId);
        if (book != null) {
//            add Author to db
            author = authorRepository.save(author);
            if (book.getAuthors() != null) {
                List<Author> authors = book.getAuthors();
                authors.add(author);
            } else {
                book.setAuthors(Collections.singletonList(author));
            }
            bookService.create(book);
            return author;
        }
        return null;
    }

    @Override
    public Author maptoEntity(AuthorDTO authorDTO) {
        return mapper.convertValue(authorDTO, Author.class);
    }

    @Override
    public AuthorDTO maptoDTO(Author author) {
        return mapper.convertValue(author, AuthorDTO.class);
    }
}
