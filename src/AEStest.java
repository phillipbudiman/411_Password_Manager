import java.io.File;

public class AEStest {
	
	
	
	public static void main(String[] args) {
		AES_file_encryption myobj = new AES_file_encryption("password123");
		File myFile = new File("decryptfile.txt");
		if (myobj.encrypt(myFile)) {
			System.out.println("encryption success");
		}else{
			System.out.println("encryption fail");
		}
		/*
		if (myobj.decrypt(myFile)) {
			System.out.println("decryption success");
		}else{
			System.out.println("decryption fail");
		}
		*/
		
		
		
	}
}