// Created by huub
// Creation date 2023-08-01

package nl.hva.dogwalker.business.domain;
// Voor web apps:
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dog {

//    Voor web apps:
private final Logger logger = LoggerFactory.getLogger(Dog.class);

  private int id;
  private String name;
  private String breed;

  @JsonIgnore
  private Customer owner;
  private List<Walk> walks;

  public Dog(String name, String breed, Customer owner) {
    super();
    this.id = 0;
    this.name = name;
    this.breed = breed;
    this.owner = owner;
    this.walks = new ArrayList<>();
    logger.info("New Dog");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public Customer getOwner() {
    return owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void addWalk(Walk walk) {
    walks.add(walk);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Dog dog = (Dog) o;

    if (id != dog.id) return false;
    if (!name.equals(dog.name)) return false;
    if (!breed.equals(dog.breed)) return false;
    if (!owner.equals(dog.owner)) return false;
    return Objects.equals(walks, dog.walks);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + name.hashCode();
    result = 31 * result + breed.hashCode();
    result = 31 * result + owner.hashCode();
    result = 31 * result + (walks != null ? walks.hashCode() : 0);
    return result;
  }
}
