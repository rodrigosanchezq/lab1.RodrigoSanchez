package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Publication extends CartItem {
    @Column(name = "title")
    private String title;

    @Column(name = "copies", nullable = false)
    private int copies;

    public Publication() {
    }

    public Publication(String title, int copies, double price, int quantity, String description) {
        super(price, quantity, description);
        this.title = title;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", copies=" + copies +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication that)) return false;
        if (!super.equals(o)) return false;
        return getCopies() == that.getCopies() && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getCopies());
    }
}