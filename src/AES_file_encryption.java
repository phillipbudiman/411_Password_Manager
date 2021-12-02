import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileWriter;

//                                    SOURCES AND REFERENCES USE
//            		https://www.novixys.com/blog/java-aes-example/#:~:text=The%20cor
//           		e%20java%20libraries%20provide,just%20the%20core%20java%20API.

//            		https://www.baeldung.com/java-aes-encryption-decryption

//------------------------------------ START OF CODING PROGRAM ----------------------------------------------//

public class AES_file_encryption {
//-------------------Attributes----------------------------//
	String login;
	String password;
	SecretKey key;
	IvParameterSpec iv;

	//constructor for initializing
	public AES_file_encryption(String login,String password)  {
		try {
			this.login = login;
			this.password = password;
			this.key = hashKey(password);
			this.iv = createIV();
		}catch (NoSuchAlgorithmException | InvalidKeySpecException e){
			System.out.println("unable to create object");
		}
	}

	public AES_file_encryption(String login,String password, String iv){
		try {
			this.login  = login;
			this.password = password;
			this.key = hashKey(password);
			IvParameterSpec preset = new IvParameterSpec(iv.getBytes());
			this.iv = preset;
		}
		 catch (NoSuchAlgorithmException | InvalidKeySpecException e){
			System.out.println("object initialize error");
		 }
	}


	//-------------------METHODS-----------------------//
	//some GETTERs methods
	public String getPassword(){
		return this.password;
	}
	public SecretKey getKey(){
		return this.key;
	}
	public IvParameterSpec getIV(){
		return this.iv;
	}
	public String getLogin(){
		return this.login;
	}

	public byte[] getKeyString(){
		return this.key.getEncoded();
	}



	public boolean updateLogin(String new_login){
		this.login = new_login;
		return true;
	}
	//some SETTERs methods
	public boolean updatePassword(String new_password)  {
		this.password = new_password;
		try{
			this.key = hashKey(new_password);
			return true;
		}catch  (NoSuchAlgorithmException | InvalidKeySpecException e ){
			return false;
		}
	}
	public byte[] getByteIv (){
		return this.iv.getIV();
	}

	public String getStringIv(){
		byte[] x = this.getByteIv();
		String str_iv = new String(x);
		return str_iv;
	}

	public void setIV(byte[] x){
		IvParameterSpec new_iv = new IvParameterSpec(x);
		this.iv = new_iv;
	}


	//  main use for internal testing
	public static void main(String[] args) throws NoSuchAlgorithmException,
			InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		System.out.println(hashKey("password123"));

	}



	//method that applies PBKDF2 on a user password, returning a crytographic key
	private static SecretKey hashKey(String user_password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16]; //or 128 bits
		salt = "[B@61f8bee4".getBytes();

		//random.nextBytes(salt);


		char[] char_us_pass = user_password.toCharArray();  //converts user password to char array
		PBEKeySpec generate_pass = new PBEKeySpec(char_us_pass,salt, 1000, 128);
		SecretKeyFactory fac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); //convert key into specific algo key


		byte[] finele = fac.generateSecret(generate_pass).getEncoded();
		SecretKey new_s_key = new SecretKeySpec(finele,"AES");

		return new_s_key;


	}
	//method use to get Initialization vector for AES-CBC cipher mode
	private static IvParameterSpec createIV() {
		//Using AES with mode CBC (Cipher Block Chaining)
		//generating an IV of 16 bytes, for AES 128 bits encryption blocks
		byte[] iv = new byte[16];
		SecureRandom srandom = new SecureRandom();
		srandom.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		return ivspec;
	}

	//helper function, given 2 file, source and destination,
	//copy content of source into destination
	private static void copy_file(File source, File destination) throws IOException {

		FileInputStream src = new FileInputStream(source);
		FileOutputStream dest = new FileOutputStream(destination);


		int bytesRead;
		//read the buffer, if reach -1 then we have reach end of file.
		//reading file in 1024 byte chunks, 1 chunk at a

		while ((bytesRead = src.read()) != -1 ) {
			dest.write(bytesRead);
		}
		src.close();
		dest.close();
	}

	//IMPORTANT method that calls the encryption of the file and catches any exception returns
	public boolean encrypt(File input_file) {
		try{
			encrypt_file(input_file,this.key,this.iv);
			return true;
		} catch (InvalidAlgorithmParameterException|NoSuchPaddingException |IllegalBlockSizeException|
				NoSuchAlgorithmException|IOException|BadPaddingException|InvalidKeyException e) {
			return false;
		}
	}

	//IMPORTANT method that calls the decryption of the file and catches any exception returns
	public boolean decrypt(File input_file){
		try{
			decrypt_file(input_file,this.key,this.iv);
			return true;
		} catch (InvalidAlgorithmParameterException|NoSuchPaddingException |IllegalBlockSizeException|
				NoSuchAlgorithmException|IOException|InvalidKeyException e) {
			return false;
		} catch (BadPaddingException e) {
			System.out.println("bad key use");
			return false;
		}
	}
	//a method for encrypting a file
	private static void encrypt_file(File input_file, SecretKey skey, IvParameterSpec iv)

			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IOException, IllegalBlockSizeException, BadPaddingException
	{

		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.ENCRYPT_MODE,skey,iv);



		//FileInputStream file_stream = new FileInputStream(input_file);


		File tempFile = new File("tempfile.json");


		FileOutputStream out_file = new FileOutputStream(tempFile);

		//adding dummy block to the front of file

		//creating a dummy cipher block
		SecureRandom srandom = new SecureRandom();
		byte[] dummy = new byte[16];
		srandom.nextBytes(dummy);

		//adding dummy block to front of file
		File obs = new File("observe.json");
		FileOutputStream obs2 = new FileOutputStream(obs);

		byte[] entire = new byte[(int) input_file.length()];
		FileInputStream in2 = new FileInputStream(input_file);
		in2.read(entire);

		byte[] total = new byte[entire.length+ dummy.length];
		System.arraycopy(dummy,0,total,0,dummy.length);
		System.arraycopy(entire,0,total,dummy.length,entire.length);
		obs2.write(total);
		in2.close();
		obs2.close();

		FileInputStream file_stream = new FileInputStream(obs);




		//instead of reading entire file into memory, we break file into buffer, then encrypt those buffer.
		//a byte is 8 bits
		//byte[1024] means a byte array of 1024 byte
		byte[] buffer = new byte[1024];
		int bytesRead;
		//read the buffer, if reach -1 then we have reach end of file.
		//reading file in 1024 byte chunks, 1 chunk at a
		while ((bytesRead = file_stream.read(buffer)) != -1 ) {

			//crypto Cipher object uses 2 method to encrypt data
			//to encrypt a single chunk of data, simply call doFinal() with the data to encrypt
			//to encrypt multiple blocks of data, call update() for each block of data,
			//the doFinal() for the final chunk
			//source: http://tutorials.jenkov.com/java-cryptography/cipher.html

			byte[] output = ci.update(buffer,0,bytesRead);
			out_file.write(output);
		}

		byte[] finalBytes = ci.doFinal();
		out_file.write(finalBytes);

		file_stream.close();
		out_file.close();
		obs.delete();
		//process of encryption is now complete


		//to copy encrypted code into the original file

		FileWriter clear = new FileWriter(input_file);
		clear.flush();
		clear.close();

		copy_file(tempFile,input_file);

		tempFile.delete();//delete temporary file




	}

	private void decrypt_file(File decrypt_file, SecretKey skey,IvParameterSpec iv) throws
			IllegalBlockSizeException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			NoSuchAlgorithmException, NoSuchPaddingException, IOException,
			BadPaddingException{



		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.DECRYPT_MODE,skey,iv);



		FileInputStream in_file = new FileInputStream(decrypt_file);

		File tempFile = new File("tempfile.json");

		FileOutputStream out_file = new FileOutputStream(tempFile);




		byte[] buffer = new byte[1024];
		int bytesRead;
		//read the buffer, if reach -1 then we have reach end of file.
		//reading file in 1024 byte chunks, 1 chunk at a time
		while ((bytesRead = in_file.read(buffer)) != -1 ) {
			byte[] output = ci.update(buffer,0,bytesRead);
			out_file.write(output);
		}
		byte[] finalBytes = ci.doFinal();
		out_file.write(finalBytes);



		in_file.close();
		out_file.close();
		//decryption is now complete


		FileWriter clear = new FileWriter(decrypt_file);
		clear.flush();
		clear.close();



		FileOutputStream out2 = new FileOutputStream(decrypt_file);
		FileInputStream in2 = new FileInputStream(tempFile);

		int file_size = (int)tempFile.length();
		int read;
		byte[] buffer2 = new byte[16];
		byte[] junk = new byte[16];
		in2.read(junk);
		while ((read = in2.read(buffer2)) != -1 ){
			out2.write(buffer2);
		}


		out2.close();
		in2.close();



		//copy_file(tempFile,decrypt_file);
		tempFile.delete();


	}

}