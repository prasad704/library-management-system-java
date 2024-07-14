package org.system.librarymanagementsystemjava.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.system.librarymanagementsystemjava.exception.BookObjectException;
import org.system.librarymanagementsystemjava.exception.NoDepartmentExistsExcpetion;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.model.Department;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;
import org.system.librarymanagementsystemjava.utilit.RequestValidation;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryManagementServiceImpl {

    @Lazy
    @Autowired
    private InventoryManagementService inventoryManagementService;

    @Autowired
    DepartmentService departmentService;


    @PostConstruct
    public void saveDepartments() throws NoSuchAlgorithmException {
        List<Department> departments = List.of(new Department(1,"Computer Science"),
                new Department(2,"History"),
                new Department(3,"Mythology"),
                new Department(4,"Mathematics"));
        departmentService.saveAll(departments);
    }

    public Optional<InventoryManagementResponseEntity> addBook(Book book) {
                inventoryManagementService.save(book);
                return Optional.of(new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book added successfully", Instant.now()));
            //throw new BookObjectException("ISBN is duplicate OR something went wrong");
    }

    public Optional<InventoryManagementResponseEntity> removeBook(String isbn) {
       Integer count = inventoryManagementService.deleteByIsbn(isbn);
       if(count>0){
           return Optional.of(new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book deleted successfully", Instant.now()));
       }else if(count == 0)
           throw new BookObjectException("Book does not exist",HttpStatus.NOT_FOUND);
       return Optional.empty();
    }

}
