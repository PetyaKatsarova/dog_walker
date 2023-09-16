// Created by huub
// Creation date 2023-08-01

package nl.hva.dogwalker.business.domain;
// Voor web apps:
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer { // extends Person removed to be simpler

//    Voor web apps:
  private final Logger  logger = LoggerFactory.getLogger(Customer.class);
  private String        password;
  private String        email;
  private int           id;
  private boolean       isVerified;
  private String        salt;
  private List<Dog>     dogs;

  public Customer() {
    super();
    this.id = 0;
    this.email = "";
    this.password = "";
    this.isVerified = false;
    this.salt = null;;
    logger.info("New Customer");
  }

  public Customer(String email, String password) {
    super();
    this.id = 0;
    this.email = email;
    this.password = password;
    this.isVerified = false;
    this.salt = null;
    logger.info("New Customer created");
  }

  public Customer(String email, String password, String salt) {
    super();
    this.id = 0;
    this.email = email;
    this.password = password;
    this.isVerified = false;
    this.salt = salt;
    logger.info("New Customer created");
  }

  public void registerDog(Dog dog) {
    dogs.add(dog);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Dog> getDogs() {
    return dogs;
  }

  public void setDogs(List<Dog> dogs) {
    this.dogs = dogs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Customer customer = (Customer) o;

    if (!super.equals(o)) return false;
    if (getId() != customer.getId()) return false;
    return getDogs().equals(customer.getDogs());
  }

//  @Override
//  public int hashCode() {
//    int result = getId();
//    result = 31 * result + getDogs().hashCode();
//    return result;
//  }

  @Override
  public String toString() {
    return "Customer{" +
      super.toString() +
      "id=" + id +
      ", dogs=" + dogs +
      '}';
  }

  public Logger getLogger() {
    return logger;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isVerified() {
    return isVerified;
  }

  public void setVerified(boolean verified) {
    isVerified = verified;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

}
