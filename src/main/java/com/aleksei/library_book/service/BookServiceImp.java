package com.aleksei.library_book.service;

import com.aleksei.library_book.entity.Author;
import com.aleksei.library_book.entity.Book;
import com.aleksei.library_book.exception.AuthorNotFoundException;
import com.aleksei.library_book.exception.BookNotFoundException;
import com.aleksei.library_book.repository.AuthorRepository;
import com.aleksei.library_book.repository.BookRepository;
import com.aleksei.library_book.rest.dto.BookRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Override
    public Book createBook(BookRequestDto bookRequestDto) {
        return bookRepository.save(new Book(bookRequestDto.getName(), bookRequestDto.getIsbn(),
                bookRequestDto.getYear(), bookRequestDto.getAuthors()));
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Unable to find the book by id: " + bookId));
    }

    @Override
    public Book updateBook(Long bookId, String name, String isbn, Integer year, List<Author> authors) {
        var book = getBookById(bookId);
        @NonNull
        List<Author> detachAuthors = book.getAuthors();
        if (!CollectionUtils.isEmpty(authors)) {
            authors.forEach(e -> {
                if (e.getAuthorId() != null &&
                        !authorRepository.existsById(e.getAuthorId())) {
                    throw new AuthorNotFoundException("Unable to find the author by id: " + e.getAuthorId());
                } else {
                    detachAuthors.add(e);
                } });
            detachAuthors.retainAll(authors);
        } else {
            detachAuthors.clear();
        }
        if (name != null) book.setBookName(name);
        if (isbn != null) book.setIsbn(isbn);
        if (year != null) book.setYear(year);
        return bookRepository.save(book);
    }

    @Override
    public Book deleteBook(Long bookId) {
        var book = getBookById(bookId);
        bookRepository.deleteById(bookId);
        return book;
    }

    @Override
    public List<Book> findAllByNameIsLikeOrAuthorNameIsLike(String bookName, String authorName,Sort sort) {
        return bookRepository.findAllByNameIsLikeOrAuthorNameIsLike(bookName, authorName, sort);
    }
}
