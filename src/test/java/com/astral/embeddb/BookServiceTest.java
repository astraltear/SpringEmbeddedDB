package com.astral.embeddb;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.astraltear.embedded.hsql.AppConfig;
import com.astraltear.embedded.hsql.Book;
import com.astraltear.embedded.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BookServiceTest.class);

	@Autowired
	BookService bookService;
	
	
	@Test
	public void testAGetBooks() {
		
		List<Book> books = bookService.getBooks();
		for (Book book : books) {
			log.debug("books :: {}",book);
		}
	}
	
	@Test
	public void testBGetBook() {
		Book selectedBook = bookService.getBook(1L);
		log.debug("book ::>> {}", selectedBook);
	}
	
	@Test
	public void testCCreateBook() {
		Book newBook = new Book(100L,"scala programming","key hoffman", "programming language", new Date());
		bookService.createBook(newBook);
	}
	
	@Test
	public void testDUpdateBook() {
		Book selectedBook = bookService.getBook(1L);
		log.debug("test select update::>>>> {}",selectedBook);
	}
	
	@Test
	public void testEDeleteBook() {
		bookService.deleteBook(100L);
	}

}
