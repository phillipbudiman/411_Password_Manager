# 411_Password_Manager

**CLASS AES_file_encryption**
Attributes:
String password;
SecretKey key;
IvParameterSpec iv; 

Constructor
AES_file_encryption(String user_password)
//this will create an encryption object that can be use to encrypt/decrypt file. Cryptographic key is created automatically within the constructor definition

Instantiate AES_file_encryption object:
   AES_file_encryption.MY_ENCRYPTION = new AES_file_encryption("password");

you now have object MY_ENCRYPTION

public METHOD Summary

These are the two main method use for encrypting the file and decrypting the file.
1. **encrypt(File input_file)**
  - return type: boolean
  - description: call encrypt_file, passing it the key and IV and catch all exception return by method encrypt_file. Return true if encryption success, else false
 
2. **decrypt(File input_file)**
  - return type: boolean
  - description: call decrypt_file, passing it the key and IV and catch all exception return by method decrypt_file. Return true if decryption success, else false

Here are some other method that you may wanna use.
3. **getPassword()**
  - return type: String
  - description: return the object current master password.
4. **updatePassword(String new_password)**
  - return type: boolean
  - description: allows the user to update the master password for the vault, this will generate a new key that can then be use.

These two method below are for debugging purposes, you probably don't need to use them.
5. **getKey()**
  - return type: SecretKey
  - description: return the cryptographic key derive from the user password. 
6. **getIV()**
  - return type: IvParameterSpec
  - description: return the initialization vector for AES-CBC cipher mode
 
 
 **Example of how to use AES_file_encryption class with JSON_Reader & JSON_Writer is in main/src/AES_test**

 
