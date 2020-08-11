package ru.pranch.catalizator.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.pranch.catalizator.domain.Message;

public interface MessageRepo extends ReactiveCrudRepository<Message, Long> {

}
