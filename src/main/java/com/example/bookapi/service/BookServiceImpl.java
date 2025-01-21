package com.example.bookapi.service;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.dto.BookRequestDTO;
import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book create(Book book) {
        final Book result = bookRepository.save(book);
        return result;
    }

    @Override
    public Book update(Long id, Book book) {
        Book result = findById(id);
        if (result != null) {
            result.setTitle(book.getTitle());
            result.setPublisher(book.getPublisher());
            result.setGenre(book.getGenre());
            result.setPublishDate(book.getPublishDate());
            return bookRepository.save(result);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        final Book result = findById(id);
        if (result != null) {
//            hard delete
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) return result.get();
        return null;
    }

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public BookDTO mapToDTO(Book book) {
        return mapper.convertValue(book, BookDTO.class);
    }

    @Override
    public Book mapToEntity(BookRequestDTO bookDTO) {
        return mapper.convertValue(bookDTO, Book.class);
    }
}
