package com.vicente.utils;

import com.vicente.exception.EsapiException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by vicente on 10/11/16.
 */
@Component
public class CryptoUtils {
    private static final String myEncryptionKey= "3s4p1s3CR3TK3yTt";


    public String encrypt(String strToEncrypt) throws EsapiException
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(myEncryptionKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final String encryptedString = Base64.encodeBase64URLSafeString(cipher.doFinal(strToEncrypt.getBytes()));
            return encryptedString;
        }
        catch (Exception e)
        {
            throw new EsapiException("esapi.validation.internal.error",e.getCause());
        }

    }

    public String decrypt(String strToDecrypt) throws EsapiException
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(myEncryptionKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
            return decryptedString;
        }
        catch (Exception e)
        {
            throw new EsapiException("esapi.validation.internal.error",e.getCause());

        }
    }
}
