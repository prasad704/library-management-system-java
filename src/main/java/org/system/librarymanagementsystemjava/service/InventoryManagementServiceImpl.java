package org.system.librarymanagementsystemjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.system.librarymanagementsystemjava.exception.BookObjectException;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;

import java.time.Instant;
import java.util.Optional;

@Service
public class InventoryManagementServiceImpl {

    @Lazy
    @Autowired
    private InventoryManagementService inventoryManagementService;

    public Optional<InventoryManagementResponseEntity> addBook(Book book) {
        try {
            inventoryManagementService.save(book);
            return Optional.of(new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book added successfully", Instant.now()));
        } catch (Exception e) {
            throw new BookObjectException("ISBN is duplicate OR something went wrong");
        }
    }

    public Optional<InventoryManagementResponseEntity> removeBook(String isbn) {
       Integer count = inventoryManagementService.deleteByIsbn(isbn);
       if(count>0){
           return Optional.of(new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book deleted successfully", Instant.now()));
       }else if(count == 0)
           throw new BookObjectException("Book does not exist");
       return Optional.empty();
    }

}
