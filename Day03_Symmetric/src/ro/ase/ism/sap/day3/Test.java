package ro.ase.ism.sap.day3;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Test {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException {
		
		
		//test ECB
		
		CipherECB.encrypt("msg.txt", "msg.enc", "password12345678", "AES");
		
		//example with a 256 bit key but with a block size of 128 bits
		//CipherECB.encrypt("msg.txt", "msg.enc", "password12345678password12345678", "AES");
		
		CipherECB.decrypt("msg.enc", "msg2.txt", "password12345678", "AES");
		
		//test CBC
		CipherCBC.encrypt("msg.txt", "msgCBC.enc", "password12345678", "AES");
		CipherCBC.decrypt("msgCBC.enc", "msg3.txt", "password12345678", "AES");
		
		System.out.println("Done.");
		
		//test CTR
		CipherCTR.encrypt("msg.txt", "msgCTR.enc", "password12345678", "AES");
		CipherCTR.decrypt("msgCTR.enc", "msg4.txt", "password12345678", "AES");
	
		//test CTS
		CipherCTS.encrypt("msg.txt", "msgCTS.enc", "password12345678", "AES");
		CipherCTS.decrypt("msgCTS.enc", "msg5.txt", "password12345678", "AES");
		
	}

}
