package com.book.service;

import com.book.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO getBookById(String id);
    void createBook(BookDTO bookDTO);
    List<BookDTO> getBooks();
    void updateBook(BookDTO bookDTO);
    void deleteBook(String id);
}
