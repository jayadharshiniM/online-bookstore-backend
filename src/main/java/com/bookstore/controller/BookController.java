package com.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // REDUCE STOCK
    @PutMapping("/{id}/reduce")
    public String reduceStock(@PathVariable Long id, @RequestParam int qty) {
        Book book = repo.findById(id).orElse(null);
        if (book == null) return "Book not found";

        if (book.getAvailable() < qty) return "Not enough stock";

        book.setAvailable(book.getAvailable() - qty);
        repo.save(book);

        return "Stock updated";
    }
}
