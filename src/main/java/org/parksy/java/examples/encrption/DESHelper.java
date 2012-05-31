package org.parksy.java.examples.encrption;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class DESHelper {
	private static Cipher encryptCipher;
	private static Cipher decryptCipher;

	// 8-byte Salt
    private byte[] salt = {
            (byte)0x03, (byte)0x56, (byte)0x9B, (byte)0x32,
            (byte)0xC8, (byte)0x35, (byte)0xE3, (byte)0xA9
        };
    
    private static String passPhraseDefault = "S7hYRUWJQa";

    // Iteration count
    int iterationCount = 19;
    

	public static void main(String args[]) throws Exception {
		
		DESHelper desHelper = new DESHelper();
		
		final String testEncrypt = "This is my Secret String";
		String  result = desHelper.encrypt(testEncrypt);
		assert ( testEncrypt.equals( desHelper.decrypt(result) ) );
		System.out.println(desHelper.decrypt(result));
		

	}
	
	public DESHelper(String passPhrase) {
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance( "PBEWithMD5AndDES" ).generateSecret(keySpec);
            encryptCipher = Cipher.getInstance(key.getAlgorithm());
            decryptCipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            // Create the ciphers
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            decryptCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            
            //TODO Put in Proper Error Handling...
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }	

	public DESHelper() {
		this( passPhraseDefault );

	}

	
    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = encryptCipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        }
        //TODO Put in Proper Error Handling...
        return null;
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = decryptCipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }

        //TODO Put in Proper Error Handling...
        return null;
    }	

}