package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
public class TestBeanPropertyRowMapper {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void before() {
        // Insertion de 2 formateurs en 1 requête
        String sql = "INSERT INTO FORMATEURS (email, nom, prenom) VALUES " +
                "('sgobin@campus-eni.fr', 'GOBIN', 'Stéphane'), " +
                "('abaille@campus-eni.fr', 'BAILLE', 'Anne-Lise');";

        int nbUpdate = jdbcTemplate.update(sql);
        assertEquals(2, nbUpdate);
    }

    @Test
    void test_BeanPropertyRowMapper() {
        // La requête explicite les colonnes
        // pour ne pas avoir de cas d'erreur avec le BeanPropertyRowMapper si ajout d'une nouvelle colonne
        String sql = "SELECT email, nom, prenom FROM FORMATEURS;";
        List<Formateur> formateurs = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Formateur.class)
        );
        assertNotNull(formateurs);
        assertEquals(2, formateurs.size());
        logger.info("BeanPropertyRowMapper");
        formateurs.forEach(e -> logger.info(e));
    }

}
