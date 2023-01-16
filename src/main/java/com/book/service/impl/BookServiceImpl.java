package com.book.service.impl;

import com.book.dto.BookDTO;
import com.book.entity.Book;
import com.book.exception.NotFoundException;
import com.book.model.BookResponse;
import com.book.repository.BookRepository;
import com.book.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO getBookById(String id) {
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isEmpty()) throw new NotFoundException();
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(optional.get(),bookDTO);
        return bookDTO;
    }

    @Override
    public void createBook(BookDTO bookDTO) {
        Book book =  new Book();
        BeanUtils.copyProperties(bookDTO,book);
        bookRepository.save(book);
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(book.getId(),book.getPublicationDate(),book.getTitle(),book.getDescription(),book.getAuthor(), book.getNumberOfPages()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateBook(BookDTO bookDTO) {
        Optional<Book> optional = bookRepository.findById(bookDTO.getId());
        if(optional.isEmpty()) throw new NotFoundException();
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isEmpty()) throw new NotFoundException();
        bookRepository.delete(optional.get());
    }
}
