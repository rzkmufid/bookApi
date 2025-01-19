package com.example.bookapi.controller;


import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/books")
    public Page<BookDTO> findAllWithPagination(@PageableDefault Pageable pageable){
        return bookService.findAll(pageable).map(book -> bookService.mapToDTO(book));
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return bookService.delete(id);
    }


}
