import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label passwordDisplay;

    @FXML
    Button passwordButton;

    @FXML
    CheckBox Numbers;

    @FXML
    CheckBox UppercaseLetters;

    @FXML
    CheckBox SpecialCharacters;

    @FXML
    TextField PasswordLength;

    public String passwordGenerator(int len, boolean number, boolean upper, boolean special){
        Random rand = new Random();

        int passwordLengthInt = len;
        String randPassword = "";

        for (int i=0; i < passwordLengthInt; i++){
            if (number==true && upper==false && special==false) {
                if (rand.nextInt(2) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(10) + 48);
                }
            }
            else if (number==false && upper==true && special==false) {
                if (rand.nextInt(2) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(26) + 65);
                }
            }
            else if (number==false && upper==false && special==true) {
                if (rand.nextInt(2) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(4) + 35);
                }
            }
            else if (number==true && upper==true && special==false) {
                if (rand.nextInt(3) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else if (rand.nextInt(3) == 1) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 65);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(10) + 48);
                }
            }
            else if (number==true && upper==true && special==true) {
                if (rand.nextInt(4) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else if (rand.nextInt(4) == 1) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 65);
                }else if (rand.nextInt(4) == 3) {
                    randPassword = randPassword + (char) (rand.nextInt(4 ) + 35);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(10) + 48);
                }
            }
            else if (number==true && upper==false && special==true) {
                if (rand.nextInt(3) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else if (rand.nextInt(3) == 1) {
                    randPassword = randPassword + (char) (rand.nextInt(4 ) + 35);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(10) + 48);
                }
            }
            else if (number==false && upper==true && special==true) {
                if (rand.nextInt(3) == 0) {
                    randPassword = randPassword + (char) (rand.nextInt(26) + 97);
                }else if (rand.nextInt(3) == 1) {
                    randPassword = randPassword + (char) (rand.nextInt(4 ) + 35);
                }else{
                    randPassword = randPassword + (char) (rand.nextInt(26) + 65);
                }
            }
            else{
                randPassword = randPassword + (char) (rand.nextInt(26) + 97);
            }
        }
        return randPassword;
    }

    public void displayPassword(){

        String passwordLength = PasswordLength.getText();
        int passwordLengthInt = Integer.parseInt(passwordLength);

        boolean numbersBool = Numbers.isSelected();
        boolean uppercaseLettersBool = UppercaseLetters.isSelected();
        boolean specialCharactersBool = SpecialCharacters.isSelected();

        String randPassword = passwordGenerator(passwordLengthInt,numbersBool,uppercaseLettersBool,specialCharactersBool);
        passwordDisplay.setText(randPassword);
    }

    @FXML
    TextField UsernameLogin;
    String username;

    @FXML
    TextField PasswordLogin;
    String masterPassword;

    @FXML
    Label PasswordError;

    @FXML
    Button LoginButton;

    @FXML
    TextArea VaultText;
    String vaultString;
    String vaultCommand;


    //Create user login-database
    User user_ka = new User("ka411", "kaPW");
    User user_phil = new User("phil411", "philPW");
    User user_nate = new User("nate411", "natePW");


    public void login(ActionEvent event) throws IOException{

        
        User user_ka = new User("ka411", "kaPW");
        user_ka.storeUser();
        user_ka.add_vault("google.com", "ka@gmail.com", "password123");
        user_ka.add_vault("yahoo.com", "ka@yahoo.com", "new_password");

        User user_phil = new User("phil411", "philPW");
        user_phil.storeUser();
        user_phil.add_vault("github.com", "newuser42", "fj8Hs0iTs");
        user_phil.add_vault("facebook.com", "zuckfan", "bad_password");

        User user_nate = new User("nate411", "natePW");
        user_nate.storeUser();
        user_nate.add_vault("youtube.com", "channel88", "channel89");
        user_nate.add_vault("duckduckgo.com", "userNate", "old_password");
         

        username = UsernameLogin.getText();
        masterPassword = PasswordLogin.getText();
        vaultCommand = VaultText.getText();
        String[] vaultCommandArr = vaultCommand.split(" ");

        if (username.equals(user_ka.getLogin()) && masterPassword.equals(user_ka.getPassword())){

            user_ka.encrypt_vault();
            user_ka.decrypt_vault();

            if(vaultCommand.contains("/add")){
                user_ka.add_vault(vaultCommandArr[1], vaultCommandArr[2], vaultCommandArr[3]);
            }
            if(vaultCommand.contains("/remove")){
                user_ka.remove_vault(vaultCommandArr[1]);
            }

            vaultString = user_ka.displayUserVault("creds.json");
            VaultText.setText(vaultString);

        }
        else if (username.equals(user_phil.getLogin()) && masterPassword.equals(user_phil.getPassword())){
            user_phil.encrypt_vault();
            user_phil.decrypt_vault();

            if(vaultCommand.contains("/add")){
                user_phil.add_vault(vaultCommandArr[1], vaultCommandArr[2], vaultCommandArr[3]);
            }
            if(vaultCommand.contains("/remove")){
                user_phil.remove_vault(vaultCommandArr[1]);
            }
            
            vaultString = user_phil.displayUserVault("creds2.json");
            VaultText.setText(vaultString);

        }
        else if (username.equals(user_nate.getLogin()) && masterPassword.equals(user_nate.getPassword())){
            user_nate.encrypt_vault();
            user_nate.decrypt_vault();

            if(vaultCommand.contains("/add")){
                user_nate.add_vault(vaultCommandArr[1], vaultCommandArr[2], vaultCommandArr[3]);
            }
            if(vaultCommand.contains("/remove")){
                user_nate.remove_vault(vaultCommandArr[1]);
            }
            
            vaultString = user_nate.displayUserVault("creds3.json");
            VaultText.setText(vaultString);

        }
        else{
            if (username.equals(user_ka.getLogin()) || username.equals(user_nate.getLogin()) || username.equals(user_phil.getLogin())) {
                VaultText.setText("Incorrect login for " + username + ".");
            }
            else{
                VaultText.setText("User does not exist.");
            }
        }
    }


    public void switchToHelpscreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Help Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTitlescreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Title Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLoginScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGeneratePW(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Generate PW.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
