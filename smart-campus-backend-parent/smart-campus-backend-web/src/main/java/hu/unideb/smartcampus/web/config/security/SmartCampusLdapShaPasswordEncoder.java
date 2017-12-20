package hu.unideb.smartcampus.web.config.security;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Delegates all reques to the original LdapShaPassword encoder.
 */
public class SmartCampusLdapShaPasswordEncoder implements PasswordEncoder {

    private LdapShaPasswordEncoder ldapShaPasswordEncoder = new LdapShaPasswordEncoder();

    public SmartCampusLdapShaPasswordEncoder() {
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return ldapShaPasswordEncoder.encodePassword(rawPassword.toString(), null);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ldapShaPasswordEncoder.isPasswordValid(encodedPassword, rawPassword.toString(), null);
    }

}
