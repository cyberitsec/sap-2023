package ro.ase.ism.sap.day4;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Test {
	
	public static String getHexString(byte[] value) {
		StringBuilder result = new StringBuilder();
		result.append("0x");
		for(byte b : value) {
			result.append(String.format(" %02X", b));
		}
		return result.toString();
	}

	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SignatureException {

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
		
		//encrypt and decrypt with asymmetric ciphers - RSA
		//generate a random AES key and encrypt it with public RSA key
		//decrypt AES key with RSA private key
		
		byte[] randomAESKey = AESCipher.generateKey(128);
		System.out.println("AES Random key: ");
		System.out.println(getHexString(randomAESKey));
		
		byte[] encryptedAESKey = 
				RSACipher.encrypt(pubIsm1FromCert, randomAESKey);
		
		System.out.println("Encrypted AES key with RSA: ");
		System.out.println(getHexString(encryptedAESKey));
		
		byte[] randomAESKeyCopy = 
				RSACipher.decrypt(privIsm1, encryptedAESKey);
		System.out.println("AES Key copy: ");
		System.out.println(getHexString(randomAESKeyCopy));
		
		
		//digital signatures
		//generate a digital signature (RSA) for a file with private key
		//validate the digital signature with public key
		
		byte[] signature = 
				RSACipher.signFile("msg.txt", privIsm1);
		
		System.out.println("Digital signature value: ");
		System.out.println(getHexString(signature));
		
		if(RSACipher.hasValidSignature(
				"msg_copy.txt", pubIsm1FromCert, signature))
		{
			System.out.println("File is the original one");
		} else {
			System.out.println("File has been changed");
		}
		
		
		//using elliptic curves EC
		
	}

}
