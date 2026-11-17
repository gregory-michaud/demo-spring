package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;

import java.util.List;
import java.util.Optional;

public interface FormateurDAO {

    List<Formateur> findAll();

    Optional<Formateur> findByEmail(String email);

    void insert(Formateur formateur);

}
