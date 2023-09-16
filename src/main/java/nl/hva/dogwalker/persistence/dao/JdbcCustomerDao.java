// Created by huub
// Creation date 2023-08-03

package nl.hva.dogwalker.persistence.dao;
import nl.hva.dogwalker.business.domain.Customer;
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
public class JdbcCustomerDao implements CustomerDao {
  private final Logger logger = LoggerFactory.getLogger(JdbcCustomerDao.class);
  private final JdbcTemplate jdbcTemplate;

  public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
    logger.info("New JdbcCustomerDao");
  }

  private PreparedStatement insertCustomerStatement(Customer customer, Connection connection) {
    try {
      PreparedStatement ps = connection.prepareStatement(
        "insert into customer_table (name, email) values (?, ?)",
        Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, customer.getPassword());
      ps.setString(2, customer.getEmail());
      return ps;
    } catch (SQLException e) {
      logger.error("Database error " + e.getMessage());
      return null;
    }
  }

  @Override
  public Customer save(Customer customer) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> insertCustomerStatement(customer, connection), keyHolder);
    int newKey = keyHolder.getKey().intValue();
    customer.setId(newKey);
    return  customer;
  }

  @Override
  public Optional<Customer> findCustomerById(int id) {
    List<Customer> customers = jdbcTemplate.query("select * from registered_customer where id = ?", new CustomerRowMapper(), id);
    if (customers.size() == 1) {
      return Optional.of(customers.get(0));
    }
    return Optional.empty();
  }

  @Override
  public Optional<Customer> findCustomerByEmail(String email) {
    List<Customer> customers = jdbcTemplate.query("select * from  where email = ?", new CustomerRowMapper(), email);
    if (customers.size() == 1) {
      return Optional.of(customers.get(0));
    }
    return Optional.empty();
  }

  private class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      String email = rs.getString("email");
      Customer customer = new Customer(name, email);
      customer.setId(id);
      return customer;
    }
  }
}
