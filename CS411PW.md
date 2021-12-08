CLASS AES_file_encryption Attributes: String password; SecretKey key; IvParameterSpec iv;

Constructor AES_file_encryption(String user_password) //this will create an encryption object that can be use to encrypt/decrypt file. Cryptographic key is created automatically within the constructor definition

Instantiate AES_file_encryption object: AES_file_encryption.MY_ENCRYPTION = new AES_file_encryption("password");

you now have object MY_ENCRYPTION

public METHOD Summary

These are the two main method use for encrypting the file and decrypting the file.

encrypt(File input_file)
return type: boolean
description: call encrypt_file, passing it the key and IV and catch all exception return by method encrypt_file. Return true if encryption success, else false
decrypt(File input_file)
return type: boolean
description: call decrypt_file, passing it the key and IV and catch all exception return by method decrypt_file. Return true if decryption success, else false
Here are some other method that you may wanna use. 3. getPassword()

return type: String
description: return the object current master password.
updatePassword(String new_password)
return type: boolean
description: allows the user to update the master password for the vault, this will generate a new key that can then be use.
These two method below are for debugging purposes, you probably don't need to use them. 5. getKey()

return type: SecretKey
description: return the cryptographic key derive from the user password.
getIV()
return type: IvParameterSpec
description: return the initialization vector for AES-CBC cipher mode
Example of how to use AES_file_encryption class with JSON_Reader & JSON_Writer is in main/src/AES_test

CLASS SceneController Attributes: private Stage stage; private Scene scene; private Parent root;

The controller class is driven by 3 main actions across 6 methods.

displayPassword(ActionEvent event)
return void
Driven by the helper method, passwordGenerator, this method uses the boolean from the checkboxes number, upper, special, and the string from PasswordLength. When this method is called, by the pressing the passwordButton, it will generate a password following the given parameters.
login(ActionEvent event)
returns void
The login method uses the two textfields, PasswordLogin and UsernameLogin, to create a user. The user is then compared to the master-cred.json data to determine if there is already a vault in place for this user or a new one needs to be made.
There are 4 methods that focus on switching scenes. The methods are triggered by pressing the return button avalible on each scene.

switchToHelpscreen(ActionEvent event)
returns void
Switches the scene to the Helpscreen.
switchToTitlescreen(ActionEvent event)
returns void
Switches the scene to the TitleScreen.
switchToLoginScreen(ActionEvent event)
returns void
Switches the scene to the LoginScreen.
switchToGeneratePW(ActionEvent event)
returns void
Switches the scene to the Generate Password Screen.
