package com.example.LibraryApp.mapper;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.BookDto;
import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.PublishingHouseDto;
import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.ReviewRequest;
import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.entity.Book;
import com.example.LibraryApp.entity.Genre;
import com.example.LibraryApp.entity.PublishingHouse;
import com.example.LibraryApp.entity.Review;
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
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review requestToEntity(ReviewRequest reviewRequest) {
        if ( reviewRequest == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        if ( reviewRequest.getRating() != null ) {
            review.rating( reviewRequest.getRating() );
        }
        if ( reviewRequest.getComment() != null ) {
            review.comment( reviewRequest.getComment() );
        }
        if ( reviewRequest.getBook() != null ) {
            review.book( bookDtoToBook( reviewRequest.getBook() ) );
        }

        return review.build();
    }

    @Override
    public ReviewDto entityToDto(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        if ( review.getUpdatedAt() != null ) {
            reviewDto.setLastUpdateDate( review.getUpdatedAt() );
        }
        if ( review.getId() != null ) {
            reviewDto.setId( review.getId() );
        }
        if ( review.getRating() != null ) {
            reviewDto.setRating( review.getRating() );
        }
        if ( review.getComment() != null ) {
            reviewDto.setComment( review.getComment() );
        }
        if ( review.getBook() != null ) {
            reviewDto.setBook( bookToBookDto( review.getBook() ) );
        }

        return reviewDto;
    }

    @Override
    public void updateEntityFromDto(ReviewDto dto, Review entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getRating() != null ) {
            entity.setRating( dto.getRating() );
        }
        if ( dto.getComment() != null ) {
            entity.setComment( dto.getComment() );
        }
        if ( dto.getBook() != null ) {
            if ( entity.getBook() == null ) {
                entity.setBook( Book.builder().build() );
            }
            bookDtoToBook1( dto.getBook(), entity.getBook() );
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

    protected Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        if ( bookDto.getId() != null ) {
            book.id( bookDto.getId() );
        }
        if ( bookDto.getTitle() != null ) {
            book.title( bookDto.getTitle() );
        }
        if ( bookDto.getPrice() != null ) {
            book.price( bookDto.getPrice() );
        }
        if ( bookDto.getPublicationDate() != null ) {
            book.publicationDate( bookDto.getPublicationDate() );
        }
        if ( bookDto.getAverageRating() != null ) {
            book.averageRating( bookDto.getAverageRating() );
        }
        if ( bookDto.getPublishingHouse() != null ) {
            book.publishingHouse( publishingHouseDtoToPublishingHouse( bookDto.getPublishingHouse() ) );
        }
        Set<Author> set = authorDtoSetToAuthorSet( bookDto.getAuthors() );
        if ( set != null ) {
            book.authors( set );
        }
        Set<Genre> set1 = genreDtoSetToGenreSet( bookDto.getGenres() );
        if ( set1 != null ) {
            book.genres( set1 );
        }

        return book.build();
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

    protected BookDto bookToBookDto(Book book) {
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

        return bookDto;
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

    protected void bookDtoToBook1(BookDto bookDto, Book mappingTarget) {
        if ( bookDto == null ) {
            return;
        }

        if ( bookDto.getId() != null ) {
            mappingTarget.setId( bookDto.getId() );
        }
        if ( bookDto.getTitle() != null ) {
            mappingTarget.setTitle( bookDto.getTitle() );
        }
        if ( bookDto.getPrice() != null ) {
            mappingTarget.setPrice( bookDto.getPrice() );
        }
        if ( bookDto.getPublicationDate() != null ) {
            mappingTarget.setPublicationDate( bookDto.getPublicationDate() );
        }
        if ( bookDto.getAverageRating() != null ) {
            mappingTarget.setAverageRating( bookDto.getAverageRating() );
        }
        if ( bookDto.getPublishingHouse() != null ) {
            if ( mappingTarget.getPublishingHouse() == null ) {
                mappingTarget.setPublishingHouse( PublishingHouse.builder().build() );
            }
            publishingHouseDtoToPublishingHouse1( bookDto.getPublishingHouse(), mappingTarget.getPublishingHouse() );
        }
        if ( mappingTarget.getAuthors() != null ) {
            Set<Author> set = authorDtoSetToAuthorSet( bookDto.getAuthors() );
            if ( set != null ) {
                mappingTarget.getAuthors().clear();
                mappingTarget.getAuthors().addAll( set );
            }
        }
        else {
            Set<Author> set = authorDtoSetToAuthorSet( bookDto.getAuthors() );
            if ( set != null ) {
                mappingTarget.setAuthors( set );
            }
        }
        if ( mappingTarget.getGenres() != null ) {
            Set<Genre> set1 = genreDtoSetToGenreSet( bookDto.getGenres() );
            if ( set1 != null ) {
                mappingTarget.getGenres().clear();
                mappingTarget.getGenres().addAll( set1 );
            }
        }
        else {
            Set<Genre> set1 = genreDtoSetToGenreSet( bookDto.getGenres() );
            if ( set1 != null ) {
                mappingTarget.setGenres( set1 );
            }
        }
    }
}
