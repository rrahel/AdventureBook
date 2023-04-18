package org.example.dto;

import lombok.Data;

@Data
public class Section {
  String content;
  Type type;
  Integer[] goTo;

  enum Type {
    DAMAGE,
    HEAL
  }
}
