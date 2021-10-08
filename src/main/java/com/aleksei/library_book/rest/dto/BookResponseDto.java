package com.aleksei.library_book.rest.dto;


import com.aleksei.library_book.entity.Author;
import com.aleksei.library_book.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BookResponseDto {

    private final Long id;

    private final String name;

    private final String isbn;

    private final Integer year;

    private final List<Author> authors;

    public BookResponseDto(Book book) {
        this.id = book.getBookId();
        this.name = book.getBookName();
        this.isbn = book.getIsbn();
        this.year = book.getYear();
        this.authors = book.getAuthors();
    }
}
