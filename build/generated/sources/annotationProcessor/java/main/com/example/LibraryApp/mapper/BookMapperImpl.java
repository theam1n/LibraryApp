package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.BookDto;
import com.example.LibraryApp.dto.BookRequest;
import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.entity.Book;
import com.example.LibraryApp.entity.Genre;
import com.example.LibraryApp.entity.PublishingHouse;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T01:40:56+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.9 (IBM Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book requestToEntity(BookRequest bookRequest) {
        if ( bookRequest == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        if ( bookRequest.getTitle() != null ) {
            book.title( bookRequest.getTitle() );
        }
        if ( bookRequest.getPrice() != null ) {
            book.price( bookRequest.getPrice() );
        }
        if ( bookRequest.getPublicationDate() != null ) {
            book.publicationDate( bookRequest.getPublicationDate() );
        }
        if ( bookRequest.getPublishingHouse() != null ) {
            book.publishingHouse( publishingHouseDtoToPublishingHouse( bookRequest.getPublishingHouse() ) );
        }
        Set<Author> set = authorDtoSetToAuthorSet( bookRequest.getAuthors() );
        if ( set != null ) {
            book.authors( set );
        }
        Set<Genre> set1 = genreDtoSetToGenreSet( bookRequest.getGenres() );
        if ( set1 != null ) {
            book.genres( set1 );
        }

        return book.build();
    }

    @Override
    public BookDto entityToDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        if ( book.getId() != null ) {
            bookDto.setId( book.getId() );
        }
        if ( book.getTitle() != null ) {
            bookDto.setTitle( book.getTitle() );
        }
        if ( book.getPrice() != null ) {
            bookDto.setPrice( book.getPrice() );
        }
        if ( book.getPublicationDate() != null ) {
            bookDto.setPublicationDate( book.getPublicationDate() );
        }
        if ( book.getAverageRating() != null ) {
            bookDto.setAverageRating( book.getAverageRating() );
        }
        if ( book.getPublishingHouse() != null ) {
            bookDto.setPublishingHouse( publishingHouseToPublishingHouseDto( book.getPublishingHouse() ) );
        }
        Set<AuthorDto> set = authorSetToAuthorDtoSet( book.getAuthors() );
        if ( set != null ) {
            bookDto.setAuthors( set );
        }
        Set<GenreDto> set1 = genreSetToGenreDtoSet( book.getGenres() );
        if ( set1 != null ) {
            bookDto.setGenres( set1 );
        }

        return bookDto;
    }

    @Override
    public void updateEntityFromDto(BookDto dto, Book entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getPublicationDate() != null ) {
            entity.setPublicationDate( dto.getPublicationDate() );
        }
        if ( dto.getAverageRating() != null ) {
            entity.setAverageRating( dto.getAverageRating() );
        }
        if ( dto.getPublishingHouse() != null ) {
            if ( entity.getPublishingHouse() == null ) {
                entity.setPublishingHouse( PublishingHouse.builder().build() );
            }
            publishingHouseDtoToPublishingHouse1( dto.getPublishingHouse(), entity.getPublishingHouse() );
        }
        if ( entity.getAuthors() != null ) {
            Set<Author> set = authorDtoSetToAuthorSet( dto.getAuthors() );
            if ( set != null ) {
                entity.getAuthors().clear();
                entity.getAuthors().addAll( set );
            }
        }
        else {
            Set<Author> set = authorDtoSetToAuthorSet( dto.getAuthors() );
            if ( set != null ) {
                entity.setAuthors( set );
            }
        }
        if ( entity.getGenres() != null ) {
            Set<Genre> set1 = genreDtoSetToGenreSet( dto.getGenres() );
            if ( set1 != null ) {
                entity.getGenres().clear();
                entity.getGenres().addAll( set1 );
            }
        }
        else {
            Set<Genre> set1 = genreDtoSetToGenreSet( dto.getGenres() );
            if ( set1 != null ) {
                entity.setGenres( set1 );
            }
        }
    }

    protected PublishingHouse publishingHouseDtoToPublishingHouse(PublishingHouseDto publishingHouseDto) {
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

    protected Author authorDtoToAuthor(AuthorDto authorDto) {
        if ( authorDto == null ) {
            return null;
        }

        Author.AuthorBuilder author = Author.builder();

        if ( authorDto.getId() != null ) {
            author.id( authorDto.getId() );
        }
        if ( authorDto.getFirstName() != null ) {
            author.firstName( authorDto.getFirstName() );
        }
        if ( authorDto.getLastName() != null ) {
            author.lastName( authorDto.getLastName() );
        }
        if ( authorDto.getBio() != null ) {
            author.bio( authorDto.getBio() );
        }

        return author.build();
    }

    protected Set<Author> authorDtoSetToAuthorSet(Set<AuthorDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Author> set1 = new LinkedHashSet<Author>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorDto authorDto : set ) {
            set1.add( authorDtoToAuthor( authorDto ) );
        }

        return set1;
    }

    protected Genre genreDtoToGenre(GenreDto genreDto) {
        if ( genreDto == null ) {
            return null;
        }

        Genre.GenreBuilder genre = Genre.builder();

        if ( genreDto.getId() != null ) {
            genre.id( genreDto.getId() );
        }
        if ( genreDto.getName() != null ) {
            genre.name( genreDto.getName() );
        }
        if ( genreDto.getDescription() != null ) {
            genre.description( genreDto.getDescription() );
        }

        return genre.build();
    }

    protected Set<Genre> genreDtoSetToGenreSet(Set<GenreDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Genre> set1 = new LinkedHashSet<Genre>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( GenreDto genreDto : set ) {
            set1.add( genreDtoToGenre( genreDto ) );
        }

        return set1;
    }

    protected PublishingHouseDto publishingHouseToPublishingHouseDto(PublishingHouse publishingHouse) {
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

    protected AuthorDto authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        if ( author.getId() != null ) {
            authorDto.setId( author.getId() );
        }
        if ( author.getFirstName() != null ) {
            authorDto.setFirstName( author.getFirstName() );
        }
        if ( author.getLastName() != null ) {
            authorDto.setLastName( author.getLastName() );
        }
        if ( author.getBio() != null ) {
            authorDto.setBio( author.getBio() );
        }

        return authorDto;
    }

    protected Set<AuthorDto> authorSetToAuthorDtoSet(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorDto> set1 = new LinkedHashSet<AuthorDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Author author : set ) {
            set1.add( authorToAuthorDto( author ) );
        }

        return set1;
    }

    protected GenreDto genreToGenreDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        if ( genre.getId() != null ) {
            genreDto.setId( genre.getId() );
        }
        if ( genre.getName() != null ) {
            genreDto.setName( genre.getName() );
        }
        if ( genre.getDescription() != null ) {
            genreDto.setDescription( genre.getDescription() );
        }

        return genreDto;
    }

    protected Set<GenreDto> genreSetToGenreDtoSet(Set<Genre> set) {
        if ( set == null ) {
            return null;
        }

        Set<GenreDto> set1 = new LinkedHashSet<GenreDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Genre genre : set ) {
            set1.add( genreToGenreDto( genre ) );
        }

        return set1;
    }

    protected void publishingHouseDtoToPublishingHouse1(PublishingHouseDto publishingHouseDto, PublishingHouse mappingTarget) {
        if ( publishingHouseDto == null ) {
            return;
        }

        if ( publishingHouseDto.getId() != null ) {
            mappingTarget.setId( publishingHouseDto.getId() );
        }
        if ( publishingHouseDto.getName() != null ) {
            mappingTarget.setName( publishingHouseDto.getName() );
        }
        if ( publishingHouseDto.getAddress() != null ) {
            mappingTarget.setAddress( publishingHouseDto.getAddress() );
        }
        if ( publishingHouseDto.getContactNumber() != null ) {
            mappingTarget.setContactNumber( publishingHouseDto.getContactNumber() );
        }
    }
}
