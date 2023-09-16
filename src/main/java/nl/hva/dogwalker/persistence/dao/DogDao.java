package nl.hva.dogwalker.persistence.dao;

import nl.hva.dogwalker.business.domain.Dog;

import java.util.List;
import java.util.Optional;

public interface DogDao {

  void save(Dog dog);

  Optional<Dog> findDogById(int id);

  List<Dog> findAllDogsForCustomer(int id);
}
