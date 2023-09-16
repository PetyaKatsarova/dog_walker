package nl.hva.dogwalker.exception;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 17:18
 */
public class PasswordNotValidException extends IllegalArgumentException{
    public PasswordNotValidException(String message) {
        super(message);
    }
}
