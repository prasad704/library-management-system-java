package org.system.librarymanagementsystemjava.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.system.librarymanagementsystemjava.key.CompositeKey;


@Component
@Entity
@Table(name="book_tbl")
@IdClass(CompositeKey.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(unique = true)
    private String title;
    @NotNull
    private String author;
    @NotNull
    @Column(unique = true)
    private String isbn;
    private String genre;
    private String yearOfPublication;
    //@ManyToOne(cascade = CascadeType.ALL)

    private String departmentName;
    private int departmentId;
    private boolean available;

    public Book(String title, String author, String isbn, String genre, String yearOfPublication, String departmentName, int departmentId) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
        this.available = false;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
