package org.system.librarymanagementsystemjava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryManagementServiceImplTest {
    @InjectMocks
    private InventoryManagementServiceImpl inventoryManagementService;
    @Mock
    private InventoryManagementService inventoryManagementServiceMock;
    @Mock
    private DepartmentService departmentServiceMock;

    @Test
    public void testCreateInventory() {
       InventoryManagementResponseEntity expected =  new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book added successfully", Instant.now());
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        when(inventoryManagementService.addBook(book)).thenReturn(Optional.of(expected));
    }

    @Test
    public void testDeleteInventory(){
        Integer count =1;
        InventoryManagementResponseEntity expected =  new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book deleted successfully", Instant.now());
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        doReturn(count).when(inventoryManagementServiceMock).deleteByIsbn(book.getIsbn());
    }

}