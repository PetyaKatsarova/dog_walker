// Created by huub
// Creation date 2023-08-13

package nl.hva.dogwalker.persistence.repository;
import nl.hva.dogwalker.business.domain.Dog;
import nl.hva.dogwalker.persistence.dao.DogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DogRepository {

  private final Logger logger = LoggerFactory.getLogger(DogRepository.class);
  private final DogDao dogDao;

  @Autowired
  public DogRepository(DogDao dogDao) {
    super();
    this.dogDao = dogDao;
    logger.info("New DogRepository");
  }

  public void save(Dog dog) {
    dogDao.save(dog);
  }

  public Optional<Dog> findDogById(int id) {
    return dogDao.findDogById(id);
  }

  public List<Dog> findAllDogsForCustomer(int id) {
    return dogDao.findAllDogsForCustomer(id);
  }
}
