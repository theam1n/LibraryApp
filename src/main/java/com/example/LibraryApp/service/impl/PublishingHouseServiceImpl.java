package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.PublishingHouseRequest;
import com.example.LibraryApp.entity.PublishingHouse;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.PublishingHouseMapper;
import com.example.LibraryApp.repository.PublishingHouseRepository;
import com.example.LibraryApp.service.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    private final PublishingHouseMapper publishingHouseMapper;

    @Override
    public PublishingHouseDto savePublishingHouse(PublishingHouseRequest publishingHouseRequest) {
        log.info("ActionLog.savePublishingHouse.start request: {}",publishingHouseRequest);

        var publishingHouse = publishingHouseMapper.
                requestToEntity(publishingHouseRequest);
        var savedpublishingHouse = publishingHouseMapper.
                entityToDto(publishingHouseRepository.save(publishingHouse));

        log.info("ActionLog.savePublishingHouse.end response: {}",savedpublishingHouse);

        return savedpublishingHouse;
    }

    @Override
    @Cacheable(value = "publishingHouses", key = "'allPublishingHouses'")
    public Page<PublishingHouseDto> getAllPublishingHouses(Pageable pageable) {
        log.info("ActionLog.getAllPublishingHouses.start");

        var publishingHouses = publishingHouseRepository.findAll(pageable);
        var publishingHouseDtos = publishingHouses.map(publishingHouseMapper:: entityToDto);

        log.info("ActionLog.getAllPublishingHouses.end response: {}",publishingHouseDtos);

        return publishingHouseDtos;
    }

    @Override
    @Cacheable(value = "publishingHouses", key = "'publishingHouseById_' + #id")
    public PublishingHouseDto getPublishingHouseById(Long id) {
        log.info("ActionLog.getPublishingHouseById.start request: {}",id);

        var publishingHouse = findById(id);
        var publishingHouseDto = publishingHouseMapper.entityToDto(publishingHouse);

        log.info("ActionLog.getPublishingHouseById.end response: {}",publishingHouseDto);

        return publishingHouseDto;
    }

    @Override
    @CachePut(value = "publishingHouses", key = "'publishingHouseById_' + #publishingHouse.id")
    public PublishingHouseDto updatePublishingHouse(PublishingHouseDto publishingHouse) {
        log.info("ActionLog.updatePublishingHouse.start request: {}",publishingHouse);

        var existingPublishingHouse = findById(publishingHouse.getId());

        publishingHouseMapper.updateEntityFromDto(publishingHouse, existingPublishingHouse);

        var publishingHouseDto = publishingHouseMapper
                .entityToDto(publishingHouseRepository.save(existingPublishingHouse));

        log.info("ActionLog.updatePublishingHouse.end response: {}",publishingHouseDto);

        return publishingHouseDto;
    }

    @Override
    @CacheEvict(value = "publishingHouses", key = "'publishingHouseById_' + #id")
    public void deletePublishingHouse(Long id) {
        log.info("ActionLog.deletePublishingHouse.start request: {}",id);

        var publishingHouse = findById(id);

        publishingHouse.setIsEnabled(false);
        publishingHouseRepository.save(publishingHouse);

        log.info("ActionLog.deletePublishingHouse.end");
    }

    private PublishingHouse findById(Long id) {
        return Optional.ofNullable(publishingHouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PublishingHouse not found with id: " + id)))
                .get();

    }
}
