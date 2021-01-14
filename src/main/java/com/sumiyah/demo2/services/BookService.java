package com.sumiyah.demo2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sumiyah.demo2.models.Books;
import com.sumiyah.demo2.repositories.BookRepository;

@Service
public class BookService {
	 // adding the book repository as a dependency
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // returns all the books
    public List<Books> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Books createBook(Books b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Books findBook(Long id) {
        Optional<Books> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    
    public void deleteBook(Long id) {
    	Optional<Books> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
        	bookRepository.deleteById(id);  
        } 
    }
	public Books updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		// TODO Auto-generated method stub
		Optional<Books> optionalBook = bookRepository.findById(id);
		 if(optionalBook.isPresent()) {
			 optionalBook.map(target -> {
             target.setTitle(title);
             target.setDescription(desc);
             target.setLanguage(lang);
             target.setNumberOfPages(numOfPages);
             bookRepository.save(target);

             return target; 
             	});
        return optionalBook.get();
	        } else {
	            return null;
	        }
	}
	
	public Books changeBook(Books b) {
		return bookRepository.save(b);
	}
    
}
