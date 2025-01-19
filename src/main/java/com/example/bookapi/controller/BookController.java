package com.example.bookapi.controller;


import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping("/create")
    public BookDTO create(@RequestBody BookDTO request) {
        final Book book = bookService.mapToEntity(request);
        final Book result = bookService.create(book);
        return bookService.mapToDTO(result);
    }

    @PutMapping("/update/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO request) {
        final Book book = bookService.mapToEntity(request);
        final Book result = bookService.update(id, book);
        return bookService.mapToDTO(result);
    }

    @GetMapping("/all")
    public List<BookDTO> findAll(){
        return bookService.findAll().stream().map(book -> bookService.mapToDTO(book)).collect(Collectors.toList());
    }
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return bookService.delete(id);
    }


}
