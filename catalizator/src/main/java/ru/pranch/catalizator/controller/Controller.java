package ru.pranch.catalizator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.pranch.catalizator.domain.Message;

@RestController
@RequestMapping("/controller")
public class Controller {
  @GetMapping
  public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                            @RequestParam(defaultValue = "3") Long count) {
    return Flux
      .just("Hello, reactive!",
        "One message",
        "Two message",
        "Three message",
        "Four message",
        "Five message")
      .skip(start)
      .take(count)
      .map(Message::new);
  }
}
