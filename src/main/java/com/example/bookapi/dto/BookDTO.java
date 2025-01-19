package com.example.bookapi.dto;

import java.util.List;

public record BookDTO(Long id, String title, String publisher, String genre, String publishDate, List<AuthorDTO> authors ) {

}
