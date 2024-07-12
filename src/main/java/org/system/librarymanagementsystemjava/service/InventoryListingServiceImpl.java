package org.system.librarymanagementsystemjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.system.librarymanagementsystemjava.exception.BookNotFoundException;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.responseEntity.InventoryListingResponseEntity;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryListingServiceImpl {

    @Autowired
    @Lazy
    private InventoryListingService inventoryListingService;

    public Optional<Book> findBookByTitle(String title){
       Book book =  inventoryListingService.findBooksByTitleIgnoreCase(title);
       if(book != null){
           return Optional.of(book);
       } else throw new BookNotFoundException("Book with title: "+title+" not found in inventory");

    }

    public Optional<List<Book>>findBookByAuthor(String authorName){
        List<Book> books=  inventoryListingService.findBookByAuthorIgnoreCase(authorName);
        if(!books.isEmpty() && books!=null ){
            return Optional.of(books);
        }else throw new BookNotFoundException("Book authoured by: "+authorName+" not found in inventory");
    }

    public Optional<List<Book>>findAllBooks(){
        try {
            List<Book> books=  inventoryListingService.findAll();
            if(!books.isEmpty() && books!=null ){
                return Optional.of(books);
            }else throw new BookNotFoundException("Inventory is empty");
        } catch (Exception e) {
            throw new BookNotFoundException(e.getMessage());
        }
    }

    public Optional<List<Book>>findAllAvailableBooks(){
        try {
            List<Book> books=  inventoryListingService.findByAvailableTrue();
            if(!books.isEmpty() && books!=null ){
                return Optional.of(books);
            }else throw new BookNotFoundException("No available books found");
        } catch (Exception e) {
            throw new BookNotFoundException(e.getMessage());
        }
    }
}
