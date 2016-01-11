package com.astraltear.embedded.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.astraltear.embedded.hsql.Book;
import com.astraltear.embedded.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookService bookService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Book> getBooks() {
		List<Book> books = bookService.getBooks();
		return books;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getBook(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		return String.format("결과값은 %s 입니다", String.valueOf(book));
	}
	
	@RequestMapping(value="/json/{id}", method=RequestMethod.GET)
	//@ResponseBody jackson을 사용할 때 ResponseBody 어노테이션을 사용하면 에러가 난다. Junit에서만 에러가 나는지는 확인이 필요함 
	public Book getBookJSON(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		return book;
	}
}
