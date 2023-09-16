// Created by huub
// Creation date 2023-08-03

package nl.hva.dogwalker.persistence.dao;

import nl.hva.dogwalker.business.domain.Customer;
import nl.hva.dogwalker.business.domain.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcDogDao implements DogDao {

  private final Logger logger = LoggerFactory.getLogger(JdbcDogDao.class);
  private final JdbcTemplate jdbcTemplate;

  public JdbcDogDao(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
    logger.info("New JdbcDogDao");
  }

  private PreparedStatement insertDogStatement(Dog dog, Connection connection) {
    try {
      PreparedStatement ps = connection.prepareStatement(
        "insert into dog_table (name, breed, owner_fk) values (?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, dog.getName());
      ps.setString(2, dog.getBreed());
      ps.setInt(3, dog.getOwner().getId());
      return ps;
    } catch (SQLException e) {
      logger.error("Database error " + e.getMessage());
      return null;
    }
  }

  @Override
  public void save(Dog dog) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> insertDogStatement(dog, connection), keyHolder);
    int newKey = keyHolder.getKey().intValue();
    dog.setId(newKey);
  }

  @Override
  public Optional<Dog> findDogById(int id) {
    List<Dog> dogs = jdbcTemplate.query("select * from dog_table where id = ?", new DogRowMapper(), id);
    if (dogs.size() == 1) {
      return Optional.of(dogs.get(0));
    }
    return Optional.empty();
  }

  public List<Dog> findAllDogsForCustomer(int customerId) {
    List<Dog> dogs = jdbcTemplate.query("select * from dog_table where owner_fk = ?", new DogRowMapper(), customerId);
    return dogs;
  }

  private class DogRowMapper implements RowMapper<Dog> {

    @Override
    public Dog mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      String breed = rs.getString("breed");
      Customer owner = null;
      Dog dog = new Dog(name, breed, owner);
      dog.setId(id);
      return dog;
    }
  }
}
