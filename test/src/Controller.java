package sample;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;

public class Controller {

    @FXML
    Label passwordDisplay;

    @FXML
    Button passwordButton;

    public void displayPassword(){
        passwordDisplay.setText("PASSED");
    }
}
