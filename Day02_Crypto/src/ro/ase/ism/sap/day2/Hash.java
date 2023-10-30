package ro.ase.ism.sap.day2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Hash {
	
	public static byte[] getMessageDigest(String input) throws NoSuchAlgorithmException, NoSuchProviderException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		//use Bouncy Castle
		//MessageDigest md = MessageDigest.getInstance("SHA-1","BC");
		
		//compute the hash in one step
		return md.digest(input.getBytes());
		
		//alternative
		//md.update(input.getBytes());
		//return md.digest();
	}
	
	public static byte[] getFileMessageDigest(
			String filename, String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
		File file = new File(filename);
		if(!file.exists()) {
			throw new UnsupportedOperationException("Missing file");
		}
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		MessageDigest ms = MessageDigest.getInstance(algorithm, provider);
		
		byte[] buffer = new byte[8];
		int noBytesFromFile = 0;
		
		while((noBytesFromFile = bis.read(buffer)) != -1) {
			ms.update(buffer, 0, noBytesFromFile);
		}
		
		bis.close();
		
		return ms.digest();		
	}
	
	
	
	
	

}
