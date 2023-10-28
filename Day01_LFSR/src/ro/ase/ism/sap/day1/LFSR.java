package ro.ase.ism.sap.day1;

//implement a LFSR based on the x^31 + x^7 + x^5 + x^3 + x^2 + x + 1
public class LFSR {
	byte[] register = new byte[4];
	
	public void init(byte[] seed) {
		if(seed.length != 4) {
			throw new UnsupportedOperationException("Seed size is wrong");
		}
		else {
			for(int i = 0; i < 4; i++) {
				this.register[i] = seed[i];
			}
		}
	}
	
	//index from 0 to 31
	private byte getBitAtIndex(int index) {
		if(index < 0 || index  > 31) {
			throw new UnsupportedOperationException("Wrong index");
		}
		
		int byteIndex = 3 - (index / 8);
		int bitIndex = index % 8;
		
		byte bitMask = (byte) (1 << bitIndex);
		
		return (byte) ((this.register[byteIndex] & bitMask) >> bitIndex);
	}
	
	//input is 0 or 1
	//index is 0 -> 3
	private byte shiftWithInsertRegisterByte(byte input, int index) {
		byte registerByte = this.register[index];
		
		byte outBit = (byte) (registerByte & 1);
		registerByte = (byte) ((registerByte & 0xFF) >> 1);
		registerByte = (byte) (registerByte | (input << 7));
		
		this.register[index] = registerByte;
		
		return outBit;
	}
	
	private byte doStep() {
		byte xorResult = (byte) (getBitAtIndex(31) ^ getBitAtIndex(7) ^ 
				getBitAtIndex(5) ^ getBitAtIndex(3) ^ 
				getBitAtIndex(2) ^ getBitAtIndex(1) ^
				getBitAtIndex(0));
		
		byte tempBit = shiftWithInsertRegisterByte(xorResult, 0);
		tempBit = shiftWithInsertRegisterByte(tempBit, 1);
		tempBit = shiftWithInsertRegisterByte(tempBit, 2);
		byte resultBit = shiftWithInsertRegisterByte(tempBit, 3);
		
		return resultBit;
	}
	
	public byte getRandomByte() {
		byte result = 0;
		for(int i = 0; i < 8; i++) {
			result = (byte) (result << 1);
			byte randomBit = this.doStep();	//1 or 0
			result = (byte) (result | randomBit);
		}
		return result;
	}
}
