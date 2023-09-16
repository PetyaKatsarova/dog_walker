package nl.hva.dogwalker.persistence.dao;

import nl.hva.dogwalker.business.domain.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JdbcCustomerDaoTest {

  private final JdbcTemplate jdbcTemplate;
  private final CustomerDao daoUnderTest;

  @Autowired
  public JdbcCustomerDaoTest(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
    this.daoUnderTest = new JdbcCustomerDao(jdbcTemplate);
  }

  @Test
  void save() {
    Customer dagobert = new Customer("Dagobert Duck", "dagobert@geld.pakhuis.nl");
    assertThat(dagobert.getId()).isEqualTo(0);
    daoUnderTest.save(dagobert);
    assertThat(dagobert.getId()).isNotEqualTo(0).isEqualTo(4);
    Optional<Customer> c = daoUnderTest.findCustomerById(4);
    assertThat(c).isNotNull().isNotEmpty().contains(dagobert);
  }

  @Test
  void findCustomerById() {
    Customer expected = new Customer("Donald Duck", "donald@disney.com");
    expected.setId(1);
    Optional<Customer> actual = daoUnderTest.findCustomerById(1);
    assertThat(actual).isNotNull().isNotEmpty().contains(expected);
  }
}