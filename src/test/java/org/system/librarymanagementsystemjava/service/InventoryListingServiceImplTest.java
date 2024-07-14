package org.system.librarymanagementsystemjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;
import org.system.librarymanagementsystemjava.model.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryListingServiceImplTest {

    @InjectMocks
    private InventoryListingServiceImpl  inventoryListingServiceImpl;
            @Mock
    InventoryListingService inventoryListingServiceMock;

   private  List<Book> books = null;
    @BeforeEach
    public void setUp() {
        books = List.of(new Book("title","author","isbn","genre","yop","dname",1),
                new Book("title1","author1","isbn1","genre1","yop1","dname1",2));
    }
    @Test
    void findBookByTitle() {
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        doReturn(book).when(inventoryListingServiceMock).findBooksByTitleIgnoreCase(book.getTitle());
}

    @Test
    void findBookByAuthor() {
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        doReturn(List.of(book)).when(inventoryListingServiceMock).findBookByAuthorIgnoreCase(book.getTitle());
    }

    @Test
    void findAllBooks() {
        when(inventoryListingServiceMock.findAll()).thenReturn(books);
    }

    @Test
    void findAllAvailableBooks() {
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        when(inventoryListingServiceMock.findByAvailableTrue()).thenReturn(List.of(book));
    }
}