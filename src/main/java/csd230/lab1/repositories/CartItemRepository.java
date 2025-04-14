package csd230.lab1.repositories;

import csd230.lab1.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /**
     * Find all CartItem entities.
     *
     * @return a list of all CartItem entities
     */

    @Query ("SELECT c FROM CartItem c")
    List<CartItem> findAll();

    /**
     * Find all CartItem entities with a specific quantity.
     *
     * @param quantity the quantity to search for
     * @return a list of CartItem entities with the specified quantity
     */
    @Query ("SELECT c FROM CartItem c WHERE c.quantity = ?1")
    List<CartItem> findByQuantity(int quantity);
}