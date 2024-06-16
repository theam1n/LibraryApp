package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.GenreRequest;
import com.example.LibraryApp.entity.Genre;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GenreMapper {

    @Mapping(target = "id", ignore = true)
    Genre requestToEntity(GenreRequest genreRequest);

    GenreDto entityToDto(Genre genre);

    void updateEntityFromDto(GenreDto dto, @MappingTarget Genre entity);
}
