package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.PublishingHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {

    Optional<PublishingHouse> findByIdAndIsEnabledTrue(Long id);

    Page<PublishingHouse> findAllByIsEnabledTrue(Pageable pageable);
}
