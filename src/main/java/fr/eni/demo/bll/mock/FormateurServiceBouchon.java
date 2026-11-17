package fr.eni.demo.bll.mock;

import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Formateur;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("dev")
public class FormateurServiceBouchon implements FormateurService {

    private List<Formateur> formateurs = List.of(
            new Formateur("nom1Formateur", "prenom1Formateur", "email1formateur@campus-eni.fr"),
            new Formateur("nom2Formateur", "prenom2Formateur", "email2formateur@campus-eni.fr")
    );

    @Override
    public List<Formateur> getFormateurs() {
        return formateurs;
    }

    @Override
    public Formateur getFormateur(String email) {
        Optional<Formateur> oFormateur = formateurs.stream().filter(f->f.getEmail().equals(email)).findFirst();

        return oFormateur.orElse(null);
    }

    @Override
    public void addFormateur(Formateur formateur) {
        formateurs.add(formateur);
    }
}
