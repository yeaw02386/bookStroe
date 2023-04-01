import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import LibPack.IOLib;
import dataPack.Holder;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("resources/login.fxml"));
        primaryStage.setTitle("Book Store");
        primaryStage.setScene(new Scene(root, 1280, 720));
        Holder.getInstance().setGstage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        IOLib.firstLoad();
        launch(args);
    }
}
