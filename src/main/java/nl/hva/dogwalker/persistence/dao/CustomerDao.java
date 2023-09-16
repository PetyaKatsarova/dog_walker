package nl.hva.dogwalker.persistence.dao;

import nl.hva.dogwalker.business.domain.Customer;

import javax.swing.plaf.PanelUI;
import java.util.Optional;

public interface CustomerDao {

  public Customer save(Customer customer);

  public Optional<Customer> findCustomerById(int id);
  public Optional<Customer> findCustomerByEmail(String email);
}
