package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import sample.App.GUI;
import sample.Board.Tile;
import sample.Exception.LackOfStylesheetFileException;
import sample.Exception.NoConnectionException;
import sample.Exception.WrongArgumentException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class ReversiApp extends Application {

    public GUI gui = new GUI();

    @Override
    public void start(Stage primaryStage) {
        gui.gameScene = new Scene(gui.gameContent());

        try {
            GUI.menuScene = new Scene(gui.menuContent());
        } catch (IOException | SAXException | LackOfStylesheetFileException | ParserConfigurationException | WrongArgumentException | NoConnectionException e) {
            GUI.logger.error("Exception in start method.", e);
        }
        try {
            gui.scoreScene = new Scene(gui.scoreContent());
        } catch (IOException | SAXException | ParserConfigurationException | WrongArgumentException e) {
            GUI.logger.error("Exception in start method.", e);
        }
        GUI.window = primaryStage;
        primaryStage.setTitle("Reversi");
        primaryStage.getIcons().add(new Image("file:data/icon.png"));
        primaryStage.setScene(GUI.menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
