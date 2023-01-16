package com.book.controller;

import com.book.dto.BookDTO;
import com.book.model.BookResponse;
import com.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
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
    public BookResponse getBookById(@PathVariable String id){
        return BookResponse.of(HttpStatus.OK.value(),bookService.getBookById(id));
    }
    @PostMapping
    public BookResponse createBook(@RequestBody BookDTO bookDTO){
        bookService.createBook(bookDTO);
        return BookResponse.of(HttpStatus.OK.value());
    }

    @GetMapping
    public BookResponse getAllBooks (){
        log.info("get list books");
        return BookResponse.of(HttpStatus.OK.value(), bookService.getBooks());
    }
    @PutMapping
    public BookResponse updateBook(@RequestBody BookDTO bookDTO){
        log.info("update book");
        bookService.updateBook(bookDTO);
        return BookResponse.of(HttpStatus.OK.value());
    }
    @DeleteMapping("/{id}")
    public BookResponse deleteBook(@PathVariable String id){
        log.info("delete book");
        bookService.deleteBook(id);
        return BookResponse.of(HttpStatus.OK.value());
    }

}
