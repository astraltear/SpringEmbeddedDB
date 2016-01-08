package com.astral.embeddb;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.astraltear.embedded.hsql.AppConfig;
import com.astraltear.embedded.hsql.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class EmbeddedDB_HSQLTest {
	
	private static final Logger log = LoggerFactory.getLogger(EmbeddedDB_HSQLTest.class);

	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getBean("dataSource");
		
		Book book = new Book(3L, "title", "create", "type", new Date());
		
		log.debug(book.toString());
	}

}
