package csd230.lab1.repositories;

import csd230.lab1.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Find all Cart entities.
     *
     * @return a list of all Cart entities
     */
    @Query( "SELECT c FROM Cart WHERE c.id = :idi")
    List<Cart> findById();
}