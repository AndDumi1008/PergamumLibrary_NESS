package com.example.pergamumlibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PergamumLibraryApplicationTests {

    @BeforeEach
    void setUp() {
        Book book1 = new Book("un titlu1", "un autor1");
        Book book2 = new Book("un titlu12", "un autor1");
        Book book3 = new Book("un titlu13-22", "un autor1");
        Book book4 = new Book("un titlu2", "un autor2");
        Book book5 = new Book("un titlu3", "un autor3");
    }

    @Test
    void contextLoads() {


        
    }

}
