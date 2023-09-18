package nl.hva.dogwalker.persistence.dao;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 16:37
 */

import nl.hva.dogwalker.business.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserDao implements UserDao {
    private final Logger        logger = LoggerFactory.getLogger(JdbcUserDao.class);
    private final JdbcTemplate  jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New UserDAO");
    }

    @Override
    public User saveUser(User user) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> buildInsertCustomerStatement(user, connection), keyHolder);
            int newKey = keyHolder.getKey().intValue();
            user.setId(newKey);
            return user;
        } catch (Exception ex) {
            logger.error("saveUser in jdbcUserDao error: " + ex.getMessage());
            return null;
        }
    }

    private PreparedStatement buildInsertCustomerStatement(User user, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO User(email, hashed_password, salt, jwttoken, isVerified) " +
                        "VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPasswordHash());
        ps.setString(3, user.getSalt());
        ps.setString(4, user.getJwtToken());
        ps.setBoolean(5, user.isVerified());
        return ps;
    }

    public Optional<User> findUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper());

        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(users.get(0));
        }
    }


    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPasswordHash(rs.getString("hashed_password"));
            user.setSalt(rs.getString("salt"));
            return user;
        }
    }

    public User updateUser(User user) {
        jdbcTemplate.update("UPDATE User SET email = ?, hashed_password = ?, salt = ?, isVerified = ?, jwtToken = ?, WHERE id = ?",
                user.getEmail(), user.getPassword(), user.getSalt(), user.isVerified(), user.getJwtToken(), user.getIdU());
        return user;
    }

    public User findUserById(int idUser) {
        String query = "SELECT * FROM User WHERE id = ?;";
        List<User> users = jdbcTemplate.query(query, new UserRowMapper(), idUser);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
    public User findUserByjwtToken(String jwtToken) {
        String query = "SELECT * FROM User WHERE jwtToken = ?;";
        List<User> users = jdbcTemplate.query(query, new UserRowMapper(), jwtToken);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
}
