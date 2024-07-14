package org.system.librarymanagementsystemjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.librarymanagementsystemjava.model.InventoryTransaction;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;
import org.system.librarymanagementsystemjava.service.TransactionService;
import org.system.librarymanagementsystemjava.service.TransactionServiceImpl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/portal")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @PostMapping("/download")
    public ResponseEntity<Optional<InventoryManagementResponseEntity>> downloadBook(@RequestBody InventoryTransaction transaction){
        transaction.setLastUpdated(Instant.now());
        Optional<InventoryManagementResponseEntity> response = transactionServiceImpl.downloadBook(transaction);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leader")
    public ResponseEntity<Optional<Map<String,String>>> getPopularByDuration(){
        Optional<Map<String,String>> response = transactionServiceImpl.getPopularByDuration();
        return ResponseEntity.ok(response);
    }

}
