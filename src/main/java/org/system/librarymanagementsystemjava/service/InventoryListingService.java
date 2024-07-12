package org.system.librarymanagementsystemjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.system.librarymanagementsystemjava.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryListingService extends JpaRepository<Book, Integer> {

    Book findBooksByTitleIgnoreCase(String title);
    List<Book> findBookByAuthorIgnoreCase(String author);
    List<Book> findByAvailableTrue();
}
