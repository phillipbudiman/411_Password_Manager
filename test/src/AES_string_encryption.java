import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;



// for AES 128 bits, we need an IV of 16 bytes.


public class AES_string_encryption {

	public static void main(String[] args) 
			throws InvalidKeyException, NoSuchPaddingException, 
			InvalidAlgorithmParameterException, 
			NoSuchAlgorithmException, 
			UnsupportedEncodingException, 
			IllegalBlockSizeException, BadPaddingException  {
		// TODO Auto-generated method stub
		
		
		//Generate an Initialization Vector IV
		byte[] iv = new byte[16];
		SecureRandom srandom = new SecureRandom();
		srandom.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		
		

		
		// for now, lets just create a simple, random secret key
		// in the future, we can also load a key and save a key
		KeyGenerator KeyGen= KeyGenerator.getInstance("AES");
		SecretKey skey = KeyGen.generateKey();
		
		String encrypt = "CS411";
		System.out.println("the input string is: " + encrypt);

		byte[] E_string = encrypt_string(encrypt, ivspec,skey);
		
		System.out.println("encrypted version: " + E_string);
		
		
		String D_string = decrypt_string(E_string,skey,ivspec);
		System.out.println("decrypted version: " + D_string);
		
	}//main
	
	//a method for encrypting a string
	public static byte[] encrypt_string(String text, IvParameterSpec ivspec, SecretKey skey)
	throws NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
	NoSuchAlgorithmException, UnsupportedEncodingException, IllegalBlockSizeException,
	BadPaddingException{

			
		// creating the cipher object, the main object that handles
		// the actual encryption and decryption
		// parameters: secret key, IV

		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.ENCRYPT_MODE,skey, ivspec);
		
		// using the Cipher object, encrypt a simple text string
		byte[] input_text = text.getBytes("UTF-8");
		byte[] encoded = ci.doFinal(input_text);
		return encoded;
	}
	
	//decrypting a string, need a secret key and an IV
	public static String decrypt_string(byte[] encoded,SecretKey skey, IvParameterSpec iv) throws 
	UnsupportedEncodingException, 	IllegalBlockSizeException, BadPaddingException, 
	InvalidKeyException, InvalidAlgorithmParameterException,
	NoSuchAlgorithmException, NoSuchPaddingException {
	
		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.DECRYPT_MODE,skey,iv);
	
		String decrypt = new String(ci.doFinal(encoded), "UTF-8");
		return decrypt;
		
	}

}
