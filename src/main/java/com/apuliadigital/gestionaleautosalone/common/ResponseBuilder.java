package com.apuliadigital.gestionaleautosalone.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public final class ResponseBuilder {

    private static final int successCode = 200;
    private static final int errorCode = 500;
    private static final int notFoundCode = 404;

    public static ResponseEntity<?> success(Object data) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> response = new HashMap<>();

        response.put("success", true);
        response.put("data", data);

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response, headers, successCode);
    }

    public static ResponseEntity<?> notFound(String message) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> response = new HashMap<>();

        response.put("success", false);
        response.put("message", message);

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response, headers, notFoundCode);
    }

    public static ResponseEntity<?> error() {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> response = new HashMap<>();

        response.put("success", false);
        response.put("message", "Something went wrong");

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response, headers, errorCode);
    }

    public static ResponseEntity<?> deleted(String message) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> response = new HashMap<>();

        response.put("success", true);
        response.put("message", message);

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response, headers, successCode);
    }
}
