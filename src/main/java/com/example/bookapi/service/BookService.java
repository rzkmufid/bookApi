package com.example.bookapi.service;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book create(Book book);
    Book update(Long id, Book book);
    Boolean delete(Long id);
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    Book findById(Long id);
    BookDTO mapToDTO(Book book);
    Book mapToEntity(BookDTO bookDTO);
}
