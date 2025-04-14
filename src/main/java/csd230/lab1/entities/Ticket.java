package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;


@Entity
public class Ticket extends CartItem {
    @Column(name = "text")
    private String text;

    public Ticket() {
    }

    public Ticket(String text, double price, int quantity, String description) {
        super(price, quantity, description);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket that)) return false;
        if (!super.equals(that)) return false;
        return Objects.equals(text, that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}