package ro.ase.ism.sap.day3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {
	
	public static byte[] getHmac(String input, String secret, String algorithm) 
			throws NoSuchAlgorithmException, InvalidKeyException
	{
		Mac hmac = Mac.getInstance(algorithm);
		Key hmacKey = new SecretKeySpec(secret.getBytes(), algorithm);
		hmac.init(hmacKey);
		
		return hmac.doFinal(input.getBytes());
	}
	
	public static byte[] getFileHmac(
			String filename, String secret, String algorithm)
					throws NoSuchAlgorithmException, InvalidKeyException, IOException {
		
		File file = new File(filename);
		if(!file.exists()) {
			throw new UnsupportedOperationException("Missing file");
		}
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		Mac hmac = Mac.getInstance(algorithm);
		Key hmacKey = new SecretKeySpec(secret.getBytes(), algorithm);
		hmac.init(hmacKey);
		
		byte[] buffer = new byte[16];
		int noBytes = 0;
		
		while(true) {
			noBytes = bis.read(buffer);
			if(noBytes == -1) {
				break;
			}
			hmac.update(buffer, 0, noBytes);
		}
		
		bis.close();
		
		return hmac.doFinal();
		
	}
}
