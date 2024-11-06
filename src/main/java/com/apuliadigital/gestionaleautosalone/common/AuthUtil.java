package com.apuliadigital.gestionaleautosalone.common;

import java.util.Base64;

public class AuthUtil {

    public static String[] extractCredentials(String authorizationBasicHeader) {

        if (authorizationBasicHeader == null || !authorizationBasicHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Authorization basic header is not correct");
        }

        String b64Credentials = authorizationBasicHeader.substring("Basic ".length());

        byte[] decodedBytes = Base64.getDecoder().decode(b64Credentials);
        String decodedString = new String(decodedBytes);

        String[] parts = decodedString.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Authorization basic header is not correct");
        }

        return parts;
    }
}
