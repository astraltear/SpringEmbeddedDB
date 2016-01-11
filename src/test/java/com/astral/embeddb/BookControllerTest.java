package com.astral.embeddb;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.astraltear.embedded.controller.BookController;
import com.astraltear.embedded.hsql.AppConfig;
import com.astraltear.embedded.hsql.Book;
import com.astraltear.embedded.hsql.RestAppConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {AppConfig.class,RestAppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(BookControllerTest.class);
	
	private MockMvc mockMvc;
	
	@Autowired
	BookController bookController;

	@Before
	public void setUp() throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).addFilter(filter, "").build();
	}

	@Test
	public void testA_Book() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/1").accept(MediaType.valueOf("text/plain;charset=UTF-8"));
		this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testB_Books() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/list").accept(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder).andDo(print());
	}
	
	@Test
	public void testC_BookJson() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/json/3").accept(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder).andDo(print());
	}
	
	@Test
	public void testD_JavaTOJson() throws JsonProcessingException {
		Book booktoJson  = new Book(40L, "어시스의 마법사", "어슐러", "판타지소설", new Date());
		ObjectMapper mapper = new ObjectMapper();
		String contents = mapper.writeValueAsString(booktoJson);
		log.debug("content ::: {}",contents);
	}
	

}
