import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class AEStest {
	
	
	
	public static void main(String[] args) throws Exception {

		File myFile = new File("creds.json");
		//instantiate encryption class



		//instantiate JSON classes
		JSON_Reader J_read = new JSON_Reader();
		JSON_Writer J_write = new JSON_Writer();


		//writing into "creds.json" file  - return void
		//J_write.addEntry("www.github.com","username","password123");
		//J_write.export("creds");



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

		//testing new implementation of backend--ignore this section below
		/*current backend problem, encryption, decryption doesn't work when user log out and turn off application
		because a new crytographickey is derive from the same master password everytime AES_file_encryption class
		is initialise
		*/

		//J_write.initANDstoreUser("ka","password123");
		ArrayList ar = J_read.read("src/master_creds.json");
		System.out.println(ar.get(0));

	}
}