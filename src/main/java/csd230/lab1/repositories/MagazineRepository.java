package csd230.lab1.repositories;

import csd230.lab1.entities.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    /**
     * Find magazines by their title and currIssue.
     *
     *  * @param title the title of the magazine
     *  * @param currIssue the current issue of the magazine
     *  * @return a list of magazines matching the title and currIssue
     */
     @Query("SELECT m FROM Magazine m WHERE m.title = :title AND m.currIssue = :currIssue")
     List<Magazine> findByTitleAndCurrIssue(String title, Date currIssue);
}

