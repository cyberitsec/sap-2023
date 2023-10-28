package ro.ase.ism.sap.day1;

import java.util.Base64;

public class Test {
	
	public static String getHexString(byte[] value) {
		StringBuilder result = new StringBuilder();
		result.append("0x");
		for(byte b : value) {
			result.append(String.format("%02X", b));
		}
		return result.toString();
	}

	public static void main(String[] args) {

		String filename = "Message.txt";
		String anotherFile = "Message.txt";
		
		//wrong way
		if(filename == anotherFile) {
			System.out.println("They are equal");
		} else {
			System.out.println("They are different");
		}
		
		
		anotherFile = new String("Message.txt");
		if(filename == anotherFile) {
			System.out.println("They are equal");
		} else {
			System.out.println("They are different");
		}
		
		//correct way with equals
		anotherFile = new String("Message.txt");
		if(filename.equals(anotherFile)) {
			System.out.println("They are equal");
		} else {
			System.out.println("They are different");
		}
		
		filename = "Message.enc";
		System.out.println("Filename  = " + filename);
		System.out.println("Other Filename  = " + anotherFile);
		
		int value1 = 23;
		Integer iObject1 = 23; //managed by a Constant Pool of numbers up to 127
		Integer iObject2 = 23;
		
		if(iObject1 == iObject2) {
			System.out.println("The numbers are equal");
		} else {
			System.out.println("The numbers are different");
		}
		
		iObject1 = 230;
		iObject2 = 230;
		
		if(iObject1.equals(iObject2)) {
			System.out.println("The numbers are equal");
		} else {
			System.out.println("The numbers are different");
		}
		
		//converting strings to byte arrays and reverse
		String password = "12345678";
		char c = 'a';		//2 bytes
		byte b = 1;			//1 byte value
		
		byte[] passwordAsByteArray = password.getBytes();
		System.out.println("Password size is " + passwordAsByteArray.length);
		
		
		//only if we know for sure that the values was obtained from a String
		String oldPassword = new String(passwordAsByteArray);
		System.out.println("The password is " + oldPassword);
		
		//printing byte arrays with different encodings
		//hex
		byte[] randomKey = {10,0,1,23,100,120,0,2};
		
		//let's break the value converting it to string
		//don't convert it to string
		password = new String(randomKey);
		
		System.out.println("The pass is " + password);
		byte[] initialKey = password.getBytes();
		for(byte bvalue : initialKey) {
			System.out.println("The byte is " + bvalue);
		}
		
		byte[] newRandomKey = {10,0,2,23,100,120,0,2};
		String newRandomPassword = new String(newRandomKey);
		
		System.out.println("The new pass is " + newRandomPassword);
		
		if(newRandomPassword.equals(password)) {
			System.out.println("They are equal");
		}
		else {
			System.out.println("They are different");
		}
		
		//hexadecimal representation
		System.out.println(String.format("Hex value of 30 is 0x%02x", 30));
		
		System.out.println("Hex value of binary key is " + 
				getHexString(newRandomKey));
		
		//base64 
		String base64Password = Base64.getEncoder().encodeToString(newRandomKey);
		System.out.println("The binary pass as base64 is " + base64Password);
		
		byte[] oldPasswordValue = Base64.getDecoder().decode(base64Password);
		
		System.out.println("Hex value of binary key is " + 
				getHexString(oldPasswordValue));
		
		//define numbers and bytes
		byte byteValue = 23;
		byteValue = 0b00010111;	//still 23
		byteValue = 0b0001_0111;//still 23
		byteValue = 0x17; //still 23
		byteValue = 1 << 4 | 1 << 2 | 1 << 1 | 1; //still 23
		
		System.out.println("Value is " + byteValue);
		
		//shifting values
		byteValue = (byte) (byteValue << 2);
		System.out.println("Value is " + byteValue);
		
		byteValue = (byte) (byteValue << 1);
		System.out.println("Value is " + byteValue); //sign bit is 1
		
		/*
		 * byteValue = (byte) (byteValue >> 1); System.out.println("Value is " +
		 * byteValue);
		 */
		
		int intValue = (int)byteValue;
		System.out.println("Int Value is " + intValue); 
		
		byteValue = (byte) (byteValue >>> 1);  //does not work on byte
		System.out.println("Value is * " + byteValue);
		
		//check the first/sign bit
		boolean isNegative = ((byteValue & (1 << 7)) == 0 ? false : true);
		
		if(isNegative)
			byteValue -= 0b1000_0000;
		
		System.out.println("Value is " + byteValue);
		
		intValue = intValue >>> 1;	//works on int values - unsigned right shift
		System.out.println("Int Value is " + intValue); 
	}

}

