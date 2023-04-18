package org.example.dto;

import lombok.Data;

import java.util.List;
@Data
public class Chapter {
  Long id;
  String title;
  List<Page> pages;
}
