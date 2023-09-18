package nl.hva.dogwalker.persistence.dao;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 18/09/2023 21:53
 */

import nl.hva.dogwalker.util.security.VerificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcVerificationTokenDao {

    private final Logger        logger = LoggerFactory.getLogger(JdbcVerificationTokenDao.class);
    private final JdbcTemplate  jdbcTemplate;
    private final DataSource    dataSource;

    public JdbcVerificationTokenDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        logger.info("New VerificationTokenDao");
    }

    public void save(VerificationToken verificationToken) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.withTableName("VerificationToken").usingGeneratedKeyColumns("id");
        int newId = simpleJdbcInsert.execute(mapInsertParameters(verificationToken));
        verificationToken.setId(newId);
    }

    private Map<String, Object> mapInsertParameters(VerificationToken verificationToken) {
        Map<String, Object> insertParameters = new HashMap<>();
        insertParameters.put("token", verificationToken.getToken());
        insertParameters.put("expirationTime", verificationToken.getExpirationTime());
        insertParameters.put("idUser", verificationToken.getUser().getIdU());
        return insertParameters;
    }

    public void delete(VerificationToken token) {
        String sql = "DELETE FROM VerificationToken WHERE token = ?;";
        jdbcTemplate.update(sql, token.getToken());
    }

    public VerificationToken findByToken(String token) {
        String query = "SELECT * FROM VerificationToken WHERE token = ?;";
        List<VerificationToken> verificationTokens = jdbcTemplate.query(query,
                new JdbcVerificationTokenDao.VerificationTokenRowMapper(), token);
        if (verificationTokens.size() == 1) {
            return verificationTokens.get(0);
        }
        return null;
    }

    public int findUserIdByToken(String token) {
        String query = "SELECT idUser FROM VerificationToken WHERE token = ?;";
        List<Integer> userId = jdbcTemplate.query(query, new IdUserRowMapper(), token);
        if (userId.size() == 1) {
            return userId.get(0);
        }
        return -1;
    }

    private class VerificationTokenRowMapper implements RowMapper<VerificationToken> {
        @Override
        public VerificationToken mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int id = resultSet.getInt("idVerificationToken");
            String token = resultSet.getString("token");
            Timestamp expirationTimestamp = resultSet.getTimestamp("expirationTime");
            VerificationToken verificationToken = new VerificationToken(token, expirationTimestamp);
            verificationToken.setId(id);
            return verificationToken;
        }
    }

    private class IdUserRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return resultSet.getInt("idUser");
        }
    }

}
