package org.example;

import org.example.dto.Book;
import org.example.services.BookService;


import java.util.*;

public class Main {
  public static void main(String[] args) {
    BookService bookService = new BookService();
    List<Book> bookList = bookService.getListOfBooks();
    Scanner sc = new Scanner(System.in);
    boolean gameStart = true;

    print("Hello and welcome to the game!");
    bookList.forEach(book -> System.out.println(book.getTitle()));
    print("Chose one book:");
    int bookId = sc.nextInt()-1;
    bookService.displayBookInfo(bookList.get(bookId));
    bookService.startGame(bookList.get(bookId));
  }


  public static void print(String toPrint) {
    System.out.println(toPrint);
  }
}
