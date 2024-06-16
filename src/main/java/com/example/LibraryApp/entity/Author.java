package com.example.LibraryApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraph(
        name = "author-books-graph",
        attributeNodes = @NamedAttributeNode("books")
)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String bio;

    @Builder.Default
    private Boolean isEnabled = true;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();


}
