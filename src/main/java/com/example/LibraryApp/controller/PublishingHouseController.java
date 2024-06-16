package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.PublishingHouseRequest;
import com.example.LibraryApp.service.PublishingHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/publishing-house")
@RequiredArgsConstructor
@Tag(name = "Publishing House Controller")
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @Operation(summary = "Save a new publishing house",
            description = "Creates a new publishing house and returns the created publishing house data")
    @PostMapping
    public ResponseEntity<PublishingHouseDto> savePublishingHouse(
            @RequestBody PublishingHouseRequest publishingHouseRequest) {

        PublishingHouseDto response = publishingHouseService.savePublishingHouse(publishingHouseRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all publishing houses",
            description = "Returns a paginated list of all publishing houses")
    @GetMapping
    public ResponseEntity<Page<PublishingHouseDto>> getAllPublishingHouses(Pageable pageable) {

        Page<PublishingHouseDto> response = publishingHouseService.getAllPublishingHouses(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get publishing house by ID",
            description = "Returns a specific publishing house by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<PublishingHouseDto> getPublishingHouseById(
            @PathVariable Long id) {

        PublishingHouseDto response = publishingHouseService.getPublishingHouseById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a publishing house",
            description = "Updates the information of an existing publishing house")
    @PutMapping
    public ResponseEntity<PublishingHouseDto> updatePublishingHouse(
            @RequestBody PublishingHouseDto publishingHouse) {

        PublishingHouseDto response = publishingHouseService.updatePublishingHouse(publishingHouse);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a publishing house",
            description = "Deletes an existing publishing house by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublishingHouse(@PathVariable Long id){

        publishingHouseService.deletePublishingHouse(id);
    }

}
