package org.example.library.service;

import org.example.library.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublishedDate(LocalDate.now());
        book = bookService.createBook(book);

        Book foundBook = bookService.getBookById(book.getId()).orElse(null);
        assertNotNull(foundBook);
        assertEquals(book.getId(), foundBook.getId());
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("New Author");
        book.setPublishedDate(LocalDate.now());

        Book createdBook = bookService.createBook(book);
        assertNotNull(createdBook);
        assertEquals(book.getTitle(), createdBook.getTitle());
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setTitle("Old Book");
        book.setAuthor("Old Author");
        book.setPublishedDate(LocalDate.now());
        book = bookService.createBook(book);

        book.setTitle("Updated Book");
        Book updatedBook = bookService.updateBook(book.getId(), book);
        assertNotNull(updatedBook);
        assertEquals("Updated Book", updatedBook.getTitle());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setTitle("Delete Book");
        book.setAuthor("Delete Author");
        book.setPublishedDate(LocalDate.now());
        book = bookService.createBook(book);

        bookService.deleteBook(book.getId());
        assertFalse(bookService.getBookById(book.getId()).isPresent());
    }
}