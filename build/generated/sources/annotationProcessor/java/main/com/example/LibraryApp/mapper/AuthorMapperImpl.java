package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.AuthorRequest;
import com.example.LibraryApp.entity.Author;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T01:40:56+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.9 (IBM Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author requestToEntity(AuthorRequest authorRequest) {
        if ( authorRequest == null ) {
            return null;
        }

        Author.AuthorBuilder author = Author.builder();

        if ( authorRequest.getFirstName() != null ) {
            author.firstName( authorRequest.getFirstName() );
        }
        if ( authorRequest.getLastName() != null ) {
            author.lastName( authorRequest.getLastName() );
        }
        if ( authorRequest.getBio() != null ) {
            author.bio( authorRequest.getBio() );
        }

        return author.build();
    }

    @Override
    public AuthorDto entityToDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        if ( author.getId() != null ) {
            authorDto.setId( author.getId() );
        }
        if ( author.getFirstName() != null ) {
            authorDto.setFirstName( author.getFirstName() );
        }
        if ( author.getLastName() != null ) {
            authorDto.setLastName( author.getLastName() );
        }
        if ( author.getBio() != null ) {
            authorDto.setBio( author.getBio() );
        }

        return authorDto;
    }

    @Override
    public void updateEntityFromDto(AuthorDto dto, Author entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getBio() != null ) {
            entity.setBio( dto.getBio() );
        }
    }
}
