import org.json.simple.JSONObject;

public class USER {
    String master_password;
    String login;
    AES_file_encryption encrypt;
    JSON_Writer j_write;
    JSON_Reader j_read;


    public USER(String login, String password){
        this.login = login;
        this.master_password = password;
        this.encrypt = new AES_file_encryption(login,password);
        this.j_write = new JSON_Writer();
        this.j_read = new JSON_Reader();
    }

    public USER(){
        this.j_write = new JSON_Writer();
        this.j_read = new JSON_Reader();
    }
    //store login and master password into a file
    public boolean storeUser(){

    }


}
