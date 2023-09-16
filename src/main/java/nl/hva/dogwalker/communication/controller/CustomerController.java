//// Created by huub
//// Creation date 2023-08-01
//
//package nl.hva.dogwalker.communication.controller;
//import nl.hva.dogwalker.business.domain.Customer;
//import nl.hva.dogwalker.business.service.RegistrationService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/customer")
//public class CustomerController {
//
////    Voor web apps:
//  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
//
//  private final RegistrationService registrationService;
//
//  @Autowired
//  public CustomerController(RegistrationService registrationService) {
//    super();
//    this.registrationService = registrationService;
//    logger.info("Constructing CustomerController, regServ = " + registrationService.getClass().getName());
//    logger.info("New CustomerController");
//  }
//
//  @PostMapping("/signup")
//  public Customer customerRegistrationHandler(@RequestParam String username, @RequestParam String email) {
//    return registrationService.registerCustomer(new Customer(username, email));
//  }
//
//  @GetMapping("/{id}")
//  public Customer getCustomerByIdHandler(@PathVariable int id) {
//    Customer customer = registrationService.findCustomerByIdWithDogs(id);
//    return customer;
//  }
//
//  @PostMapping("/newDog")
//  public Customer addNewDogForCustomerHandler(
//    @RequestParam int customerId,
//    @RequestParam String name,
//    @RequestParam String breed) {
//    return registrationService.addNewDogForCustomer(customerId, name, breed);
//  }
//
//}
