package com.synthilearn.securestarter.services;

import io.jsonwebtoken.Claims;

public interface TokenService {
    Claims extractPayload(String token);
}