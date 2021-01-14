package com.sumiyah.demo2.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumiyah.demo2.models.Books;
import com.sumiyah.demo2.services.BookService;

@RestController
public class BooksApi {

	 private final BookService bookService;
	    public BooksApi(BookService bookService){
	        this.bookService = bookService;
	    }
	    @RequestMapping("/api/books")
	    public List<Books> index() {
	        return bookService.allBooks();
	    }
	    
	    @RequestMapping(value="/api/books", method=RequestMethod.POST)
	    public Books create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
	        Books book = new Books(title, desc, lang, numOfPages);
	        return bookService.createBook(book);
	    }
	    
	    @RequestMapping("/api/books/{id}")
	    public Books show(@PathVariable("id") Long id) {
	        Books book = bookService.findBook(id);
	        return book;
	    }
	    
	    @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	    public Books update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
	        Books book = bookService.updateBook(id, title, desc, lang, numOfPages);
//	    	Books book = bookService.findBook(id);
////	    	bookService.createBook(book);
//	        bookService.changeBook(book);
	        return book;
	    }
	    
	    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
	    public void destroy(@PathVariable("id") Long id) {
	        bookService.deleteBook(id);
	    }
	    
	    
}
