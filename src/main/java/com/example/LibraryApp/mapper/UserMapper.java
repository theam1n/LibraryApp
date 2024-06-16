package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.UserDto;
import com.example.LibraryApp.dto.UserRequest;
import com.example.LibraryApp.entity.Review;
import com.example.LibraryApp.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "stringToCharArray")
    User requestToEntity(UserRequest userRequest);

    UserDto entityToDto(User user);

    UserRequest entityToRequest(User user);

    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);

    @Named("stringToCharArray")
    default char[] stringToCharArray(String password) {
        if (password == null) {
            return null;
        }
        return password.toCharArray();
    }
}
