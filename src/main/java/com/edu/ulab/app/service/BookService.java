package com.edu.ulab.app.service;


import com.edu.ulab.app.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto, Long userId);

    List<BookDto> getBooksByUserId(Long userId);

    void deleteBooksByUserId(Long userId);
}
