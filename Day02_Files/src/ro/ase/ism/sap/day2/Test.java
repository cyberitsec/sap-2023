package ro.ase.ism.sap.day2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Test {

	public static void main(String[] args) throws IOException {
		
		//managing the file system
		File location = new File("D:\\2023-2024\\sap-2023");
		if(!location.exists()) {
			throw new UnsupportedOperationException("FOLDER is not there");
		}
		
		File tempFolder = 
				new File(location.getAbsolutePath() + 
						File.separator + 
						"temp");
		
		if(!tempFolder.exists()) {
			tempFolder.mkdir();
		}
			
		File[] files =  location.listFiles();
		for(File file : files) {
			System.out.println(file.getName());
			if(file.isDirectory()) {
				System.out.println(" --- is a folder");
			} else {
				System.out.println(" --- is a file");
			}
		}
		
		//text files
		File messageTextFile = new File("message.txt");
		if(!messageTextFile.exists()) {
			messageTextFile.createNewFile();
		}
		
		
		//writing into text files
		FileWriter fileWriter = new FileWriter(messageTextFile, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("Hello !");
		printWriter.println("This is a secret message.");
		
		printWriter.close();
		
		//reading from text files
		FileReader fileReader = new FileReader(messageTextFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line;
		while((line = bufferedReader.readLine()) != null) {
			System.out.println("File line: " + line);
		} 		
		bufferedReader.close();
		
		
		//binary files
		//writing into binary files
		File dataFile = new File("mydata.dat");
		if(!dataFile.exists()) {
			dataFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(dataFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);
		
		dos.writeFloat(23.5f);
		dos.writeInt(23);
		dos.writeBoolean(true);
		dos.writeUTF("Hello");
		byte[] values = {0x0A, 0x0B};
		dos.writeInt(values.length);
		dos.write(values);
		dos.close();
		
		//read from a binary file
		FileInputStream fis = new FileInputStream(dataFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		
		float floatValue = dis.readFloat();
		int value = dis.readInt();
		boolean logicValue = dis.readBoolean();
		String stringValue = dis.readUTF();
		int byteArraySize = dis.readInt();
		byte[] byteValues = new byte[byteArraySize];
		dis.read(byteValues, 0, byteArraySize);
		
		
		System.out.println("Float value is " + floatValue);
		System.out.println("Int value is " + value);
		System.out.println("String value is " + stringValue);
		
		dis.close();
		
		//binary files with the legacy Random Access File class

		RandomAccessFile raf = new RandomAccessFile(dataFile, "rw");
		values = new byte[]{0x0a, 0x0b, 0x0c};
		for(byte v: values) {
			raf.writeByte(v);
		}
		
		//move to the beginning og the file
		raf.seek(0);
		byte byteValue = raf.readByte();
		
		System.out.println("First byte " + byteValue);
		
		raf.seek(2);
		byteValue = raf.readByte();
		
		System.out.println("Last byte " + byteValue);
		
		raf.close();
	}

}
