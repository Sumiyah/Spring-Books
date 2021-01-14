package com.sumiyah.demo2.repositories;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sumiyah.demo2.models.Books;

@Repository
public interface BookRepository extends CrudRepository<Books, Long> {
	 // this method retrieves all the books from the database
    List<Books> findAll();
    // this method finds books with descriptions containing the search string
    List<Books> findByDescriptionContaining(String search);
    // this method counts how many titles contain a certain string
    Long countByTitleContaining(String search);
    // this method deletes a book that starts with a specific title
    Long deleteByTitleStartingWith(String search);
}
