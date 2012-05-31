package org.parksy.java.examples.encrption;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Basic IO example with CTR using AES
 * http://www.java2s.com/Code/Java/Security/BasicIOexamplewithCTRusingAES.htm
 */
public class AESwCTRExample {

	private static final byte[] keyBytes = new byte[] {
		'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' 
		};  
	
	public static void main(String[] args) throws Exception {
    //Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());        
    byte[] input = "Sam Sneed Sat Smartly".getBytes();
    
  
    System.out.println("Key Length: " + keyBytes.length );

    byte[] ivBytes = new byte[] { 0x02, 0x07, 0x01, 0x03, 0x01, 0x0B, 0x02, 0x03, 0x0E, 0x03, 0x08,
            0x0A, 0x09, 0x00, 0x05, 0x01 };

    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
//    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding"); // , "BC" -- Was for Bouncy Castle Provider
    Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding"); // , "BC" -- Was for Bouncy Castle Provider
    System.out.println("input : " + new String(input));

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    ByteArrayInputStream bIn = new ByteArrayInputStream(input);
    CipherInputStream cIn = new CipherInputStream(bIn, cipher);
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    int ch;
    while ((ch = cIn.read()) >= 0) {
      bOut.write(ch);
    }

    byte[] cipherText = bOut.toByteArray();

    System.out.println("cipher: " + new String(cipherText));

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    bOut = new ByteArrayOutputStream();
    CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
    cOut.write(cipherText);
    cOut.close();
    System.out.println("plain : " + new String(bOut.toByteArray()));
  }
}
