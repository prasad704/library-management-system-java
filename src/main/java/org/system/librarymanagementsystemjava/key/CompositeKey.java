package org.system.librarymanagementsystemjava.key;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


public class CompositeKey implements Serializable {

    private int id;
    private String title;
    private String isbn;

    public CompositeKey() {}
    public CompositeKey(String title , String isbn,int id) {
        this.title = title;
        this.isbn = isbn;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn);
    }
}
