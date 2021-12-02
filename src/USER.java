import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class USER {
    String master_password;
    String login;
    AES_file_encryption encrypt;
    JSON_Writer j_write;
    JSON_Reader j_read;
    File vault;



    public USER(String login, String password){
        this.login = login;
        this.master_password = password;
        this.encrypt = new AES_file_encryption(login,password);
        this.j_write = new JSON_Writer();
        this.j_read = new JSON_Reader();
        this.vault = new File("creds.json");

    }

    public USER(){
        this.j_write = new JSON_Writer();
        this.j_read = new JSON_Reader();
    }
    //store login and master password into a file
    public boolean storeUser() {
        byte[] iv = encrypt.getByteIv();
        j_write.initANDstoreUser(this.login,iv);

        //temporary storage into text file
        try {
            FileOutputStream dest = new FileOutputStream("testfile.txt"); //write iv to file
            dest.write(iv);
            dest.close();
        }catch ( IOException e){
            System.out.println("file not found");
        }

        return true;
    }
    private String getFileString(){
        return "creds";
    }
    //read from login database and find any existing user login/matching password
    public String lookupUser() {
        ArrayList ar = null;
        try {
            ar = j_read.read("master_creds.json");
        } catch (Exception e) {
            System.out.println("file not found");
        }

        for (int i = 0; i < ar.size(); i++) {
            // For loop that iterates 3 times--one for each index in the array for each ArrayList index
            for (int j = 0;j < 2;j++){
                //String[] x = ar.get(i);

            }
            // Newline
            System.out.println();
        }

        return "testing phase";
    }

    public void add_vault(String website, String web_login, String web_password){
        j_write.addEntry(website,web_login,web_password);
        try {
            j_write.export(this.getFileString());
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


    }
    public boolean updateMasterPassword(String new_password){
        this.master_password = new_password;
        return encrypt.updatePassword(new_password);
    }

    public void encrypt_vault(){
        if (encrypt.encrypt(this.vault)){
            System.out.println("encryption sucessful");
        }else{
            System.out.println("encryption fail");
        }
    }
    //perform password checking on the frontend side
    public void decrypt_vault(){
        try {
            //j_read.read("master_creds.json");   not working for now
            //retrieve the IV from file

            DataInputStream ds = null;
            byte[] dataread= new byte[16];
            FileInputStream src = new FileInputStream("testfile.txt"); //read iv from file
            ds = new DataInputStream(src);
            ds.readFully(dataread);   //read IV in terms of byte and store it in dataread
            this.encrypt.setIV(dataread);
            ds.close();
            src.close();
        }catch (Exception e) {
            System.out.println("can't retrieve IV");
        }
        if (encrypt.decrypt(this.vault)){
            System.out.println("decryption sucessful");
        }else{
            System.out.println("decryption fail");
        }


    }







}
