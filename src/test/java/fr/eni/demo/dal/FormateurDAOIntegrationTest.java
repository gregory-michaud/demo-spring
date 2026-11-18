package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(FormateurDAOImpl.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class FormateurDAOIntegrationTest {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private FormateurDAO dao;

    @Test
    void test01_InsertAndFindByEmail() {
        String email = "jtrillard@campus-eni.fr";
        String nom = "Trillard";
        String prenom = "Julien";

        Formateur f = new Formateur(nom, prenom, email);
        dao.insert(f);

        Optional<Formateur> result = dao.findByEmail(email);

        assertThat(result).isPresent();
        assertThat(result.get().getNom()).isEqualTo(nom);
        assertThat(result.get().getPrenom()).isEqualTo(prenom);

        logger.info("test01_InsertAndFindByEmail");
        logger.info(f);
    }

    @Test
    void test02_FindAllEmpty() {
        List<Formateur> results = dao.findAll();
        assertThat(results).isEmpty();

        logger.info("test02_FindAllEmpty");
        logger.info(results);
    }

    @Test
    void test03_FindAll_returnsAtLeastOne() {
        // GIVEN
        dao.insert(new Formateur("Le Guilly", "Marie", "mleguilly@campus-eni.fr"));
        dao.insert(new Formateur("Trillard", "Julien", "jtrillard@campus-eni.fr"));

        // WHEN
        List<Formateur> results = dao.findAll();

        // THEN
        assertThat(results).hasSize(2);
        assertThat(results)
                .extracting(Formateur::getEmail)
                .containsExactlyInAnyOrder("mleguilly@campus-eni.fr", "jtrillard@campus-eni.fr");

        logger.info("test03_FindAll_returnsAtLeastOne");
        logger.info(results);
    }
}
