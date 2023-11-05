package ro.ase.ism.sap.day3;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		String secretSeed = "randomKEY1234";
		KeyGenerator keyGenerator = new KeyGenerator(
				secretSeed.getBytes(), "SHA1PRNG");
		
		OTP.encryptFile("msg.txt", "msg.otp", "secretkey.key",keyGenerator);
		OTP.decryptFile("msg.otp", "secretkey.key", "msg2.txt");
		
		
		//you get the key
		OTP.decryptFile("msg.otp", "msg.txt", "key.txt");
		
		System.out.println("Done");
	}

}
