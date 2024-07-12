package org.system.librarymanagementsystemjava.exception;

public class BookObjectException extends RuntimeException {

    public String message;

    public BookObjectException(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
