package Utilities;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by zemoso014 on 27/10/15.
 */
public class Encryption {

    public static final String calculateHash(final String clearString) {
        if (clearString == null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }
        return BCrypt.hashpw(clearString, BCrypt.gensalt());
    }

    public static final boolean matchHash(final String candidate, final String encryptedPassword) {
        if (candidate == null) {
            return false;
        }
        if (encryptedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(candidate, encryptedPassword);
    }

}
