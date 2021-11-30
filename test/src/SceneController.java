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

    public void displayPassword(){

        passwordDisplay.setText("nsnvohjnt");

        Random rand = new Random();

        boolean numbersBool= Numbers.isSelected();
        boolean uppercaseLettersBool = UppercaseLetters.isSelected();
        boolean specialCharactersBool = SpecialCharacters.isSelected();
        int passwordLengthInt = Integer.parseInt(PasswordLength.getText());
        /**
        if (numbersBool == false && uppercaseLettersBool == false && specialCharactersBool == false){
            for (int i=0; i <passwordLengthInt; i++){
            }
            passwordDisplay.setText("nsnvohjn");
        }**/
        //Random rand = new Random();

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

    public void login(ActionEvent event) throws IOException{

        username = UsernameLogin.getText();
        masterPassword = PasswordLogin.getText();

        if (username.equals("User42") && masterPassword.equals("ILoveCS")){
            Parent root = FXMLLoader.load(getClass().getResource("Vault.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            PasswordError.setText("Incorrect password.");
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