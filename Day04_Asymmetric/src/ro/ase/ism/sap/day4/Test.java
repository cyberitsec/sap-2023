package ro.ase.ism.sap.day4;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class Test {
	
	public static String getHexString(byte[] value) {
		StringBuilder result = new StringBuilder();
		result.append("0x");
		for(byte b : value) {
			result.append(String.format(" %02X", b));
		}
		return result.toString();
	}

	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {

		KeyStore ks = KeyStoreManager.getKeyStore(
				"ismkeystore.ks", "passks", "pkcs12");
		KeyStoreManager.list(ks);
		
		PublicKey pubIsm1 = KeyStoreManager.getPublicKey("ismkey1", ks);
		PrivateKey privIsm1 = KeyStoreManager.getPrivateKey("ismkey1", "passks", ks);
		
		System.out.println("Public key:");
		System.out.println(getHexString(pubIsm1.getEncoded()));
		System.out.println("Private key");
		System.out.println(getHexString(privIsm1.getEncoded()));
		
		PublicKey pubIsm1FromCert = 
				PublicCertificate.getCertificateKey("ISMCertificateX509.cer");
		System.out.println("Public key from certificate: ");
		System.out.println(getHexString(pubIsm1FromCert.getEncoded()));
		
	}

}
