package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.ReviewRequest;
import com.example.LibraryApp.entity.Review;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    Review requestToEntity(ReviewRequest reviewRequest);

    @Mapping(target = "lastUpdateDate", source = "updatedAt")
    ReviewDto entityToDto(Review review);

    void updateEntityFromDto(ReviewDto dto, @MappingTarget Review entity);
}
