package com.aleksei.library_book.repository;

import com.aleksei.library_book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
