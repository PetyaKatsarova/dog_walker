package nl.hva.dogwalker.util.security.password;

import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 09:45
 */
@Service
public class PepperService {
    private static final String PEPPER = "addingPepper";
    public String getPepper() { return PEPPER; }
}
