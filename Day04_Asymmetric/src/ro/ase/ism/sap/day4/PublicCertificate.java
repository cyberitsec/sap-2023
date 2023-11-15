package ro.ase.ism.sap.day4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class PublicCertificate {
	public static PublicKey getCertificateKey(String certificateFile) throws CertificateException, IOException {
		File file = new File(certificateFile);
		if(!file.exists()) {
			throw new UnsupportedOperationException("****Missing file****");
		}
		FileInputStream fis = new FileInputStream(file);
		
		CertificateFactory certFactory = 
				CertificateFactory.getInstance("X.509");
		X509Certificate certificate = 
				(X509Certificate) certFactory.generateCertificate(fis);
		fis.close();
		return certificate.getPublicKey();	
	}
}
