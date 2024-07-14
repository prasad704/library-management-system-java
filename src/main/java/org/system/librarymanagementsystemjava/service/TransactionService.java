package org.system.librarymanagementsystemjava.service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.system.librarymanagementsystemjava.model.Book;
import org.system.librarymanagementsystemjava.model.InventoryTransaction;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionService extends JpaRepository<InventoryTransaction,Integer> {
    InventoryTransaction getDownloadCountByBookName(String bookName);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update inventory_transaction set download_count =:count where book_name=:bookName")
    Integer updateDownloadCountByBookName(@Param("count")int count,@Param("bookName") String bookName);
    Boolean existsByBookName(String bookNme);
    InventoryTransaction getByBookName(String bookName);
    @Query(nativeQuery = true, value = "select * from inventory_transaction where last_updated>=:duration")
    List<InventoryTransaction> getBookNameByWeek(@Param("duration")Instant duration);
    InventoryTransaction getDepartmentNameByBookName(String bookName);
}
