package fr.eni.demo.controller;

import fr.eni.demo.bll.FormateurService;
import fr.eni.demo.bo.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
// Définit une url racine pour tout le controller
@RequestMapping("/formateurs")
public class FormateurController {

    private final FormateurService formateurService;

    public FormateurController(FormateurService formateurService) {
        System.out.println("Appel du constructeur");
        this.formateurService = formateurService;
    }

    @GetMapping
    //@ResponseBody
    public List<Formateur> afficherFormateurs(){
        List<Formateur> formateurList = this.formateurService.getFormateurs();
        return formateurList;
    }

    @GetMapping("/{emailFormateur}")
    public Formateur detailFormateur(@PathVariable("emailFormateur") String email){
        Formateur formateur = this.formateurService.getFormateur(email);
        return formateur;

    }

    @PostMapping
    public void creerFormateur(Formateur formateur){

        this.formateurService.addFormateur(formateur);
    }



    /*@Autowired
    public void setFormateurService(FormateurService formateurService) {
        System.out.println("Appel de setFormateurService");
        this.formateurService = formateurService;
    }*/
}
