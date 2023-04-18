package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Book {
  String author;
  String title;
  @JsonFormat(pattern="dd-MM-yyyy")
  Date publishedDate;
  String country;
  String city;
  String publisher;
  List<Chapter> chapters;
}
