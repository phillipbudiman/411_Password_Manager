import java.io.File;
import java.util.Scanner;
public class AEStest {
	
	
	
	public static void main(String[] args) throws Exception {

		File myFile = new File("creds.json");
		//instantiate encryption class



		//instantiate JSON classes
		JSON_Reader J_read = new JSON_Reader();
		JSON_Writer J_write = new JSON_Writer();


		//writing into "creds.json" file  - return void
		J_write.addEntry("www.github.com","username","password123");
		J_write.export("creds");

		J_write.initANDstoreUser("ka","password123");



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



	}
}