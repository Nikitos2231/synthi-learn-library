package com.synthilearn.securestarter;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class AccessToken implements Serializable {

    private final Claims payload;

    public String getSub() {
        return payload.getSubject();
    }
}
