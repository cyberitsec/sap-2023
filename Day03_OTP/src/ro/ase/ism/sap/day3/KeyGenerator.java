package ro.ase.ism.sap.day3;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//do we need a seed ?
//do we need to continue to generate the sequence ?
public class KeyGenerator {
	
	private byte[] seed;
	private String algorithm;
	SecureRandom secureRandom = null;
	
	public KeyGenerator(byte[] seed, String algo) {
		super();
		this.seed = seed;
		this.algorithm = algo;
	}

	public byte[] getRandomBytes(int noBytes) throws NoSuchAlgorithmException {
		if(secureRandom == null) {
			secureRandom = SecureRandom.getInstance(this.algorithm);
			secureRandom.setSeed(this.seed);
		}
		
		byte[] random = new byte[noBytes];
		secureRandom.nextBytes(random);
		return random;	
	}
}
