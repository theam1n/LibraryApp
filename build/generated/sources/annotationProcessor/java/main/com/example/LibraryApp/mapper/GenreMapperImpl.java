package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.GenreRequest;
import com.example.LibraryApp.entity.Genre;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T02:29:18+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.9 (IBM Corporation)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre requestToEntity(GenreRequest genreRequest) {
        if ( genreRequest == null ) {
            return null;
        }

        Genre.GenreBuilder genre = Genre.builder();

        if ( genreRequest.getName() != null ) {
            genre.name( genreRequest.getName() );
        }
        if ( genreRequest.getDescription() != null ) {
            genre.description( genreRequest.getDescription() );
        }

        return genre.build();
    }

    @Override
    public GenreDto entityToDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        if ( genre.getId() != null ) {
            genreDto.setId( genre.getId() );
        }
        if ( genre.getName() != null ) {
            genreDto.setName( genre.getName() );
        }
        if ( genre.getDescription() != null ) {
            genreDto.setDescription( genre.getDescription() );
        }

        return genreDto;
    }

    @Override
    public void updateEntityFromDto(GenreDto dto, Genre entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
    }
}
