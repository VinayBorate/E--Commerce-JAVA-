package com.vinay.util;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.spec.MGF1ParameterSpec;

public class RSAUtil_DECRYPTION {

	
	// WE WILL USE THI CLASS TO DECYPT THE HASH 
	
	
    // Private Key in PEM format
    private static final String PRIVATE_KEY_PEM = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDranuHtiGEXErmYJTBDoqIbZA6Z+thzJlCo0+DLVH4Ekzm1XsabLJgCzdG5fHfNgdZNg5IX7SRqkIyixaBHp1KlgWps0cWYZcTMWKFOB/Xl9WZkX5P3olChPPo3Zv98+YicySqBLeghs7uzYpiO8yCEUIwtmF4yGIx50QucjeSf5C3kQl04BJQIcRPSoudnO1IdJsJezkveDf7gew30H1wT6lXMVqI0GDR402al6D4cSHECaimHkmFmVt1sJTMNH4gmOMKvd15mVEE8oQtej7u2ri1xsDDEl7/F1lse+R+d/Rzkf0zXz3tEqHwclYMwBd/ydGUbSC/kj7DhDrmw+evAgMBAAECggEBAK/BL7iCIUPQI5sbP/NiB8sTATqZmwHI3iMCCbrFewzZnNA5UU7JF+LcfMhRo+IhVBsmYENoMEcP9ZwGeyaq1IN6vX+UeN4cvc12cTdET4GFudKM7OB0I8WlvPN9f5h5HrdZf2XvX6hY/OUMTuRIAMaaW/BCOUMTVzwqjJygaRzU8UPUQ5yRXwMH8giXTcgbo4Q3rqK4hxtIiYyVqB1uwY2OCRKRZmoqP+Sy3ZtTjxsgM8PZCmlfdjeb9AkIn+SZmpn7u/vk9f9bV9y4gllD5E6NcPKyHbu0AAQVIRFjOOy/36VNcaF0mS4Ls7jGEXJemaKwleLs+2PoL5GIKXEhZAECgYEA/u8hg4fnwko1Cc78fdNfeimco98NqUno8i+S9qt0L48dZhhlxY+LL3v3O0Mhww0R6+6smNd0mHHc5zBjqoMI4nX1kXJ7NAccR62g6B4UJ3hAkDSprctHnyV0XkAFuYIW4ASfqgPyCh4dyzPH/Ag+kVRWITDY0ifaVPUMReZIMp8CgYEA7GZ12LcdgU7jPgOwN9Fj5c+BztguuXupiKhxR1JPat1DjpXjOepIJ2EKsv/46OSYOdSgdhgc4q7ZMPAYoWJ8exUsfwMTWmXXKNwBP4vEMyj9R3pmfWGDyBh6xWUHxx07rZq+ovik/ApeT24sZBpa89RVwgjRQp3lBHXxCPYLwPECgYEAtqfHL2J0nzRkA/8V0WFdCz49SPxL4XtfOAb2GV6HBHxZ6AibAo3yf7cmUIOs8P+wU4bSiz/z/jW2vL5i6oywFPHHXMrVk1V04bZ5j1XdwtnaCHhzCl6XlmNTmSPt9hhLI36slzf0d2NUnX8gTmfpzF7Mx0MkVo7PVAevH3X7TKkCgYEAlQSBhPrpPnncBVuN2aHEtzj3OsxLp+CsHdkQj0TziwmvbFJPylJE6Y3ikLOK1ERFMS83dzn7+2j8wDyZSiIrFetjAS2PJYzM9JS6tLQGGbnc7xHZSXAPAjNsMMJfBMUIuYayZUkXt0lR6gApO3DhpJJU/NtSDgw6MXWnW16+SgECgYEA9NI1jjDqpfP1V/2cIdaXckvqoIyFgwq6YVBuYxOBYnWp/tyFF92oh7xhUREHobmKskk6HCxLAaFphHCWT1+4eZNyldS3BvOop7PZ/VKMbYjpXOzO0PYXLqD6FYEBA+MERESPXXpxNZozsnAi0+iRDJw9ZYDHOcPncJG34qRwXEE=" ;
    
    // pUBLIC KEY (FOR FRONTEND )
  //  private static final String PUBLIC_KEY_PEM = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA62p7h7YhhFxK5mCUwQ6KiG2QOmfrYcyZQqNPgy1R+BJM5tV7GmyyYAs3RuXx3zYHWTYOSF+0kapCMosWgR6dSpYFqbNHFmGXEzFihTgf15fVmZF+T96JQoTz6N2b/fPmInMkqgS3oIbO7s2KYjvMghFCMLZheMhiMedELnI3kn+Qt5EJdOASUCHET0qLnZztSHSbCXs5L3g3+4HsN9B9cE+pVzFaiNBg0eNNmpeg+HEhxAmoph5JhZlbdbCUzDR+IJjjCr3deZlRBPKELXo+7tq4tcbAwxJe/xdZbHvkfnf0c5H9M1897RKh8HJWDMAXf8nRlG0gv5I+w4Q65sPnrwIDAQAB";
    
 

    public  String decrypt(String encrypted) throws Exception {
    	
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        String privateKeyPem = PRIVATE_KEY_PEM;
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPem);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        // Use OAEPParameterSpec to match the JavaScript encryption
        OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                "SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT
        );

        cipher.init(Cipher.DECRYPT_MODE, privateKey, oaepParams);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
