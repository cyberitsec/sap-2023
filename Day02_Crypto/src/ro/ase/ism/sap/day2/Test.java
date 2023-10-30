package ro.ase.ism.sap.day2;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
		
		//test if a provider is available
		
		//String providerName = "SUN";
		String providerName = "BC";
		
		Provider provider = Security.getProvider(providerName);
		if(provider != null) {
			System.out.println(providerName + " is available");
		} else {
			System.out.println(providerName + " is NOT available");
		}
		
		
		//load a provider at runtime - BouncyCastle
		if(provider == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		
		provider = Security.getProvider(providerName);
		if(provider != null) {
			System.out.println(providerName + " is available");
		} else {
			System.out.println(providerName + " is NOT available");
		}
		
		//test the Secure Random
		byte[] randomBytes = RandomGenerator.getSecureRandom(16);
		System.out.println("Secure random bytes");
		System.out.println(Util.getHexString(randomBytes));
		
		byte[] seed = {0x01, 0x02, 0x03};
		randomBytes = RandomGenerator.getSecureRandom(16, seed);
		System.out.println("Secure random bytes");
		System.out.println(Util.getHexString(randomBytes));
		
		//test hash algorithms
		byte[] hash = Hash.getMessageDigest("Hello! How are you ?");
		System.out.println("SHA1: ");
		System.out.println(Util.getHexString(hash));
		
		byte[] fileHash = Hash.getFileMessageDigest("message.txt", "MD5", "BC");
		System.out.println("File MD5: ");
		System.out.println(Util.getHexString(fileHash));
	}

}
