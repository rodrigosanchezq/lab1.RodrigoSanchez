package csd230.lab1.repositories;

import csd230.lab1.entities.DiscMag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscMagRepository extends JpaRepository<DiscMag, Long> {
    /**
     * Find all DiscMag entities.
     *
     * @return a list of all DiscMag entities
     */
     @Query("SELECT d FROM DiscMag d")
     List<DiscMag> findAll();
}