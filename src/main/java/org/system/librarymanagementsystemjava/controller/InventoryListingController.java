package org.system.librarymanagementsystemjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.librarymanagementsystemjava.exception.BookNotFoundException;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.service.InventoryListingService;
import org.system.librarymanagementsystemjava.service.InventoryListingServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/find-book")
public class InventoryListingController {

    @Autowired
    private InventoryListingServiceImpl inventoryListingServiceImpl;


    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<Optional<Book>> findBookByTitle(@PathVariable String bookTitle){
        try {
            Optional<Book> book = inventoryListingServiceImpl.findBookByTitle(bookTitle);
            if(book!=null && book.isPresent()){
                return ResponseEntity.ok(book);
            }
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<Optional<List<Book>>> findBookByAuthor(@PathVariable String authorName){
        try {
            Optional<List<Book>> books = inventoryListingServiceImpl.findBookByAuthor(authorName);
            if(!books.isEmpty() && books!=null && books.isPresent()){
                return ResponseEntity.ok(books);
            }
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/findAll")
    public ResponseEntity<Optional<List<Book>>> findAllBooks(){
        try {
            Optional<List<Book>> books = inventoryListingServiceImpl.findAllBooks();
            if(!books.isEmpty() && books!=null){
                return ResponseEntity.ok(books);
            }
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException(e.getMessage());
        }catch(Exception e){
            throw new BookNotFoundException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/find-available-books")
    public ResponseEntity<Optional<List<Book>>> findAllAvailableBooks(){
        try {
            Optional<List<Book>> books = inventoryListingServiceImpl.findAllAvailableBooks();
            if(!books.isEmpty() && books!=null){
                return ResponseEntity.ok(books);
            }
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException(e.getMessage());
        }catch(Exception e){
            throw new BookNotFoundException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
