
package org.system.librarymanagementsystemjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InventoryException {

    @ExceptionHandler(BookObjectException.class)
    public ResponseEntity<ApiResponse> handleInventoryException(BookObjectException e) {
        ApiResponse objectException = new ApiResponse(e.message,e.getStatus());
        return new ResponseEntity<ApiResponse>(objectException,e.getStatus());
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse> handleInventoryExceptionBookNotFound(BookNotFoundException e) {
        ApiResponse objectException = new ApiResponse(e.message,HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiResponse>(objectException,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDepartmentExistsExcpetion.class)
    public ResponseEntity<ApiResponse> handleInventoryExceptionDepartmentNotFound(NoDepartmentExistsExcpetion e) {
        ApiResponse objectException = new ApiResponse(e.message,e.getStatus());
        return new ResponseEntity<ApiResponse>(objectException,e.getStatus());
    }
}

