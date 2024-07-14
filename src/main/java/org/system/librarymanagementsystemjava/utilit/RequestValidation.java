package org.system.librarymanagementsystemjava.utilit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.system.librarymanagementsystemjava.exception.BookObjectException;
import org.system.librarymanagementsystemjava.exception.NoDepartmentExistsExcpetion;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.model.Department;
import org.system.librarymanagementsystemjava.service.DepartmentService;
import org.system.librarymanagementsystemjava.service.InventoryListingService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
public class RequestValidation {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    InventoryListingService inventoryListingService;


    public boolean validateRequest(Book book){
        if(!checkMandatoryRequestParam(book))
            throw new BookObjectException("Some of the request parameters are missing. Please check your request", HttpStatus.BAD_REQUEST);
        if(isISBNDuplicate(book.getIsbn()))
            throw new BookObjectException("ISBN already exists", HttpStatus.CONFLICT);
        if(!validateDepartment(book))
                throw new NoDepartmentExistsExcpetion("Department does not exist with name: "+book.getDepartmentName(), HttpStatus.NOT_FOUND);
        if(checkForDuplicateTitle(book))
            throw new BookObjectException("Title already exists", HttpStatus.CONFLICT);
        return true;
    }
    public boolean isISBNDuplicate(String isbn) {
        return inventoryListingService.existsByIsbn(isbn);
    }

    public boolean checkMandatoryRequestParam(Book book){
        return book.getTitle()!=null && book.getAuthor()!=null && book.getIsbn()!=null && book.getDepartmentName()!=null;
    }

    public boolean validateDepartment(Book book){
        Optional<Department> department= departmentService.getIdByDepartmentName(book.getDepartmentName());
        if(!department.isPresent())
            return false;
        book.setDepartmentId(department.get().getId());
        return true;
    }

    public boolean checkForDuplicateTitle(Book book){
        return inventoryListingService.existsByTitleIgnoreCase(book.getTitle());
    }

}
