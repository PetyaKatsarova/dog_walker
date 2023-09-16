package nl.hva.dogwalker.business.service;

import nl.hva.dogwalker.business.domain.User;
import nl.hva.dogwalker.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 14:07
 */
@Service
public class UserService {

//    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public User findUserByEmail(String email) {
        try{
            return userRepository.findUserByEmail(email);
        } catch (NoSuchElementException ex){
            System.err.println("Exception in UserService: " + ex.getMessage());
            return null;
            // or could use:
//              System.err.println("Exception in UserService: " + ex.getMessage());
//        throw new NoSuchElementException("User with email " + email + " was not found in UserService.");
        }
    }
}
