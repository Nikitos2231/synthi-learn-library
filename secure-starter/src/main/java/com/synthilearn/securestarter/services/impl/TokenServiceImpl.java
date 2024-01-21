package com.synthilearn.securestarter.services.impl;

import com.synthilearn.securestarter.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9JbOfqTtcqCuE9qOd2xhErJc6l3N5piM2VeMcCol9xV0fzbAXEssSWbVuS+nQ2Taah4CM+C0875xMOsJv3/Xve9Ee6RrSBeAq/HAA0Rxo9kEePaNuhg/5JPa6UnbRonbq977zzP9cAW5uMU4X9qeQ3dLuMFtCsIXZX1SEOLxqSTg78G/BL8rxIHUjAljBoh02Qe0P+mdNUzGqP1TirkQgxr3KZ56XEEa5mng7KKSTDeuxqJ97ksZU5VUFO5Q2AHNUzEI6LoXGgUBbvk1s9vzMLwD43Hptm9WT6dQA/tbF22nITa/93z9LrLl8D9lhAAlqk6egv38PUc5gdHxtezASQIDAQAB";
    private final PublicKey publicKey = getKey();

    private PublicKey getKey() {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            log.error("Invalid public key: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("Invalid public key encoding: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Unexpected error has occurred {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Claims extractPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
