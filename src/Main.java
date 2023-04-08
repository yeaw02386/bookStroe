import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import LibPack.CVLib;
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

        primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we){
                Holder hold = Holder.getInstance();
                try {
                    IOLib.writerAllFile(CVLib.user2Data(hold.getUsers()), Holder.getUserPath(), false);
                    IOLib.writerAllFile(CVLib.book2Data(hold.getShelf().getBooks()), Holder.getBookPath(), false);
                    IOLib.writerAllFile(CVLib.tag2Data(hold.getShelf().getTags()), Holder.getTagPath(), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); 
    }

    public static void main(String[] args) throws IOException {
        IOLib.firstLoad();
        launch(args);
    }
}
