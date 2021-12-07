package sample;

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

    // Helper method for the password generate method
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
    //Add done button - encrypt and exit
    public void login(ActionEvent event) throws IOException{

        username = UsernameLogin.getText();
        masterPassword = PasswordLogin.getText();
        vaultCommand = VaultText.getText();

        User user = new User(username, masterPassword);
        user.encrypt_vault();

        if (user.checkUserExists() && (user.checkMasterEmpty() == false)){

            if(user.decrypt_vault().contains("success")) {
                if (vaultCommand.contains("/add")) {
                    String[] vaultCommandSplit_add = vaultCommand.split("/add ");
                    String[] vaultCommandArr_add = vaultCommandSplit_add[1].split(" ");
                    user.add_vault(vaultCommandArr_add[0], vaultCommandArr_add[1], vaultCommandArr_add[2]);
                }
                if (vaultCommand.contains("/remove")) {
                    String[] vaultCommandSplit_remove = vaultCommand.split("/remove ");
                    user.remove_vault(vaultCommandSplit_remove[1]);
                }
                if (vaultCommand.contains("/done")) {
                    user.encrypt_vault();
                    System.exit(0);
                }
            }
            else{
                VaultText.setText("Incorrect password.");
            }
            vaultString = user.displayUserVault("creds.json");
            VaultText.setText(vaultString);
        }
        else if (user.checkUserExists() == false){
            
            user.storeUser();
            if(user.decrypt_vault().contains("success")) {
                if (vaultCommand.contains("/add")) {
                    String[] vaultCommandSplit_add = vaultCommand.split("/add ");
                    String[] vaultCommandArr_add = vaultCommandSplit_add[1].split(" ");
                    user.add_vault(vaultCommandArr_add[0], vaultCommandArr_add[1], vaultCommandArr_add[2]);
                }
                if (vaultCommand.contains("/remove")) {
                    String[] vaultCommandSplit_remove = vaultCommand.split("/remove ");
                    user.remove_vault(vaultCommandSplit_remove[1]);
                }
                if (vaultCommand.contains("/done")) {
                    user.encrypt_vault();
                    System.exit(0);
                }
            }
            else{
                VaultText.setText("Incorrect password.");
            }
            vaultString = user.displayUserVault("creds.json");
            VaultText.setText(vaultString);
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
