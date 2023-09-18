package nl.hva.dogwalker.business.service;

import nl.hva.dogwalker.persistence.repository.CustomerRepository;
import nl.hva.dogwalker.util.security.password.HashService;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 15:22
 */
public class LoginService {
    private final CustomerRepository    customerRepository;
    private final HashService           hashService;

    public LoginService(CustomerRepository customerRepository, HashService hashService) {
        this.customerRepository = customerRepository;
        this.hashService = hashService;
    }

//    public boolean authenticate(Customer customer) {
//        boolean CustomerCanEnter = false;
//        Customer loginCust = customerRepository.findCustomerByEmail(customer.getEmail());
//        if (loginCust != null) {
//            String hashedPassw = loginCust.getPassword();
//            String salt = loginCust.getSalt();
//            String toBechecked = getHa
//        }
//    }
}
