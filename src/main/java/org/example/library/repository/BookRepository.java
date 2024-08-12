package org.example.library.repository;

import org.example.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {
}