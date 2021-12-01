import java.io.File;

public class AEStest {
	
	
	
	public static void main(String[] args) {

		File myFile = new File("creds.json");
		//instantiate encryption class
		AES_file_encryption my_encrypt = new AES_file_encryption("password123");

		//instantiate JSON classes
		JSON_Reader J_read = new JSON_Reader();
		JSON_Writer J_write = new JSON_Writer();

		J_write.addEntry("www.github.com","username","password123","123");



		if (my_encrypt.encrypt(myFile)) {
			System.out.println("encryption success");
		}else {
			System.out.println("encryption fail");
		}

		if (my_encrypt.decrypt(myFile)) {
			System.out.println("decryption success");
		}else {
			System.out.println("decryption fail");
		}

		
		
	}
}