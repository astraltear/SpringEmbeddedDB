package com.astral.embeddb;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.astraltear.embedded.hsql.AppConfig;
import com.astraltear.embedded.hsql.Book;
import com.astraltear.embedded.hsql.BookMapper;
import com.astraltear.embedded.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class BookServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BookServiceTest.class);

	@Autowired
	BookService bookService;
	
	@Autowired
	BookMapper bookMapper;
	
	@Test
	public void testGetBook() {
		
		List<Book> books = bookService.getBooks();
		for (Book book : books) {
			log.debug("book :: {}",book);
		}
		
//		List<Book> books = bookMapper.select();
	}

}
