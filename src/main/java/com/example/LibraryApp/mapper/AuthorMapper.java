package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.AuthorRequest;
import com.example.LibraryApp.entity.Author;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    Author requestToEntity(AuthorRequest authorRequest);

    AuthorDto entityToDto(Author author);

    void updateEntityFromDto(AuthorDto dto, @MappingTarget Author entity);
}
