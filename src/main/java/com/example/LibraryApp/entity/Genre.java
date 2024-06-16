package com.example.LibraryApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraph(
        name = "genre-books-graph",
        attributeNodes = @NamedAttributeNode("books")
)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Builder.Default
    private Boolean isEnabled = true;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books = new HashSet<>();
}
