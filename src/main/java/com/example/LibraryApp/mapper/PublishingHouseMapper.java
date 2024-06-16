package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.PublishingHouseRequest;
import com.example.LibraryApp.entity.PublishingHouse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublishingHouseMapper {

    @Mapping(target = "id", ignore = true)
    PublishingHouse requestToEntity(PublishingHouseRequest publishingHouseRequest);

    PublishingHouseDto entityToDto(PublishingHouse publishingHouse);

    PublishingHouse dtoToEntity(PublishingHouseDto publishingHouseDto);

    void updateEntityFromDto(PublishingHouseDto dto, @MappingTarget PublishingHouse entity);
}
