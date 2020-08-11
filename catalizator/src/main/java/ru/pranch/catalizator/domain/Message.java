package ru.pranch.catalizator.domain;

import org.springframework.data.annotation.Id;

public class Message {
  @Id
  private Long id;
  private String data;

  public Message() {
  }

  public Message(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
