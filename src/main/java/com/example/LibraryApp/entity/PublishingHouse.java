package com.example.LibraryApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publishing_house")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String contactNumber;

    @Builder.Default
    private Boolean isEnabled = true;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Book> books = new HashSet<>();
}
