package fr.eni.demo.dal;

import fr.eni.demo.bo.Formateur;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("default")
public class FormateurDAOImpl implements FormateurDAO {

    private static final String FIND_ALL = "SELECT email, nom, prenom FROM formateurs";

    private static final String FIND_BY_EMAIL = "SELECT email, nom, prenom FROM formateurs WHERE email = :email";

    private static final String INSERT = "INSERT INTO formateurs (email, nom, prenom) VALUES (:email, :nom, :prenom)";


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FormateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Formateur> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Formateur.class));
    }

    @Override
    public Optional<Formateur> findByEmail(String email) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        try {
            Formateur formateur = jdbcTemplate.queryForObject(FIND_BY_EMAIL, map, new BeanPropertyRowMapper<>(Formateur.class));
            return Optional.of(formateur);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void insert(Formateur formateur) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", formateur.getEmail());
        map.addValue("nom", formateur.getNom());
        map.addValue("prenom", formateur.getPrenom());

        jdbcTemplate.update(INSERT, map);

    }
}
