package csd230.lab1.repositories;

import csd230.lab1.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

    // This method will find all publications with the given title
    // and return them as a list
    List<Publication> findByTitle(String title);

    // This method will find all publications with the given author
    // and return them as a list
    List <Publication> findByAuthor(String author);
}