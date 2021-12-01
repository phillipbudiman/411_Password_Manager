import java.io.File;
import java.util.Scanner;
public class AEStest {
	
	
	
	public static void main(String[] args) throws Exception {

		File myFile = new File("creds.json");
		//instantiate encryption class
		AES_file_encryption my_encrypt = new AES_file_encryption("ka","password123");

		//instantiate JSON classes
		JSON_Reader J_read = new JSON_Reader();
		JSON_Writer J_write = new JSON_Writer();

		//writing into "creds.json" file  - return void
		J_write.addEntry("www.github.com","username","password123");
		J_write.export("creds");


		//reading "creds.json" file
		//J_read.read("creds.json");   // - return ArrayList

		//call encryption on "creds.json"
		/*
		if (my_encrypt.encrypt(myFile)) {
			System.out.println("encryption succesful");
		}else {
			System.out.println("encryption failed");
		}
		if (my_encrypt.encrypt(myFile)) {
			System.out.println("decryption succesful");
		}else {
			System.out.println("decryption failed");
		}
		*/


		//Some extra testing below - IGNORE this

		System.out.print(my_encrypt.getKey());
		byte[] current = my_encrypt.getByteIv();

		//System.out.println(current);

		String str = "[B@1d16f93d";
		//my_encrypt.setIV(str.getBytes());
		//System.out.println(my_encrypt.getStringIv());

		/*
		if (my_encrypt.encrypt(myFile)) {
			System.out.println("encryption succesful");
		}else {
			System.out.println("encryption failed");
		}

		if (my_encrypt.decrypt(myFile)) {
			System.out.println("decryption succesful");
		}else {
			System.out.println("decryption failed");
		}
		*/
	}
}