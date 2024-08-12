package org.example.library.service;

import org.example.library.annotation.LogAnnotation;
import org.example.library.annotation.PerformanceAnnotation;
import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @LogAnnotation
    @PerformanceAnnotation
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @LogAnnotation
    @PerformanceAnnotation
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @LogAnnotation
    @PerformanceAnnotation
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @LogAnnotation
    @PerformanceAnnotation
    public Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }

    @LogAnnotation
    @PerformanceAnnotation
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}