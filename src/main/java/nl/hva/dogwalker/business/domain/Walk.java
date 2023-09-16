// Created by huub
// Creation date 2023-08-03

package nl.hva.dogwalker.business.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Walk {

  private final Logger logger = LoggerFactory.getLogger(Walk.class);

  private LocalDateTime date;
  private List<Dog> dogs;
  private Employee walker;

  public Walk() {
    super();
    logger.info("New Walk");
  }

  public Walk(LocalDateTime date, Employee walker) {
    super();
    this.date = date;
    this.walker = walker;
    this.dogs = new ArrayList<>();
    logger.info("New Walk");
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Employee getWalker() {
    return walker;
  }

  public void setWalker(Employee walker) {
    this.walker = walker;
  }

  public void registerDogforWalk(Dog dog) {
    dogs.add(dog);
  }
}
