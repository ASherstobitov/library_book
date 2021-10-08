package com.aleksei.library_book.repository;

import com.aleksei.library_book.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select distinct b from Book b join b.authors a where b.bookName like %:bookName%" +
            " or a.authorName like %:authorName%")
    List<Book> findAllByNameIsLikeOrAuthorNameIsLike(@Param("bookName") String bookName,
                                                     @Param("authorName") String authorName, Sort sort);
}
