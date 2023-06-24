package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	Image iconImage = new Image(getClass().getResourceAsStream("/icons/study.png"));

            StackPane iconPane = new StackPane();
            primaryStage.getIcons().add(iconImage);
            
            primaryStage.setTitle("Giriş Yap");

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
            root.getChildren().add(iconPane);
            AnchorPane.setTopAnchor(iconPane, 10.0);
            AnchorPane.setLeftAnchor(iconPane, 10.0);

            Scene scene = new Scene(root, 323, 366);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
