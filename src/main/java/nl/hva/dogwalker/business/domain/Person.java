// Created by huub
// Creation date 2023-08-03

package nl.hva.dogwalker.business.domain;
// Voor web apps:
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {

//    Voor web apps:
  private final Logger logger = LoggerFactory.getLogger(Person.class);

  private String name;
  private String email;

  public Person() {
    super();
    logger.info("New Person");
  }

  public Person(String name, String email) {
    super();
    this.name = name;
    this.email = email;
    logger.info("New Person");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person = (Person) o;

    if (!getName().equals(person.getName())) return false;
    return getEmail().equals(person.getEmail());
  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getEmail().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Person{" +
      "name='" + name + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}
