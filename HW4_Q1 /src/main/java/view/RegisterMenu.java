package view;


import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterMenu extends Application {
    public RegisterMenuController controller ;
    public static void main(String[] args) {

        launch(args);
    }


    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        ApplicationController.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(RegisterMenu.class.getResource("/FXML/RegisterMenu.fxml"));
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(pane));
        stage.show();


    }

}