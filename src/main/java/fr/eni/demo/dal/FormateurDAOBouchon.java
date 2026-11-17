package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FormateurDAOBouchon implements FormateurDAO {

    private List<Formateur> formateurs;

    public FormateurDAOBouchon() {
        formateurs = new ArrayList<>();
        formateurs.add(new Formateur("nom1FormateurDAO", "prenom1FormateurDAO", "email1formateurDAO@campus-eni.fr"));
        formateurs.add(new Formateur("nom2FormateurDAO", "prenom2FormateurDAO", "email2formateurDAO@campus-eni.fr"));
    }

    @Override
    public List<Formateur> findAll() {
        return formateurs;
    }

    @Override
    public Optional<Formateur> findByEmail(String email) {
        return formateurs.stream().filter(f->f.getEmail().equals(email)).findFirst();
    }

    @Override
    public void insert(Formateur formateur) {
        formateurs.add(formateur);
    }
}
