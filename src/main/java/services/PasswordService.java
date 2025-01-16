package services;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordService {
    public String hashPassword(String rawPassword) {
        return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword).verified;
    }
}
