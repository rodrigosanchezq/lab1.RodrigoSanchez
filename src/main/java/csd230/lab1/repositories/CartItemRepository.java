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
}