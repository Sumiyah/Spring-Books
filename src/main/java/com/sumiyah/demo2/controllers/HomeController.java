package com.sumiyah.demo2.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumiyah.demo2.models.Books;
import com.sumiyah.demo2.services.BookService;

@Controller
public class HomeController {

	private final BookService bookService;

	public HomeController(BookService bookService) {
	        this.bookService = bookService;
	    }

	@GetMapping("/books")
//	@RequestMapping("/books")
    public String index(Model model) {
        List<Books> books = bookService.allBooks();
        model.addAttribute("books", books);
		return "index.jsp";
	}
	
	@RequestMapping("/books/new")
    public String newBook(@ModelAttribute("book") Books book) {
        return "new.jsp";
    }
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("book") Books book, BindingResult result) {
        if (result.hasErrors()) {
            return "new.jsp";
        } else {
            bookService.createBook(book);
            return "redirect:/books";
        }
    }

    
    @RequestMapping("/books/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        
		Books books =  bookService.findBook(id);
        model.addAttribute("book", books);
		return "show.jsp";
	}
}
