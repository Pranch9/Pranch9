package ru.pranch.catalizator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pranch.catalizator.domain.Message;
import ru.pranch.catalizator.service.MessageService;

@RestController
@RequestMapping("/controller")
public class Controller {
  private final MessageService messageService;

  @Autowired
  public Controller(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping
  public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                            @RequestParam(defaultValue = "3") Long count) {
    return messageService.list();
  }

  @PostMapping
  public Mono<Message> addOne(@RequestBody Message message) {
    return messageService.addOne(message);
  }
}
