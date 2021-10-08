package com.aleksei.library_book.service;

import com.aleksei.library_book.entity.Author;
import com.aleksei.library_book.entity.Book;
import com.aleksei.library_book.rest.dto.BookRequestDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BookService {
    Book createBook(BookRequestDto bookRequestDto);
    Book getBookById(Long bookId);
    Book updateBook(Long bookId, String name, String isbn, Integer year, List<Author> authors);
    Book deleteBook(Long bookId);
    List<Book> findAllByNameIsLikeOrAuthorNameIsLike(String bookName, String authorName, Sort sort);
}
