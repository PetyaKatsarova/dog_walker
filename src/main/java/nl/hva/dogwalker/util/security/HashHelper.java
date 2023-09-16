package nl.hva.dogwalker.util.security;

import nl.hva.dogwalker.util.security.ByteArrayToHexHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 09:06
 */

@Component
public class HashHelper {
    public static final String HACH_ALGORITM = "SHA-256";

    public static String hash(String password){
        String noSalt = "";
        return hash(password,noSalt);
    }

    public static String hash(String password, String salt){
        String noPepper = "";
        return hash(password, salt, noPepper);
    }

    public static String hash(String password, String salt, String pepper){
        String pepper1 = "";
        String pepper2 = pepper;
        if (pepper.length()>=2){
            // cut pepper into 2 and append to beggining and end of password
            pepper1 = pepper.substring(0, (pepper.length()/2));
            pepper2 = pepper.substring((pepper.length()/2));
        }
        byte[] digest = getPasswordBytes(salt, pepper1, password, pepper2);
        return ByteArrayToHexHelper.encodeHexString(digest);
    }

    private static byte[] getPasswordBytes(String salt, String pepper1, String password, String pepper2) {
        MessageDigest md = getMessageDigest(HACH_ALGORITM);
        // hashes all params. with sha_256 algorithm and after that can store in db
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        md.update(pepper1.getBytes(StandardCharsets.UTF_8));
        md.update(password.getBytes(StandardCharsets.UTF_8));
        md.update(pepper2.getBytes(StandardCharsets.UTF_8));
        return md.digest();
    }

    private static MessageDigest getMessageDigest(String algorithmName) {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (Exception ex) {
            System.out.println("Algorithm not found error: " + ex.getMessage());
            return null;
        }
    }
}
