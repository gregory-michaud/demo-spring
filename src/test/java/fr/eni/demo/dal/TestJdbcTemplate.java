package fr.eni.demo.dal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestJdbcTemplate {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void before() {
        // Insertion de données par défaut
        String sql = "INSERT INTO FORMATEURS (email,nom,prenom) " +
                " VALUES ('sgobin@campus-eni.fr','GOBIN','Stéphane');";
        // Validation d'un update
        int nbUpdate = jdbcTemplate.update(sql);
        assertEquals(1, nbUpdate);
    }

    @Test
    void test01_QueryForObject() {
        String sql = "SELECT count(*) FROM FORMATEURS;";
        Integer nbFormateurs = jdbcTemplate.queryForObject(sql, Integer.class);
        assertNotNull(nbFormateurs);
        assertEquals(1, nbFormateurs);
    }

    @Test
    void test02_QueryForList() {
        String sql = "SELECT email FROM FORMATEURS;";
        List<String> emails = jdbcTemplate.queryForList(sql, String.class);
        assertNotNull(emails);
        assertEquals(1, emails.size());
        assertEquals("sgobin@campus-eni.fr", emails.get(0));
        logger.info("QueryForList");
        emails.forEach(e -> logger.info(e));
    }
}
