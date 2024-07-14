package org.system.librarymanagementsystemjava.service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.librarymanagementsystemjava.model.Book;
@Repository
@Transactional
public interface InventoryManagementService extends JpaRepository<Book,Integer> {
    Integer deleteByIsbn(String isbn);
}
