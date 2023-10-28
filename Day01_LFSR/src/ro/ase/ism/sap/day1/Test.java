package ro.ase.ism.sap.day1;

public class Test {

	public static void main(String[] args) {
		
		byte value = 0b0001_0111;
		
		System.out.println("The value is "  + value);
		
		//shift to left
		value = (byte) (value << 2);
		System.out.println("The value is "  + value);
		
		value = (byte) (value << 1);
		System.out.println("The value is "  + value);
		
		//value is now 1011_1000
		
		//we get 10000000_00000000_..._00111000
		int intValue = (int) value;
		
		//unsigned shift to right
		//temporary we get an int with  00000000_00000000_..._10111000
		value = (byte) ((value & 0xFF) >> 1);
		System.out.println("The value is "  + value);
		
		System.out.println("Demo");
		for(int i = 0; i < 36; i++) {
			System.out.println("The value is "  + (value >> i));	
		}
		
		//how to check and get bit values
		byte keyByte = (byte) 0b1010_0010;
		
		//check if the 4th bit from left to right is 1
		byte _4thBitMask = 0b0001_0000; //1 << 4;
		
		if((keyByte & _4thBitMask) == 0) {
			System.out.println("The 4th bit is zero");
		} else {
			System.out.println("The 4th bit is one");
		}
		
		byte _4thBitValue = (byte) (keyByte & _4thBitMask);
		
		
		//implement a LFSR based on the x^32 + x^7 + x^5 + x^3 + x^2 + x + 1
		LFSR lfsr = new LFSR();
		byte[] seed = {10,20,30,40};
		lfsr.init(seed);
		
		for(int i = 0 ; i < 10; i++) {
			byte randomByte = lfsr.getRandomByte();
			System.out.println("random byte is " + randomByte);
		}
		
	}

}
