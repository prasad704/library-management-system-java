package org.system.librarymanagementsystemjava.exception;

public class BookNotFoundException extends RuntimeException {
    public String message;

    public BookNotFoundException(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
