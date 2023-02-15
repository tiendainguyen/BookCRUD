package com.book.controller;

import com.book.dto.BookDTO;
import com.book.model.AuthResponse;
import com.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
@Slf4j
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/{id}")
    public AuthResponse getBookById(@PathVariable String id){
        return AuthResponse.of(HttpStatus.OK.value(),bookService.getBookById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public AuthResponse createBook(@RequestBody BookDTO bookDTO){
        bookService.createBook(bookDTO);
        return AuthResponse.of(HttpStatus.OK.value());
    }

    @GetMapping
    public AuthResponse getAllBooks (){
        log.info("get list books");
        return AuthResponse.of(HttpStatus.OK.value(), bookService.getBooks());
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public AuthResponse updateBook(@RequestBody BookDTO bookDTO){
        log.info("update book");
        bookService.updateBook(bookDTO);
        return AuthResponse.of(HttpStatus.OK.value());
    }
    @PreAuthorize("hasAnyRole('AMIN')")
    @DeleteMapping("/{id}")
    public AuthResponse deleteBook(@PathVariable String id){
        log.info("delete book");
        bookService.deleteBook(id);
        return AuthResponse.of(HttpStatus.OK.value());
    }

}
