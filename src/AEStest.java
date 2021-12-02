import javax.crypto.spec.IvParameterSpec;
import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
public class AEStest {
	
	
	
	public static void main(String[] args) throws Exception {

		File myFile = new File("creds.json");


		//instantiate JSON classes
		//JSON_Reader J_read = new JSON_Reader();
		//JSON_Writer J_write = new JSON_Writer();

		//writing into "creds.json" file  - return void
		//J_write.addEntry("www.github.com","username","password123");
		//J_write.export("creds");

		//reading "creds.json" file
		//J_read.read("creds.json");   // - return ArrayList



		//testing new implementation of backend--ignore this section below
		/*current backend problem, encryption, decryption doesn't work when user log out and turn off application
		because a new crytographickey is derive from the same master password everytime AES_file_encryption class
		is initialise
		*/

		//J_write.initANDstoreUser("ka","password123");
		//ArrayList ar = J_read.read("og.json");

		//my_encrypt.encrypt(myFile);

		//byte[] iv = my_encrypt.getByteIv();
		//File ivFile = new File("testfile.txt");

		//FileOutputStream dest = new FileOutputStream(ivFile); //write iv to file
		//dest.write(iv);

		/*
		DataInputStream ds = null;
		byte[] dataread= new byte[16];
		FileInputStream src = new FileInputStream(ivFile); //read iv from file
		ds = new DataInputStream(src);
		ds.readFully(dataread);


		my_encrypt.setIV(dataread);
		my_encrypt.decrypt(myFile);

		 */

		USER new_user = new USER("ka", "password123");
		new_user.storeUser();    //store login credential into login database
		//new_user.lookupUser();   not working right now
		new_user.add_vault("google.com","kayquoc","password");

		//new_user.encrypt_vault();
		//new_user.decrypt_vault();



	}
}