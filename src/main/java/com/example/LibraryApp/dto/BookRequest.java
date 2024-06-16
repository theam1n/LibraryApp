package com.example.LibraryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    private String title;
    private BigDecimal price;
    private ZonedDateTime publicationDate;
    private PublishingHouseDto publishingHouse;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
}
