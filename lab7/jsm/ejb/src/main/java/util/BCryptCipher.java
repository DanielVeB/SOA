package util;


import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

@Stateless
public class BCryptCipher {

    public String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean checkPassword(String password, String hash){
        return BCrypt.checkpw(password,hash);
    }
}
