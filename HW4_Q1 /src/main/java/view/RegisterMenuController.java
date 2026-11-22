package view;

import controller.ApplicationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterMenuController {

    public Label welcomeText;

    public TextField username;



    @FXML
    public void initialize() {
        changeLabel();
    }
    public void changeLabel() {
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            welcomeText.setText("Welcome " + username.getText());
        });
    }

    public void register (MouseEvent mouseEvent) {
        try {
            new GameLauncher(username.getText()).start(ApplicationController.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO
    }


    public void register2(MouseEvent mouseEvent) {
        try {
            new GameLauncher2(username.getText()).start(ApplicationController.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
