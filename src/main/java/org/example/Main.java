package org.example;

import org.example.dto.Book;
import org.example.dto.User;
import org.example.services.BookService;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args) {
    BookService bookService = new BookService();
    List<Book> bookList = bookService.getListOfBooks();
    Scanner sc = new Scanner(System.in);

    print("Hello and welcome to the game!");
    print("");
    print("Insert your username");
    String username = sc.nextLine();
    User user =  new User(username, 10);
    print("");
    print("Welcome " + user.getName() + ". You have " + user.getHealthPoints() + " health points.");
    print("");
    AtomicInteger index = new AtomicInteger(1);
    bookList.forEach(book -> System.out.println(index.getAndIncrement() + ": " + book.getTitle()));
    print("Chose one book:");
    int bookId = sc.nextInt();
    if(bookId > bookList.size() || bookId < 0) {
      print("Invalid book id");
      System.exit(0);
    }
    bookService.displayBookInfo(bookList.get(bookId-1));
    bookService.startGame(bookList.get(bookId), user);
  }


  public static void print(String toPrint) {
    System.out.println(toPrint);
  }
}
