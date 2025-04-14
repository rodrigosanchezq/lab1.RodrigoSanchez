package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Book extends Publication {
    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String ISBN;

    public Book() {
    }
    public Book(double price, int quantity, String description, String title, int copies, String author, String isbn) {
        super(title, copies, price, quantity, description);
        this.author = author;
        this.ISBN = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book that)) return false;
        if (!super.equals(that)) return false;
        return author.equals(that.author) && ISBN.equals(that.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, ISBN);
    }

}
