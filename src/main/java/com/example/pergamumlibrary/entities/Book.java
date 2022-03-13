package com.example.pergamumlibrary.entities;

public class Book implements Comparable<Book> {
    private String author;
    private String title;
    private final Integer id;

    private static int nextId = 1;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.id = Book.nextId++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Book o) {

        if(this.author.compareTo(o.author) < 0){
            return -1;
        }
        else if(this.author.compareTo(o.author) > 0){
            return 1;
        }
        else{   //when is the same author
            return Integer.compare(this.title.compareTo(o.title), 0);
        }
    }
}
