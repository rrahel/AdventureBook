package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Book;
import org.example.dto.Page;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.Main.print;

public class BookService {
  ObjectMapper om = new ObjectMapper();

  public List<Book> getListOfBooks() {

    return Stream.of(Objects.requireNonNull(new File("src/main/resources/").listFiles()))
      .filter(file -> !file.isDirectory()).map(file -> {
        try {
          return om.readValue(file, Book.class);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }).collect(Collectors.toList());
  }

  public void displayBookInfo(Book book) {
    print("You chose: " + book.getTitle());
    print("By " + book.getAuthor());
    print("Published in " + book.getCountry() + ", " + book.getCity());
    print("on " + book.getPublishedDate());
  }

  public void startGame(Book book) {
    for (int i = 0; i < book.getChapters().size(); i++) {
      for (int j = 0; j < book.getChapters().get(i).getPages().size(); j++) {
        Page currentPage = book.getChapters().get(i).getPages().get(j);
        print(currentPage.getContent());
        if(currentPage.getGoTo() != null){
          i = Math.toIntExact(currentPage.getGoTo()-2);
          break;
        }
      }
    }
  }
}
