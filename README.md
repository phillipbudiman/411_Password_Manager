# 411_Password_Manager

**CLASS AES_file_encryption**

Instantiate AES_file_encryption object:
   AES_file_encryption.MY_ENCRYPTION = new AES_file_encryption();

you now have object MY_ENCRYPTION

public METHOD Summary
1. **hashKey(String user_pass)**
  - return type: SecretKey
  - description: a public method that turns a given master password (in String) into a symmetric encryption/decryption key.
  - example of use: MY_ENCRYPTION.hashKey("password123") return @fffe848a

2.**getIV()**
  - return type: IvParameterSpec
  - description: a public method that generatates an initialization vector for AES CBC cipher mode. Does not need to be keep secret. 
  - example of use: IvParameterSpec IV = MY_ENCRYPTION.getIV()

3. **encrypt_file(File input_file, SecretKey key, IvParameterSpec iv)**
  - return type: void - but given file will be encrypted
  - description: encrypt a given file using a key (generated from master password). 
  - exampe of use: MY_ECRYPTION.encrypt(my_json_file, key, iv)
 
 4. **decrypt_file(File input_file, SecretKey key, IvParameterSpec iv)**
  - return type :void - but given file will be decrypted back to original form
  - description: if the given key is the same key use for encryption then file is decryption, else a message is print "bad key use".
 
 
 **Example of how to use AES_file_encryption class to encrypt a file**
AES_file_encryption myobj = new AES_file_encryption();          
//create AES_file_encryption object

File mainFile = new File("og.json");                            
//get database file
		
String master_pass = "password123";                            
//example of a master password
		
SecretKey key = myobj.getKey(master_pass);                     
//turn master password into symmetric key

IvParameterSpec iv = myobj.getIV();                            
//get an IV for encryption and decryption

myobj.encrypt_file(mainFile, key,iv);                         
//call encryption method

myobj.decrypt_file(mainFile, key,iv);                         
//call decryption method
		
 
