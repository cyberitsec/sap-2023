package ro.ase.ism.sap.day3;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Test {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IOException, InvalidKeySpecException {
		// test hmac
		
		byte[] hmacValue = 
				HMAC.getHmac("This is a secret !", "1234", "HmacSHA256");
		System.out.println("HMAC: ");
		System.out.println(Util.getHexString(hmacValue)); 
		
		
		hmacValue = HMAC.getFileHmac(
				"msg.txt", "12345678", "HmacMD5");
		System.out.println("File HMAC: ");
		System.out.println(Util.getHexString(hmacValue)); 
		
		// test pbkdf
		
		byte[] saltedHash = PBKDF.getPBKDF("12345678", 
				"PBKDF2WithHmacSHA256", "ism", 100);
		System.out.println("Salted hash of 12345678: ");
		System.out.println(Util.getHexString(saltedHash));
		
		//benchmark sha2 vs BKDF2WithHmacSHA256
		
		double tStart = System.currentTimeMillis();
		byte[] hashValue = Hash.getHash("12345678", "SHA-256");
		double tEnd = System.currentTimeMillis();
		
		System.out.println("SHA2 of 12345678 is ");
		System.out.println(Util.getHexString(hashValue));
		System.out.println(String.format(
				"Done in %f millis", tEnd - tStart));
		

		tStart = System.currentTimeMillis();
		saltedHash = PBKDF.getPBKDF("12345678", 
				"PBKDF2WithHmacSHA256", "ism", 15000);
		tEnd = System.currentTimeMillis();
		
		System.out.println("PBKFD SHA2 of 12345678 is ");
		System.out.println(Util.getHexString(saltedHash));
		System.out.println(String.format(
				"Done in %f millis", tEnd - tStart));
		
	}

}
