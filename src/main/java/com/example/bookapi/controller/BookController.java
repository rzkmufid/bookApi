package com.example.bookapi.controller;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.dto.BookRequestDTO;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@Tag(name = "Book Management", description = "API for managing books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService, BookService bookServices) {
        this.bookService = bookService;
    }

    @Operation(summary = "Create a new book", description = "Create a new book with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/create")
    public BookDTO create(@RequestBody @Parameter(description = "Book details to be created", required = true) BookRequestDTO request) {
        final Book book = bookService.mapToEntity(request);
        final Book result = bookService.create(book);
        return bookService.mapToDTO(result);
    }


    @Operation(summary = "Update an existing book", description = "Update an existing book with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/update/{id}")
    public BookDTO update(@PathVariable @Parameter(description = "Book ID to update", required = true) Long id,
                          @RequestBody @Parameter(description = "Book details to be updated", required = true) BookDTO request) {
        final Book book = bookService.mapToEntity(request);
        final Book result = bookService.update(id, book);
        return bookService.mapToDTO(result);
    }


    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of books retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/all")
    public List<BookDTO> findAll() {
        return bookService.findAll().stream().map(bookService::mapToDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Get books with pagination", description = "Retrieve a paginated list of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginated list of books retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/books")
    public Page<BookDTO> findAllWithPagination(
            @Parameter(description = "Page number (0-indexed)", required = true, schema = @Schema(type = "integer", example = "0")) @PageableDefault() int page,
            @Parameter(description = "Page size", required = true, schema = @Schema(type = "integer", example = "10")) @PageableDefault() int size) {
        return bookService.findAll(PageRequest.of(page, size)).map(bookService::mapToDTO);
    }


    @Operation(summary = "Delete a book", description = "Delete a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable @Parameter(description = "Book ID to delete", required = true) Long id) {
        return bookService.delete(id);
    }
}
