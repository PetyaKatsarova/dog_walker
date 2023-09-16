package nl.hva.dogwalker.communication.controller;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 20/08/2023 20:03
 */
import nl.hva.dogwalker.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Controller;

//@Controller
//public class UserController {
//
//    private final CustomerRepository.MapDatabase database = new CustomerRepository.MapDatabase();

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public ModelAndView authenticate(@RequestParam String username, @RequestParam String password) {
//        Hash hashedPassword = database.findHashByUsername(username);
//
//        if (hashedPassword != null && hashedPassword.getValue().equals(HashHelper.hashPassword(password))) {
//            ModelAndView mv = new ModelAndView("welcome");
//            mv.addObject("username", username);
//            return mv;
//        }
//        ModelAndView mv = new ModelAndView("login");
//        mv.addObject("error", "Invalid credentials");
//        return mv;
//    }
//}

