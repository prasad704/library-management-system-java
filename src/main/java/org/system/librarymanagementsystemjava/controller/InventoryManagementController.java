package org.system.librarymanagementsystemjava.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.system.librarymanagementsystemjava.exception.BookObjectException;
import org.system.librarymanagementsystemjava.exception.NoDepartmentExistsExcpetion;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;
import org.system.librarymanagementsystemjava.service.InventoryManagementService;
import org.system.librarymanagementsystemjava.service.InventoryManagementServiceImpl;
import org.system.librarymanagementsystemjava.utilit.RequestValidation;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/manage")
public class InventoryManagementController {

    @Autowired
    private InventoryManagementServiceImpl inventoryManagementServiceImpl;

    @Autowired
    RequestValidation requestValidation;

    @PostMapping("/add")
    public ResponseEntity<Optional<InventoryManagementResponseEntity>> addBook(@RequestBody Book book) {
        try {
            if(requestValidation.validateRequest(book)){
                book.setAvailable(true);
                Optional<InventoryManagementResponseEntity> responseEntity = inventoryManagementServiceImpl.addBook(book);
                if (responseEntity != null && responseEntity.isPresent()) {
                    return ResponseEntity.ok(responseEntity);
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (BookObjectException e) {
            throw new BookObjectException(e.getMessage(),e.getStatus());
        } catch (NoDepartmentExistsExcpetion e) {
            throw new BookObjectException(e.getMessage(),e.getStatus());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Transactional
    @DeleteMapping("/deleteByIsbn/{isbn}")
    public ResponseEntity<Optional<InventoryManagementResponseEntity>> removeBook(@PathVariable String isbn) {
        try {
            Optional<InventoryManagementResponseEntity> responseEntity = inventoryManagementServiceImpl.removeBook(isbn);
            if (responseEntity != null && responseEntity.isPresent()) {
                return ResponseEntity.ok(responseEntity);
            }
            Optional opt = Optional.of(new InventoryManagementResponseEntity("Internal Error : 500", "Book could not be deleted", Instant.now()));
            return new ResponseEntity<>(opt, HttpStatus.NOT_FOUND);
        } catch (BookObjectException e) {
            throw new BookObjectException(e.getMessage(),e.getStatus());
        }
    }
}

