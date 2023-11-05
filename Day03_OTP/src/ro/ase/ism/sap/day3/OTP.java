package ro.ase.ism.sap.day3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class OTP {
	public static byte[] encryptDecrypt(byte[] input, byte[] key) {
		if(input.length != key.length) {
			throw new UnsupportedOperationException("Different key size!");
		}
		byte[] output = new byte[input.length];
		for(int i = 0;i < input.length; i++) {
			output[i] = (byte) (input[i] ^ key[i]);
		}
		return output;
	}
	
	public static void encryptFile(
			String inputFilename, 
			String outputFilename, 
			String keyFilename,
			KeyGenerator keyGenerator) throws IOException, NoSuchAlgorithmException {
		
		//opening the input file
		File input = new File(inputFilename);
		if(!input.exists()) {
			throw new UnsupportedOperationException("File is missing");
		}
		
		FileInputStream fis = new FileInputStream(input);
		
		//open the cipher file
		File cipher = new File(outputFilename);
		if(!cipher.exists()) {
			cipher.createNewFile();
		}
		FileOutputStream fosCipher = new FileOutputStream(cipher);
		
		//open the key file
		File key = new File(keyFilename);
		if(!key.exists()) {
			key.createNewFile();
		}
		FileOutputStream fosKey = new FileOutputStream(key);
		

		byte[] buffer = new byte[16];
		int noBytes = 0;
		while(true) {
			noBytes = fis.read(buffer);
			if(noBytes == -1) {
				break;
			}
			byte[] keyStream = keyGenerator.getRandomBytes(noBytes);		
			byte[] cipherStream = encryptDecrypt(
					Arrays.copyOfRange(buffer, 0, noBytes), keyStream);
			
			fosCipher.write(cipherStream);
			fosKey.write(keyStream);
		}
		fosCipher.close();
		fosKey.close();
		fis.close();
	}
	
	public static void decryptFile(
			String cipherFilename, String keyFilename, String outputFilename) throws IOException {
		File cipher = new File(cipherFilename);
		File key = new File(keyFilename);
		if(!cipher.exists() || !key.exists()) {
			throw new UnsupportedOperationException("Missing input files");
		}
		
		File output = new File(outputFilename);
		if(!output.exists()) {
			output.createNewFile();
		}
		
		FileInputStream cipherStream = new FileInputStream(cipher);
		FileInputStream keyStream = new FileInputStream(key);
		FileOutputStream outputStream = new FileOutputStream(output);
		
		byte[] cipherBuffer = new byte[16];
		byte[] keyBuffer = new byte[16];
		
		while(true) {
			int noCipherBytes = cipherStream.read(cipherBuffer);
			int noKeyBytes = keyStream.read(keyBuffer);
			if(noCipherBytes == -1 || noKeyBytes == -1) {
				break;
			}
			if(noCipherBytes < noKeyBytes) {
				noKeyBytes = noCipherBytes;
			} else {
				noCipherBytes = noKeyBytes;
			}
			
			byte[] outputBuffer = encryptDecrypt(
					Arrays.copyOfRange(cipherBuffer, 0, noCipherBytes),
					Arrays.copyOfRange(keyBuffer, 0, noKeyBytes));
			
			outputStream.write(outputBuffer);
		}
		
		outputStream.close();
		keyStream.close();
		cipherStream.close();
		
	}
	
	

}
