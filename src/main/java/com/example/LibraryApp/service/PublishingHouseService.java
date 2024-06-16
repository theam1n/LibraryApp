package com.example.LibraryApp.service;

import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.PublishingHouseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublishingHouseService {

    PublishingHouseDto savePublishingHouse(PublishingHouseRequest publishingHouseRequest);

    Page<PublishingHouseDto> getAllPublishingHouses(Pageable pageable);

    PublishingHouseDto getPublishingHouseById(Long id);

    PublishingHouseDto updatePublishingHouse(PublishingHouseDto publishingHouse);

    void deletePublishingHouse(Long id);
}
