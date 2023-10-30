package ro.ase.ism.sap.day2;

import java.util.ArrayList;
import java.util.List;

public class Certificate {
	String name;
	String organization;
	String country;
	String signature;
	
	ArrayList<Byte> publicKey = new ArrayList<>(128);
	
	public Certificate(String name, String organization, String country, String signature) {
		super();
		this.name = name;
		this.organization = organization;
		this.country = country;
		this.signature = signature;
	}
	
	@Override
	public String toString() {
		return this.name + " with signature " + this.signature;
	}

	@Override
	public int hashCode() {
		return this.signature.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Certificate)) {
			return false;
		}
		
		Certificate other = (Certificate) obj;
		
		return this.name.equals(other.name) && 
				this.signature.equals(other.signature);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Certificate copy = 
				new Certificate(name, organization, country, signature);
		
		//don't do the shallow copy
		//copy.publicKey = this.publicKey;
		
		//do deep-copy
		copy.publicKey = 
				(ArrayList<Byte>) this.publicKey.clone();
		//alternative
		//copy.publicKey = new ArrayList<>(this.publicKey);
		
		return copy;
	}
	
	
	
	
}
