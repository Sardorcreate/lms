package com.company.util;

import com.company.dto.JWTDto;
import com.company.enums.Role;
import com.company.exception.AllException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JWTUtil {
    public static String secretKey="salomjhbshbfsuegbfvakkfnhbfhueeygfbsbvead64465";
    public static String encode(Integer id, String login, Role role) {

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + ( 60 * 60 * 1000)));
        jwtBuilder.setIssuer("Sardor");
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.claim("id", id);
        jwtBuilder.claim("login", login);
        jwtBuilder.claim("role", role.toString());

        String jwt = jwtBuilder.compact();

        return jwt;
    }


    public static JWTDto decode (String token) {

        JWTDto jwtDto = new JWTDto();

        Integer id = null;
        String login = null;
        String role = null;

        try {
            JwtParser build = Jwts.parser().setSigningKey(secretKey).build();
            Claims claims = build.parseClaimsJws(token).getBody();

            id = (Integer) claims.get("id");
            login = (String) claims.get("login");
            role = (String) claims.get("role");

            Role role1 = Role.valueOf(role);

            jwtDto.setId(id);
            jwtDto.setLogin(login);
            jwtDto.setRole(role1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new AllException("Token is incorrect!!!");
        }

        return jwtDto;
    }
}
