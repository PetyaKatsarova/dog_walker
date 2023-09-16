package nl.hva.dogwalker.util.security;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 24/08/2023 09:43
 */
import nl.hva.dogwalker.util.security.ByteArrayToHexHelper;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class SaltMaker {

    private SecureRandom    secureRandom;
    private int             saltLength;
    public static final int STANDARD_SALT_LENGTH = 8;
    public static final int MINIMUM_SALT_LENGTH = 1;
    public static final int MAXIMUM_SALT_LENGTH = 20;

    public SaltMaker(int saltLength) {
        setSaltLength(saltLength);
        secureRandom = new SecureRandom();
    }

    public SaltMaker() {
        this(STANDARD_SALT_LENGTH);
    }


    public String generateSalt() throws IllegalArgumentException{
        byte[]arr = new byte[saltLength];
        secureRandom.nextBytes(arr);
        return ByteArrayToHexHelper.encodeHexString(arr);
    }

    public void setSaltLength(int saltLength) {
        if(saltLength<MINIMUM_SALT_LENGTH || saltLength>MAXIMUM_SALT_LENGTH){
            throw new IllegalArgumentException("Salt length must be between"
                    + MINIMUM_SALT_LENGTH + " and " + (MAXIMUM_SALT_LENGTH+1));
        }else{
            this.saltLength = saltLength;
        }
    }
}
