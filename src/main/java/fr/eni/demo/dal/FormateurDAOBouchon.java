package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormateurDAOBouchon implements FormateurDAO {

    private List<Formateur> formateurs = List.of(
            new Formateur("nom1FormateurDAO", "prenom1FormateurDAO", "email1formateurDAO@campus-eni.fr"),
            new Formateur("nom2FormateurDAO", "prenom2FormateurDAO", "email2formateurDAO@campus-eni.fr")
    );


    @Override
    public List<Formateur> findAll() {
        return formateurs;
    }
}
