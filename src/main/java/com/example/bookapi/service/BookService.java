package com.example.bookapi.service;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.dto.BookRequestDTO;
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
//    Book mapToEntity(BookRequestDTO bookDTO);
default Book mapToEntity(BookRequestDTO request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setPublisher(request.publisher());
        book.setGenre(request.genre());
        book.setPublishDate(request.publishDate());
        // Map authors if necessary
        return book;
    }

     default Book mapToEntity(BookDTO request) {
        Book book = new Book();
        book.setId(request.id());
        book.setTitle(request.title());
        book.setPublisher(request.publisher());
        book.setGenre(request.genre());
        book.setPublishDate(request.publishDate());
        // Map authors if necessary
        return book;
    }
}
