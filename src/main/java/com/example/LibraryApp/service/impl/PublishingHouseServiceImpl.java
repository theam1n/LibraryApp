package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.PublishingHouseRequest;
import com.example.LibraryApp.entity.PublishingHouse;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.PublishingHouseMapper;
import com.example.LibraryApp.repository.PublishingHouseRepository;
import com.example.LibraryApp.service.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    private final PublishingHouseMapper publishingHouseMapper;

    @Override
    public PublishingHouseDto savePublishingHouse(PublishingHouseRequest publishingHouseRequest) {

        var publishingHouse = publishingHouseMapper.
                requestToEntity(publishingHouseRequest);
        var savedpublishingHouse = publishingHouseMapper.
                entityToDto(publishingHouseRepository.save(publishingHouse));

        return savedpublishingHouse;
    }

    @Override
    @Cacheable(value = "publishingHouses", key = "'allPublishingHouses'")
    public Page<PublishingHouseDto> getAllPublishingHouses(Pageable pageable) {

        var publishingHouses = publishingHouseRepository.
                findAllByIsEnabledTrue(pageable);
        var publishingHouseDtos = publishingHouses.
                map(publishingHouseMapper:: entityToDto);

        return publishingHouseDtos;
    }

    @Override
    @Cacheable(value = "publishingHouses", key = "'publishingHouseById_' + #id")
    public PublishingHouseDto getPublishingHouseById(Long id) {

        var publishingHouse = findById(id);
        var publishingHouseDto = publishingHouseMapper.entityToDto(publishingHouse);

        return publishingHouseDto;
    }

    @Override
    @CachePut(value = "publishingHouses", key = "'publishingHouseById_' + #publishingHouse.id")
    public PublishingHouseDto updatePublishingHouse(PublishingHouseDto publishingHouse) {

        var existingPublishingHouse = findById(publishingHouse.getId());

        publishingHouseMapper.updateEntityFromDto(publishingHouse, existingPublishingHouse);

        var publishingHouseDto = publishingHouseMapper
                .entityToDto(publishingHouseRepository.save(existingPublishingHouse));

        return publishingHouseDto;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "publishingHouses", key = "'allPublishingHouses'", allEntries = true),
            @CacheEvict(value = "publishingHouses", key = "'publishingHouseById_' + #id")
    })
    public void deletePublishingHouse(Long id) {

        var publishingHouse = findById(id);

        publishingHouse.setIsEnabled(false);
        publishingHouseRepository.save(publishingHouse);
    }

    private PublishingHouse findById(Long id) {
        return Optional.ofNullable(publishingHouseRepository.findByIdAndIsEnabledTrue(id)
                .orElseThrow(() -> new NotFoundException("PublishingHouse not found with id: " + id)))
                .get();

    }
}
