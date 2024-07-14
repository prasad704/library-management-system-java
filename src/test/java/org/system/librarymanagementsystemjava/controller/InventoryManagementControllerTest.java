package org.system.librarymanagementsystemjava.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;
import org.system.librarymanagementsystemjava.service.InventoryListingServiceImpl;
import org.system.librarymanagementsystemjava.service.InventoryManagementService;
import org.system.librarymanagementsystemjava.service.InventoryManagementServiceImpl;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@WebMvcTest(controllers = InventoryManagementController.class)
//@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryManagementControllerTest {

   /* @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;*/

    @Mock
    InventoryManagementServiceImpl inventoryManagementService;

    @Test
    public void testAddBook(){
        InventoryManagementResponseEntity responseEntity =new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book added successfully", Instant.now());
        when(inventoryManagementService.addBook(new Book("title1","author1","isbn1","genre1","yop1","dname1",2)))
                .thenReturn((Optional.of(responseEntity)));
    }

    @Test
    public void testDeleteController(){

        InventoryManagementResponseEntity responseEntity = new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book deleted successfully", Instant.now());
        Book book = new Book("title1","author1","isbn1","genre1","yop1","dname1",2);
        doReturn(Optional.of(responseEntity)).when(inventoryManagementService).removeBook(book.getIsbn());

    }
}