package org.example.dto;

import lombok.Data;

@Data
public class Action {
  String content;
  Type type;
  Integer points;

  public enum Type {
    DAMAGE, HEAL
  }
}
