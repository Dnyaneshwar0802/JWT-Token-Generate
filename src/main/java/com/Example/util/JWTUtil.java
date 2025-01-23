package com.Example.util;

import com.Example.config.PersonPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JWTUtil {
    private String jwtSecretkey = "DGTVCWHSjbn#cfgvnbm$tscxgvz*dcxg!$cvjxcgfvhb#fccghvcx";

    public String jwtTokenGenerate(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretkey.getBytes(StandardCharsets.UTF_8));
      /* Had Error for this line cause it need UserPrincipal which Extends
      User Details
       Person person =(Person) authentication.getPrincipal();
       */
        PersonPrincipal personPrincipal = (PersonPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject((personPrincipal.getUsername()))
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .setIssuedAt(new Date(new Date().getTime()))
                .setExpiration(new Date((new Date()).getTime() + 2 * 60 * 60 * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritySet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritySet.add(authority.getAuthority());
        }
        return String.join(",", authoritySet);
    }

}
