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
    
    @RequestMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Books book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "edit.jsp";
    }
    
    @RequestMapping(value="/books/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("book") Books book, BindingResult result) {
        if (result.hasErrors()) {
            return "edit.jsp";
        } else {
            bookService.changeBook(book);
            return "redirect:/books";
        }
    }
    
    @RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
