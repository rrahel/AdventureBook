package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {
  Long id;
  String title;
  Type type;
  Section section;
  Action action;

  public enum Type {
    BEGIN,
    STORY,
    END
  }
}
