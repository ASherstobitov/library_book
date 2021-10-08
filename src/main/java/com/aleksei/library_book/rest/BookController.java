package com.aleksei.library_book.rest;

import com.aleksei.library_book.rest.dto.BookRequestDto;
import com.aleksei.library_book.rest.dto.BookResponseDto;
import com.aleksei.library_book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/library/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponseDto createBook(@RequestBody BookRequestDto book) {
        return new BookResponseDto(bookService.createBook(book));
    }

    @GetMapping("/{bookId}")
    public BookResponseDto getBook(@PathVariable Long bookId) {
        return new BookResponseDto(bookService.getBookById(bookId));
    }

    @PutMapping("/{bookId}")
    public BookResponseDto updateBook(@PathVariable Long bookId,
                                      @RequestBody BookRequestDto book) {
        return new BookResponseDto(bookService.updateBook(bookId, book.getName(),
             book.getIsbn(), book.getYear(), book.getAuthors()));
    }

    @DeleteMapping("/{bookId}")
    public BookResponseDto deleteBook(@PathVariable Long bookId) {
        return new BookResponseDto(bookService.deleteBook(bookId));
    }

    @GetMapping("/search")
    public List<BookResponseDto> getBooksByNameAndAuthorName(
            @RequestParam(value = "bookName", required = false) String bookName,
            @RequestParam(value = "authorName", required = false) String authorName,
            Sort sort) {
        return bookService.findAllByNameIsLikeOrAuthorNameIsLike(bookName, authorName, sort)
                .stream().map(BookResponseDto::new)
                .collect(toList());
    }

}
