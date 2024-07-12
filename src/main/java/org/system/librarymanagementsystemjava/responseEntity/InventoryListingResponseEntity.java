package org.system.librarymanagementsystemjava.responseEntity;

import org.system.librarymanagementsystemjava.model.Book;

import java.util.List;

public record InventoryListingResponseEntity(List<Book> listOfBooks) {
}
