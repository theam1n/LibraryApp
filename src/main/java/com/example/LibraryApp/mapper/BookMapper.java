package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.BookDto;
import com.example.LibraryApp.dto.BookRequest;
import com.example.LibraryApp.entity.Book;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book requestToEntity(BookRequest bookRequest);

    BookDto entityToDto(Book book);

    void updateEntityFromDto(BookDto dto, @MappingTarget Book entity);
}
