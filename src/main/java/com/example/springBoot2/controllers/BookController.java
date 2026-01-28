package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Album;
import com.example.springBoot2.models.Book;
import com.example.springBoot2.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks(){
        return  bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id){
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PostMapping("/addBulk")
    public List<Book> addBooks(@RequestBody List<Book> books) {
        return bookRepository.saveAll(books);
    }



    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id , @RequestBody Book book){
        Book book1 = bookRepository.findById(id).orElse(null);
        book1.setName(book.getName());
        book1.setAuthor(book.getAuthor());
        book1.setPages(book.getPages());
        book1.setYear(book.getYear());
        return bookRepository.save(book1);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
    }
}