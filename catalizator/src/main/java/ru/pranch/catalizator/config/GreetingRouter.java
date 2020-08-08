package ru.pranch.catalizator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import ru.pranch.catalizator.handlers.GreetingHandler;

@Configuration
public class GreetingRouter {
  @Bean
  public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

    RequestPredicate route = RequestPredicates
      .GET("/hello")
      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

    return RouterFunctions
      .route(route, greetingHandler::hello)
      .andRoute(RequestPredicates
        .GET("/"), greetingHandler::index);
  }
}
