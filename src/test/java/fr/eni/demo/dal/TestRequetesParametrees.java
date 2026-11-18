package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestRequetesParametrees {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    void test01_InjectionSQL_RequeteParametree() {
        // La saisie utilisateur est la suivante :
        String userInput = "' OR '1'='1";

        // Concaténation de la requête et du paramètre
        String sql = "SELECT email, nom, prenom FROM FORMATEURS WHERE email = ?";

        // Aucun formateur en base n'a comme email ' OR '1'='1
        // La méthode queryForObject retourne une exception EmptyResultDataAccessException quand le nombre de résultat = 0
        // Nous testons la levée de l'exception pour prouver notre protection de l'injection SQL
        assertThrows(EmptyResultDataAccessException.class, () -> {
            jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Formateur.class), userInput);
        });
    }

    @Test
    void test02_InjectionSQL_NamedPrameterJdbcTemplate() {
        // La saisie utilisateur est la suivante :
        String userInput = "' OR '1'='1";

        // Concaténation de la requête et du paramètre
        String sql = "SELECT email, nom, prenom FROM FORMATEURS WHERE email = :email";


        // Paramétrage sécurisé
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", userInput);

        // Aucun formateur en base n'a comme email ' OR '1'='1
        // La méthode queryForObject retourne une exception EmptyResultDataAccessException quand le nombre de résultat = 0
        // Nous testons la levée de l'exception pour prouver notre protection de l'injection SQL
        assertThrows(EmptyResultDataAccessException.class, () -> {
            namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new BeanPropertyRowMapper<>(Formateur.class)
            );
        });
    }
}
