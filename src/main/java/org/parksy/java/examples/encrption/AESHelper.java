package org.parksy.java.examples.encrption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESHelper {

	private static final String ALGO = "AES"; 

	// TODO: Would want this in separate file??
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B',
		'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	private static Key generateKey() throws Exception  {
		return new SecretKeySpec(keyValue, ALGO);
	}	

	public static void main(String[] args) {

		AESHelper aes = new AESHelper();

		File inF = new File("file.txt");
		File outF = new File("Encrpted.txt");
		File unencryptF = new File("UnEncrypted.txt");

		try {
			aes.encrypt( inF, outF);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			aes.decrypt(outF, unencryptF);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String password = "mypassword";
		String passwordEnc;
		try {
			passwordEnc = aes.encrypt( password );
			String passwordDec = aes.decrypt( passwordEnc );

			System.out.println("Plain Text : " + password );
			System.out.println("Encrypted Text : " + passwordEnc );
			System.out.println("Decrypted Text : " + passwordDec );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void encrypt(File in, File out) throws Exception {

		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);

		FileInputStream is = new FileInputStream(in);
		CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), c);

		copy(is, os);

		os.close();
	}

	public void decrypt(File in, File out) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);

		CipherInputStream is = new CipherInputStream(new FileInputStream(in), c );
		FileOutputStream os = new FileOutputStream(out);

		copy(is, os);

		is.close();
		os.close();
	}

	private void copy(InputStream is, OutputStream os) throws IOException {
		int i;
		byte[] b = new byte[1024];
		while((i=is.read(b))!=-1) {
			os.write(b, 0, i);
		}
	} 	
	
	public String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] encVal = c.doFinal(Data.getBytes());
		return new BASE64Encoder().encode(encVal);
	}

	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		return new String(decValue);
	}
	

}
