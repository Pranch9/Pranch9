package ru.pranch.librarylist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pranch.librarylist.repository.BookRepository;
import ru.pranch.librarylist.repository.UserRepository;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/")
    public String login(Model model) {
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main(Model model) {
        Long userCounter = userRepository.count();
        Long bookCounter = bookRepository.count();
        model.addAttribute("userCounter", userCounter);
        model.addAttribute("bookCounter", bookCounter);
        return "main";
    }

}
