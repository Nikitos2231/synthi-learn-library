package com.synthilearn.securestarter;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
@AllArgsConstructor
public class AccessToken implements Serializable {

    private final Claims payload;

    public String getEmail() {
        return payload.getSubject();
    }

    public UUID getId() {
        return UUID.fromString(payload.getId());
    }
}
