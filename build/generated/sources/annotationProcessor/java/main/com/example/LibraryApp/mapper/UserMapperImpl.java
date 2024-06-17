package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.RoleDto;
import com.example.LibraryApp.dto.UserDto;
import com.example.LibraryApp.dto.UserRequest;
import com.example.LibraryApp.entity.Role;
import com.example.LibraryApp.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T21:45:29+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.9 (IBM Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User requestToEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( userRequest.getPassword() != null ) {
            user.password( stringToCharArray( userRequest.getPassword() ) );
        }
        if ( userRequest.getFirstName() != null ) {
            user.firstName( userRequest.getFirstName() );
        }
        if ( userRequest.getLastName() != null ) {
            user.lastName( userRequest.getLastName() );
        }
        if ( userRequest.getUsername() != null ) {
            user.username( userRequest.getUsername() );
        }
        Set<Role> set = roleDtoSetToRoleSet( userRequest.getRoles() );
        if ( set != null ) {
            user.roles( set );
        }

        return user.build();
    }

    @Override
    public UserDto entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( user.getId() != null ) {
            userDto.setId( user.getId() );
        }
        if ( user.getFirstName() != null ) {
            userDto.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userDto.setLastName( user.getLastName() );
        }
        if ( user.getUsername() != null ) {
            userDto.setUsername( user.getUsername() );
        }
        Set<RoleDto> set = roleSetToRoleDtoSet( user.getRoles() );
        if ( set != null ) {
            userDto.setRoles( set );
        }

        return userDto;
    }

    @Override
    public UserRequest entityToRequest(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequest userRequest = new UserRequest();

        if ( user.getFirstName() != null ) {
            userRequest.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userRequest.setLastName( user.getLastName() );
        }
        if ( user.getUsername() != null ) {
            userRequest.setUsername( user.getUsername() );
        }
        if ( user.getPassword() != null ) {
            userRequest.setPassword( user.getPassword() );
        }
        Set<RoleDto> set = roleSetToRoleDtoSet( user.getRoles() );
        if ( set != null ) {
            userRequest.setRoles( set );
        }

        return userRequest;
    }

    @Override
    public void updateEntityFromDto(UserDto dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getUsername() != null ) {
            entity.setUsername( dto.getUsername() );
        }
        if ( entity.getRoles() != null ) {
            Set<Role> set = roleDtoSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.getRoles().clear();
                entity.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = roleDtoSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.setRoles( set );
            }
        }
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        if ( roleDto.getId() != null ) {
            role.id( roleDto.getId() );
        }
        if ( roleDto.getName() != null ) {
            role.name( roleDto.getName() );
        }

        return role.build();
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleDtoToRole( roleDto ) );
        }

        return set1;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( role.getId() != null ) {
            roleDto.setId( role.getId() );
        }
        if ( role.getName() != null ) {
            roleDto.setName( role.getName() );
        }

        return roleDto;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDto( role ) );
        }

        return set1;
    }
}
