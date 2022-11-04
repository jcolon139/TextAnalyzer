package org.openjfx.wordoccurences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Word Occurrences");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        File file = new File("\\C:\\Users\\jcol_\\Documents");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}