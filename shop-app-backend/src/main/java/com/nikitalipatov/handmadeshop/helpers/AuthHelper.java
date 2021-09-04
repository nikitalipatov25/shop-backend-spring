package com.nikitalipatov.handmadeshop.helpers;

import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public final class AuthHelper {

    private AuthHelper() {
    }

    public static String getUsernameFromToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7, header.length());
        String username = Jwts.parser().setSigningKey("bezKoderSecretKey").parseClaimsJws(token).getBody().getSubject();
        return username;
    }
}
