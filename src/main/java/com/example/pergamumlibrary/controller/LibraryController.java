package com.example.pergamumlibrary.controller;

import com.example.pergamumlibrary.entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    List<Book> listOfBooks = new ArrayList<>();
    Book temp = null;

    //Help search methods to filter elements
    private Optional<Book> findByAuthor(List<Book> listOfBooks, String author) {
        return listOfBooks.stream().filter(c -> c.getAuthor().equals(author)).findAny();
    }
    private Optional<Book> findByTitle(List<Book> listOfBooks, String title) {
        return listOfBooks.stream().filter(c -> c.getTitle().equals(title)).findAny();
    }
    private Optional<Book> findById(List<Book> listOfBooks, Integer id) {
        return listOfBooks.stream().filter(c -> c.getId().equals(id)).findAny();
    }


//    @RequestMapping("/add")
//    public String addBook(){  //Testing purpose
//        listOfBooks.add(new Book("carte A - autor A", "autor A"));
//        listOfBooks.add(new Book("carte B - autor B", "autor A"));
//        listOfBooks.add(new Book("carte A - autor B", "autor B"));
//
//        getAllBooks();
//        return "here should add books";
//    }

    @PostMapping("")
    public void addBook(@RequestBody final Book book) { //Add new book
        listOfBooks.add(book);

    }

    @RequestMapping("/books")
    public List<Book> getAllBooks() { //Return the list of books

        listOfBooks.sort(Comparator.naturalOrder());

        return listOfBooks;
    }

//    @RequestMapping("/{id}/delete")
//    public String deleteBook(@PathVariable final Integer id) { //using GET Method to delete a book
//        if(searchBook(id)) {
//            listOfBooks.remove(temp);
//        }
//        getAllBooks();
//        return "here should delete books";
//    }

    @DeleteMapping("")
    public void deleteBook(@RequestBody final Book book) { // Using DEL Method to delete a book (using body)
        if(searchBook(book.getId())) {
            listOfBooks.remove(temp);
        }
        getAllBooks();
    }

    @RequestMapping("/search/title={title}")
    public Boolean searchTitle(@PathVariable final String title) {
        Optional<Book> bookFound = findByTitle(listOfBooks, title);

        if(bookFound.isPresent()) {
            temp = bookFound.get();
            return true;
        }
        return false;
    }

    @RequestMapping("/search/author={author}")
    public Boolean searchAuthor(@PathVariable final String author) {
        Optional<Book> bookFound = findByAuthor(listOfBooks, author);

        if(bookFound.isPresent()) {
            temp = bookFound.get();
            return true;
        }

        return false;
    }

    @RequestMapping("/search/id={id}")
    public Boolean searchBook(@PathVariable final Integer id) {
        Optional<Book> bookFound = findById(listOfBooks, id);

        if(bookFound.isPresent()) {
            temp = bookFound.get();
            return true;
        }

        return false;
    }

//    @RequestMapping("/{id}/update/author={author}")
//    public String updateBook(@PathVariable final Integer id,@PathVariable final String author) { // Update author of a book found by id with GET Method. Could also be in PUT format
//
//        if(searchBook(id)) {
//            temp.setAuthor(author);
//            return "Author was changed... \nTitle: " + temp.getTitle() + "\nAuthor: " + temp.getAuthor();
//        }
//
//        getAllBooks();
//        return "here should update books";
//    }

    @PatchMapping("/{id}/")
    public void updateBook(@PathVariable final Integer id,@RequestBody final Book book) { // Update author of a book found by id with PATCH Method.

        if(searchBook(id)) {
            temp.setAuthor(book.getAuthor());
        }
        getAllBooks();
    }
}
