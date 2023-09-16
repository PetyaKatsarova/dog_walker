// Created by huub
// Creation date 2023-08-03

package nl.hva.dogwalker.business.domain;
// Voor web apps:

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {

  private final Logger logger = LoggerFactory.getLogger(Employee.class);

  private LocalDate start;
  private List<Walk> walks;

  public Employee(LocalDate start) {
    super();
    this.start = start;
    this.walks = new ArrayList<>();
    logger.info("New Employee");
  }

  public LocalDate getStart() {
    return start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public void addWalk(Walk walk) {
    walks.add(walk);
  }
}
