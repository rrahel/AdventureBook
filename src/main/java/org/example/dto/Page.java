package org.example.dto;

import lombok.Data;

@Data
public class Page {
  Long id;
  String picture;
  String content;
  String footnote;
  Long goTo;
}
