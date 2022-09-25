package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.BookMapperImpl;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Mapped book: {}", book);
        Book savedBook = bookRepository.save(book);
        log.info("Saved book: {}", savedBook);
        return bookMapper.bookToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long userId) {
        // реализовать недстающие методы
        BookDto updatedBook = createBook(bookDto);
        getBooksByUserId(userId).add(updatedBook);
        log.info("Updated book: {}", updatedBook);
        return updatedBook;
    }

    @Override
    public List<BookDto> getBooksByUserId(Long userId) {
        // реализовать недстающие методы
        List<BookDto> bookDtos = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .filter(book -> book.getUserId().equals(userId))
                .map(book -> bookMapper.bookToBookDto(book))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public void deleteBooksByUserId(Long userId) {
        // реализовать недстающие методы
        List<Long> booksIdForDeleting = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .filter(book -> book.getUserId().equals(userId))
                .map(Book::getId)
                .collect(Collectors.toList());
        bookRepository.deleteAllById(booksIdForDeleting);
        log.info("BooksID which will be deleted: {}", booksIdForDeleting);
    }
}
