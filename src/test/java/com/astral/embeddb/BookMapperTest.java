package com.astral.embeddb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.astraltear.embedded.hsql.AppConfig;
import com.astraltear.embedded.hsql.Book;
import com.astraltear.embedded.hsql.BookMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class BookMapperTest {

	private static final Logger log = LoggerFactory.getLogger(BookMapperTest.class);

	@Autowired
	BookMapper bookMapper;

	@Test
	public void testBookMapperCrud() {
		assertNotNull(bookMapper);
		List<Book> books = bookMapper.select();
		assertEquals(3, books.size());

		for (Book book : books) {
			log.debug("book :{}", book);
		}

		Book newBook = new Book(10L, "스칼라 프로그래밍", "케이 호스트만", "프로그래밍 언어", new Date());
		bookMapper.insert(newBook);
		books = bookMapper.select();
		assertEquals(4, books.size()); // 조회 결과 4건이면 테스트 통과
		
		Book selectedBook = bookMapper.selectByPrimaryKey(10L);
		log.info("i.selectedBook = {}", selectedBook);
		assertEquals(newBook, selectedBook); // 신규 등록한 책과 조회한 책이동일하면 테스트 통과
		
		newBook.setCreator("여자친구");
		bookMapper.updateByPrimaryKey(newBook);
		log.debug("new BOOK :{}", newBook);
		
		bookMapper.deleteByPrimaryKey(10L);
		selectedBook = bookMapper.selectByPrimaryKey(10L);
		log.debug("selectedBook: {}",selectedBook);
		

	}

}
