package com.example.bookapi.dto;

import java.util.List;

public record BookRequestDTO(String title, String publisher, String genre, String publishDate, List<AuthorDTO> authors) {
}
