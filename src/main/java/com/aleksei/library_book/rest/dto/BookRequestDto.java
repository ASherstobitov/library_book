package com.aleksei.library_book.rest.dto;

import com.aleksei.library_book.entity.Author;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BookRequestDto {

    private final String name;

    private final String isbn;

    private final Integer year;

    private final List<Author> authors;
}
