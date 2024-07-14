package org.system.librarymanagementsystemjava.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.service.InventoryListingServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryListingControllerTest {

    @Mock
    private InventoryListingServiceImpl inventoryListingService;

    private  List<Book> books = null;

    @BeforeEach
    public void setUp() {
        books = List.of(new Book("title","author","isbn","genre","yop","dname",1),
                new Book("title1","author1","isbn1","genre1","yop1","dname1",2));
    }
    @Test
    void findBookByTitle() {
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        doReturn(Optional.of(book)).when(inventoryListingService).findBookByTitle(book.getTitle());
    }
    @Test
    void findBookByAuthor() {
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        doReturn(Optional.of(List.of(book))).when(inventoryListingService).findBookByAuthor(book.getTitle());
    }

    @Test
    void findAllBooks() {
        when(inventoryListingService.findAllBooks()).thenReturn(Optional.of(books));
    }

    @Test
    void findAllAvailableBooks() {
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        when(inventoryListingService.findAllAvailableBooks()).thenReturn(Optional.of(List.of(book)));
    }

}