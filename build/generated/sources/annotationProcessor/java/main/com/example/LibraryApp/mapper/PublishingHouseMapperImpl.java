package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.PublishingHouseRequest;
import com.example.LibraryApp.entity.PublishingHouse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-16T22:04:42+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.9 (IBM Corporation)"
)
@Component
public class PublishingHouseMapperImpl implements PublishingHouseMapper {

    @Override
    public PublishingHouse requestToEntity(PublishingHouseRequest publishingHouseRequest) {
        if ( publishingHouseRequest == null ) {
            return null;
        }

        PublishingHouse.PublishingHouseBuilder publishingHouse = PublishingHouse.builder();

        if ( publishingHouseRequest.getName() != null ) {
            publishingHouse.name( publishingHouseRequest.getName() );
        }
        if ( publishingHouseRequest.getAddress() != null ) {
            publishingHouse.address( publishingHouseRequest.getAddress() );
        }
        if ( publishingHouseRequest.getContactNumber() != null ) {
            publishingHouse.contactNumber( publishingHouseRequest.getContactNumber() );
        }

        return publishingHouse.build();
    }

    @Override
    public PublishingHouseDto entityToDto(PublishingHouse publishingHouse) {
        if ( publishingHouse == null ) {
            return null;
        }

        PublishingHouseDto publishingHouseDto = new PublishingHouseDto();

        if ( publishingHouse.getId() != null ) {
            publishingHouseDto.setId( publishingHouse.getId() );
        }
        if ( publishingHouse.getName() != null ) {
            publishingHouseDto.setName( publishingHouse.getName() );
        }
        if ( publishingHouse.getAddress() != null ) {
            publishingHouseDto.setAddress( publishingHouse.getAddress() );
        }
        if ( publishingHouse.getContactNumber() != null ) {
            publishingHouseDto.setContactNumber( publishingHouse.getContactNumber() );
        }

        return publishingHouseDto;
    }

    @Override
    public PublishingHouse dtoToEntity(PublishingHouseDto publishingHouseDto) {
        if ( publishingHouseDto == null ) {
            return null;
        }

        PublishingHouse.PublishingHouseBuilder publishingHouse = PublishingHouse.builder();

        if ( publishingHouseDto.getId() != null ) {
            publishingHouse.id( publishingHouseDto.getId() );
        }
        if ( publishingHouseDto.getName() != null ) {
            publishingHouse.name( publishingHouseDto.getName() );
        }
        if ( publishingHouseDto.getAddress() != null ) {
            publishingHouse.address( publishingHouseDto.getAddress() );
        }
        if ( publishingHouseDto.getContactNumber() != null ) {
            publishingHouse.contactNumber( publishingHouseDto.getContactNumber() );
        }

        return publishingHouse.build();
    }

    @Override
    public void updateEntityFromDto(PublishingHouseDto dto, PublishingHouse entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getAddress() != null ) {
            entity.setAddress( dto.getAddress() );
        }
        if ( dto.getContactNumber() != null ) {
            entity.setContactNumber( dto.getContactNumber() );
        }
    }
}
