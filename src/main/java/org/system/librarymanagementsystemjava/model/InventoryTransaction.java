package org.system.librarymanagementsystemjava.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Entity
@Table
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String bookName;
    private String departmentName;
    private int downloadCount;
    private Instant lastUpdated;

    public InventoryTransaction() {
    }

    public InventoryTransaction(String bookName, String departmentName, int downloadCount, Instant last_updated) {
        this.bookName = bookName;
        this.departmentName = departmentName;
        this.downloadCount = downloadCount;
        this.lastUpdated = last_updated;
    }

    public int getTransactionId(){
        return transactionId;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
