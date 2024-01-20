package ro.ase.ism.sap.day4;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

public class AESCipher {

	public static byte[] generateKey(int noBytes) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = 
				KeyGenerator.getInstance("AES");
		keyGenerator.init(noBytes);
		return keyGenerator.generateKey().getEncoded();
	}
	
}
