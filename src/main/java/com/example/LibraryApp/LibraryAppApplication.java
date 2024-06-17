package com.example.LibraryApp;

import com.example.LibraryApp.entity.*;
import com.example.LibraryApp.mapper.UserMapper;
import com.example.LibraryApp.repository.*;
import com.example.LibraryApp.service.UserService;
import com.example.LibraryApp.service.impl.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching
public class LibraryAppApplication implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final ReviewRepository reviewRepository;
	private final BookRepository bookRepository;
	private final GenreRepository genreRepository;
	private final PublishingHouseRepository publishingHouseRepository;
	private final RoleRepository roleRepository;
	private final UserService userService;
	private final UserMapper userMapper;
	private final ReviewServiceImpl reviewService;


	public static void main(String[] args) {
		SpringApplication.run(LibraryAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// roles
		roleRepository.save(Role.builder().name("ADMIN").build());
		roleRepository.save(Role.builder().name("USER").build());

		// users
		Set<Role> roles1 = new HashSet<>();
		roles1.add(roleRepository.findById(1L).get());

		Set<Role> roles2 = new HashSet<>();
		roles2.add(roleRepository.findById(2L).get());

		User user1 = User.builder().firstName("Amin").lastName("Aliyev").username("amin03")
				.password(("12345678").toCharArray()).roles(roles1).build();
		userService.saveUser(userMapper.entityToRequest(user1));
		User user2 = User.builder().firstName("Ali").lastName("Aliyev").username("ali02")
				.password(("123456789").toCharArray()).roles(roles2).build();
		userService.saveUser(userMapper.entityToRequest(user2));

		// publishingHouse
		publishingHouseRepository.save(PublishingHouse.builder().name("Publisher A")
				.address("123 Main St").contactNumber("555-1234").build());

		// genres
        genreRepository.save(Genre.builder().name("Fiction").description("Fictional genre").build());
		genreRepository.save(Genre.builder().name("Fantasy").description("Fantasy genre").build());

		// authors
		authorRepository.save(Author.builder().firstName("John").lastName("Doe")
				.bio("John Doe is a prolific writer with numerous bestsellers.").build());
		authorRepository.save(Author.builder().firstName("Jane").lastName("Smith")
				.bio("Jane Smith is an award-winning novelist.").build());

		// books
		Set<Author> authors1 = new HashSet<>();
		authors1.add(authorRepository.findByIdAndIsEnabledTrue(1L).get());

		PublishingHouse publishingHouse = publishingHouseRepository.findByIdAndIsEnabledTrue(1L).get();

		Set<Genre> genres1 = new HashSet<>();
		genres1.add(genreRepository.findByIdAndIsEnabledTrue(1L).get());

		bookRepository.save(Book.builder().title("The Great Gatsby").price(new BigDecimal("19.99"))
				.publicationDate(ZonedDateTime.now().minusYears(5)).publishingHouse(publishingHouse)
				.authors(authors1).genres(genres1).build());


		Set<Author> authors2 = new HashSet<>();
		authors2.add(authorRepository.findByIdAndIsEnabledTrue(2L).get());

		Set<Genre> genres2 = new HashSet<>();
		genres2.add(genreRepository.findByIdAndIsEnabledTrue(2L).get());

		bookRepository.save(Book.builder().title("To Kill a Mockingbird")
				.price(new BigDecimal("15.50")).publicationDate(ZonedDateTime.now().minusYears(10))
				.publishingHouse(publishingHouse).authors(authors2).genres(genres2).build());

		// reviews
		Book book1 = bookRepository.findByIdAndIsEnabledTrue(1L).get();

		reviewRepository.save(Review.builder().rating(new BigDecimal("4"))
				.comment("Great book, enjoyed every chapter!").book(book1)
				.build());

		reviewRepository.save(Review.builder().rating(new BigDecimal("3"))
				.comment("Interesting read, although a bit slow at times.")
				.book(book1).build());

		Book book2 = bookRepository.findByIdAndIsEnabledTrue(2L).get();

		reviewRepository.save(Review.builder().rating(new BigDecimal("5"))
				.comment("Absolutely loved it, couldn't put it down!")
				.book(book2).build());

		reviewRepository.save(Review.builder().rating(new BigDecimal("2"))
				.comment("Not my cup of tea, too predictable.")
				.book(book2).build());

	}
}
