package csd230.lab1.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class DiscMag extends Magazine {
    @Column(name = "has_disc", nullable = true)
    private boolean hasDisc;

    public DiscMag() {}

    public DiscMag (boolean hasDisc, double price, int quantity, String description, String title, int copies, int orderQty, Date currIssue) {
        super(price, quantity, description, title, copies, orderQty, currIssue);
        this.hasDisc = hasDisc;
    }

    public boolean getHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public String toString() {
        return "DiscMag{" +
                "hasDisc=" + hasDisc +
                "} " + super.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscMag discMag)) return false;
        if (!super.equals(o)) return false;
        return getHasDisc() == discMag.getHasDisc();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHasDisc());
    }


}