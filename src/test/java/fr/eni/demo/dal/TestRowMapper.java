package fr.eni.demo.dal;

import fr.eni.demo.bo.Filiere;
import fr.eni.demo.bo.Promotion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestRowMapper {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void before() {
        // Insertion de 2 filières en 1 requête
        String sql = "INSERT INTO FILIERES (id, nom) VALUES " +
                " ('DEV','Développement'), " +
                " ('S&R','Système et Réseaux');";
        int nbUpdate = jdbcTemplate.update(sql);
        assertEquals(2, nbUpdate);

        // Insertion de 2 promotions en 1 requête
        sql = "INSERT INTO PROMOTIONS (id, id_filiere) VALUES " +
                " ('CDA_DEMO_2509','DEV'), " +
                " ('TSSR_DEMO_2510','S&R');";
        nbUpdate = jdbcTemplate.update(sql);
        assertEquals(2, nbUpdate);
    }

    @Test
    void test_RowMapper() {
        String sql = "SELECT id, id_filiere FROM PROMOTIONS;";
        List<Promotion> promotions = jdbcTemplate.query(sql, new PromotionRowMapper());
        assertNotNull(promotions);
        assertEquals(2, promotions.size());

        assertEquals("CDA_DEMO_2509", promotions.get(0).getCode());
        assertEquals("DEV", promotions.get(0).getFiliere().getId());

        assertEquals("TSSR_DEMO_2510", promotions.get(1).getCode());
        assertEquals("S&R", promotions.get(1).getFiliere().getId());

        promotions.forEach(elem -> assertNotNull(elem.getFiliere()));
        logger.info("PromotionRowMapper");
        promotions.forEach(elem -> logger.info(elem));
    }

    /**
     * Mise en place du RowMapper avec gestion de l'association 1-1
     */
    class PromotionRowMapper implements RowMapper<Promotion> {
        @Override
        public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Promotion promo = new Promotion();
            promo.setCode(rs.getString("id"));

            // Association en LAZY - uniquement la clef primaire associée
            Filiere filiere = new Filiere();
            filiere.setId(rs.getString("id_filiere"));
            promo.setFiliere(filiere);

            return promo;
        }
    }
}
