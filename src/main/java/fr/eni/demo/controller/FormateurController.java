package fr.eni.demo.controller;

import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormateurController {

    private final FormateurService formateurService;

    public FormateurController(FormateurService formateurService) {
        System.out.println("Appel du constructeur");
        this.formateurService = formateurService;
    }

    public void afficherFormateurs(){
        List<Formateur> formateurList = this.formateurService.getFormateurs();
        formateurList.forEach(System.out::println);
    }

    /*@Autowired
    public void setFormateurService(FormateurService formateurService) {
        System.out.println("Appel de setFormateurService");
        this.formateurService = formateurService;
    }*/
}
