package fr.eni.demo.bll;

import fr.eni.demo.bo.Formateur;

import java.util.List;

public interface FormateurService {

    List<Formateur> getFormateurs();

    Formateur getFormateur(String email);

    void addFormateur(Formateur formateur);


}
