package com.example.LibraryApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "book-reviews-graph",
                attributeNodes = @NamedAttributeNode("reviews")
        ),
        @NamedEntityGraph(
                name = "book-authors-graph",
                attributeNodes = @NamedAttributeNode("authors")
        ),
        @NamedEntityGraph(
                name = "book-genres-graph",
                attributeNodes = @NamedAttributeNode("genres")
        ),
        @NamedEntityGraph(
                name = "book-publishingHouse-graph",
                attributeNodes = @NamedAttributeNode("publishingHouse")
        ),
        @NamedEntityGraph(
                name = "book-withAll",
                attributeNodes = {
                        @NamedAttributeNode("publishingHouse"),
                        @NamedAttributeNode("authors"),
                        @NamedAttributeNode("genres"),
                        @NamedAttributeNode("reviews")
                }
        )
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal price;

    private ZonedDateTime publicationDate;

    @Builder.Default
    private BigDecimal averageRating = new BigDecimal(0);

    @Builder.Default
    private Boolean isEnabled = true;

    @ManyToOne
    @JoinColumn(name = "publishingHouse_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PublishingHouse publishingHouse;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews = new HashSet<>();
}
