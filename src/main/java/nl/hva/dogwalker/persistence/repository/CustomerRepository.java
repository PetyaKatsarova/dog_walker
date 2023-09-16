// Created by huub
// Creation date 2023-08-03

package nl.hva.dogwalker.persistence.repository;
import nl.hva.dogwalker.business.domain.Customer;
import nl.hva.dogwalker.business.domain.Dog;
import nl.hva.dogwalker.persistence.dao.CustomerDao;
import nl.hva.dogwalker.persistence.dao.DogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {

  private final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
  private final CustomerDao customerDao;
  private final DogDao dogDao;

  public CustomerRepository(CustomerDao customerDao, DogDao dogDao) {
    super();
    this.customerDao = customerDao;
    this.dogDao = dogDao;
    logger.info("New CustomerRepository");
  }

  public Customer findCustomerByIdWithDogs(int id) {
    Optional<Customer> customerOption = customerDao.findCustomerById(id);
//    Customer customer = customerOption.orElse(null);
    if (customerOption.isPresent()) {
      Customer customer = customerOption.get();
      List<Dog> dogs = dogDao.findAllDogsForCustomer(id);
      for (Dog dog: dogs) {
        dog.setOwner(customer);
      }
      customer.setDogs(dogs);
      return customer;
    }
    return null;
  }

  public Customer save(Customer customer) {
    return customerDao.save(customer);
  }

  public Customer findCustomerById(int id) {
    Optional<Customer> customerOptional = customerDao.findCustomerById(id);
    return customerOptional.orElse(null);
  }
  public Customer findCustomerByEmail(String email) {
    return customerDao.findCustomerByEmail(email).orElse(null);
  }

  /**
   * Description:
   * Author: Petya Katsarova
   * Email: pskpetya@gmail.com
   * Created on: 24/08/2023 15:55
   */
  public static class MapDatabase {
      // Heel simpele opslag van gebruikersnaam en wachtwoord
      private Map<String, String> db;
      public MapDatabase() {
          db = new ConcurrentHashMap<>();
  // vul de database zelf met test data
  // denk eraan deze hashen
      }
      public String findHashByUsername(String username) {
          return db.get(username);
      }
      public boolean insertUsernameWithHash(String username, String hash){
          if(!db.containsKey(username)){ // controleer of de nieuw aan te maken key al bestaat
              db.put(username, hash);
              return true;
          }
          return false;
      }
  }
}
