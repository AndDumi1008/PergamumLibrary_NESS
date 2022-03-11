package com.example.pergamumlibrary.controller;

import com.example.pergamumlibrary.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/library")
public class Library {

    List<Book> listOfBooks = new ArrayList<>();
    Book temp = null;



    @RequestMapping("/books")
    public List<Book> getAllBooks(){

        listOfBooks.sort(Comparator.naturalOrder());

        return listOfBooks;
    }

    @RequestMapping("/add")
    public String addBook(){
        listOfBooks.add(new Book("B andrei carte", "andrei"));
        listOfBooks.add(new Book("A andrei carte", "andrei"));
        listOfBooks.add(new Book("A andrei carte", "bianca"));

        getAllBooks();
        return "here should add books";
    }

    @RequestMapping("/{id}/delete")
    public String deleteBook(@PathVariable final Integer id){
        if(searchBook(id)) {
            listOfBooks.remove(temp);
        }
        getAllBooks();
        return "here should delete books";
    }

    @RequestMapping("/search/title={title}")
    public Boolean searchTitle(@PathVariable final String title){
        Optional<Book> bookFound = findByTitle(listOfBooks, title);

        if(bookFound.isPresent()) {
            temp = bookFound.get();
            return true;
        }
        return false;
    }


    @RequestMapping("/search/author={author}")
    public Boolean searchAuthor(@PathVariable final String author){
        Optional<Book> bookFound = findByAuthor(listOfBooks, author);

        if(bookFound.isPresent()) {
            temp = bookFound.get();
            return true;
        }

        return false;
    }

    private Optional<Book> findByAuthor(List<Book> listOfBooks, String author) {
        return listOfBooks.stream().filter(c -> c.getAuthor().equals(author)).findAny();
    }
    private Optional<Book> findByTitle(List<Book> listOfBooks, String title) {
        return listOfBooks.stream().filter(c -> c.getTitle().equals(title)).findAny();
    }

    @RequestMapping("/search/id={id}")
    public Boolean searchBook(@PathVariable Integer id){
        Optional<Book> bookFound = findById(listOfBooks, id);

        if(bookFound.isPresent()) {
            temp = bookFound.get();
            return true;
        }

        return false;
    }

    private Optional<Book> findById(List<Book> listOfBooks, Integer id) {
        return listOfBooks.stream().filter(c -> c.getId().equals(id)).findAny();
    }


    @RequestMapping("/{id}/update/author={author}")
    public String updateBook(@PathVariable final Integer id,@PathVariable final String author){

        if(searchBook(id)) {
            temp.setAuthor(author);
            return "Author was changed... \nTitle: " + temp.getTitle() + "\nAuthor: " + temp.getAuthor();
        }

        getAllBooks();
        return "here should update books";
    }
}
