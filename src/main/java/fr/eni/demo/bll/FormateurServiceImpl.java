package fr.eni.demo.bll;

import fr.eni.demo.bo.Formateur;
import fr.eni.demo.dal.FormateurDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("default")
public class FormateurServiceImpl implements FormateurService {

    private final FormateurDAO formateurDAO;

    public FormateurServiceImpl(FormateurDAO formateurDAO) {
        this.formateurDAO = formateurDAO;
    }

    @Override
    public List<Formateur> getFormateurs() {
        return this.formateurDAO.findAll();
    }
}
