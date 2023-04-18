package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Action;
import org.example.dto.Book;
import org.example.dto.Chapter;
import org.example.dto.User;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
    print("");
    print("You chose: " + book.getTitle());
    print("By " + book.getAuthor());
    print("Published in " + book.getCountry() + ", " + book.getCity());
    print("on " + book.getPublishedDate());
    print("");
  }

  public void startGame(Book book, User user) {
    Optional<Chapter> firstChapter = book.getChapters().stream().filter(chapter -> chapter.getType() == Chapter.Type.BEGIN).findFirst();
    if (firstChapter.isPresent()) {
      gameLoop(book, firstChapter.get(), user);
    } else {
      throw new IllegalArgumentException("Book has no beginning");
    }

  }

  private void gameLoop(Book book, Chapter chapter, User user) {
    Optional<Chapter> chosenChapter = Optional.empty();

    print(chapter.getTitle());

    checkActions(chapter, user);

    int goTo = checkSection(chapter);
    if (goTo > -1) {
      chosenChapter = book.getChapters().stream().filter(c -> c.getId() == goTo).findFirst();
    }

    if (chosenChapter.isPresent()) {
      if (chosenChapter.get().getType() != Chapter.Type.END)
        gameLoop(book, chosenChapter.get(), user);
      else {
        print(chosenChapter.get().getTitle());
        print("");
        print("You have reached the end!!!");
      }
    } else {
      throw new IllegalArgumentException("Chapter is missing");
    }
  }

  private int checkSection(Chapter chapter) {
    if (chapter.getSection().getGoTo() != null) {
      Scanner sc = new Scanner(System.in);
      print("");
      print("Choose where to go next. Available options are" + Arrays.toString(chapter.getSection().getGoTo()));
      return sc.nextInt();
    }
    return -1;
  }

  private void checkActions(Chapter chapter, User user) {
    if (chapter.getAction() != null) {
      print("!!!!!");
      print(chapter.getAction().getContent());

      if (chapter.getAction().getType() == Action.Type.DAMAGE)
        user.setHealthPoints(user.getHealthPoints() - chapter.getAction().getPoints());
      else
        user.setHealthPoints(user.getHealthPoints() + chapter.getAction().getPoints());
      print("!!!!!");
      checkUserHealth(user);
    }
  }

  private void checkUserHealth(User user) {
    int points = user.getHealthPoints();
    if (points > 0) {
      print(user.getName() + " has " + user.getHealthPoints() + " health points left");
    } else {
      print("Game over!!! You have no more health points left");
      System.exit(0);
    }
  }
}
