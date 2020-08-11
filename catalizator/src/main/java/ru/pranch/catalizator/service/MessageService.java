package ru.pranch.catalizator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pranch.catalizator.domain.Message;
import ru.pranch.catalizator.repo.MessageRepo;

@Service
public class MessageService {
  private final MessageRepo messageRepo;

  @Autowired
  public MessageService(MessageRepo messageRepo) {
    this.messageRepo = messageRepo;
  }

  public Flux<Message> list() {
    return messageRepo.findAll();
  }

  public Mono<Message> addOne(Message message) {
    return messageRepo.save(message);

  }
}
