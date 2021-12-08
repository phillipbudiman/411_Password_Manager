import sample.JSON_Reader;
import sample.User;

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

		//testing new implementation of backend--ignore this section below
		/*current backend problem, encryption, decryption doesn't work when user log out and turn off application
		because a new crytographickey is derive from the same master password everytime AES_file_encryption class
		is initialise */


		User user = new User("user", "pass");
		JSON_Reader jr = new JSON_Reader();

		//for (int i =0;i <=3;i++) {
		//	user.add_vault("google.com", "username1", "password1");
		//}

		//user.encrypt_vault();

//		user.decrypt_vault();

		user.decrypt_vault();

		System.out.println(user.displayUserVault("creds.json"));
		System.out.println(jr.read("creds.json"));

	}

}