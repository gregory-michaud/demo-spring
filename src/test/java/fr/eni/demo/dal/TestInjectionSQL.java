package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
public class TestInjectionSQL {
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
    void test_InjectionSQL() {
        // La saisie utilisateur est la suivante :
        String userInput = "' OR '1'='1";

        // Concaténation de la requête et du paramètre
        String sql = "SELECT email, nom, prenom FROM FORMATEURS WHERE email = '" + userInput + "'";

        // La méthode queryForObject retourne une exception IncorrectResultSizeDataAccessException quand le nombre de résultat > 1
        // Dans le cas de cette injection SQL = 2 Donc levée de l'exception
        // Nous testons la levée de l'exception pour prouver l'injection SQL
        assertThrows(IncorrectResultSizeDataAccessException.class, () -> {
            jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Formateur.class));
        });
    }

}
