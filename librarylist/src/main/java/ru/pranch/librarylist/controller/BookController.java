package ru.pranch.librarylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pranch.librarylist.model.Book;
import ru.pranch.librarylist.model.User;
import ru.pranch.librarylist.repository.BookRepository;
import ru.pranch.librarylist.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping
    public String findAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book-update";
    }

    @PostMapping("/edit")
    public String updateBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/add")
    public String addBookForm(Book book) {
        return "book-add";
    }

    @PostMapping("/add")
    public String addBook(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String year) {
        Book book = new Book(title, author, year, user);
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/bookFilter")
    public String filterBook(@RequestParam String filter, Model model) {
        Iterable<Book> books;
        if (filter != null && !filter.isEmpty()) {
            books = bookRepository.findByTitle(filter);
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        return "book-list";
    }
}
