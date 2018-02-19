package sample.App;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import sample.Board.Move;
import sample.Board.Piece;
import sample.Board.PieceType;
import sample.Board.Tile;

import javax.xml.crypto.Data;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.Exception.LackOfStylesheetFileException;
import sample.Exception.NoConnectionException;
import sample.Exception.WrongArgumentException;
import sample.Exception.WrongPieceTypeException;
import sample.ReversiApp;

public class GUI {
    public static Stage window;
    public  Scene gameScene, scoreScene;
    public static Scene menuScene;

    public static final int TILE_SIZE = 100;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    public static Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Group tileGroup = new Group();
    public static Group pieceGroup = new Group();
    public static ArrayList<Tile> emptyTileList = new ArrayList<>();

    public static final Logger logger = LogManager.getLogger(GUI.class);
    private XMLReader xmlReader = new XMLReader();
    private static String language = ConfigReader.getDataFromProperty("lang");

    private TextField player1Field;
    private TextField player2Field;
    private static String player1Name;
    private static String player2Name;

    private Database db = new Database();
    public static ObservableList<DataModel> data = FXCollections.observableArrayList();
    private EventHandler<ActionEvent> playButtonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            player1Name = player1Field.getText();
            player2Name = player2Field.getText();
            window.setScene(gameScene);
            logger.info("Changing scene to gameScene. Starting game.");
        }
    };

    public Parent gameContent(){
        Pane root = new Pane();
        root.setPrefSize(WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);


        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                Tile tile = new Tile(x, y);
                emptyTileList.add(tile);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);


                Piece piece = null;
                if((x == 3 && y == 3)  || (x == 4 && y == 4)){
                    try {
                        piece = new Piece(PieceType.WHITE, x, y);
                    } catch (WrongPieceTypeException e) {
                        GUI.logger.error("Exception while creating piece", e);
                    }
                    emptyTileList.remove(tile);
                }
                if((x == 4 && y == 3)  || (x == 3 && y == 4)){
                    try {
                        piece = new Piece(PieceType.BLACK, x, y);
                    } catch (WrongPieceTypeException e) {
                        GUI.logger.error("Exception while creating piece", e);
                    }
                    emptyTileList.remove(tile);
                }
                if(piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    public Parent menuContent() throws IOException, SAXException, ParserConfigurationException, LackOfStylesheetFileException, NoConnectionException, WrongArgumentException {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.setVgap(8);
        grid.setHgap(10);

        Button skin1Button = new Button(xmlReader.getWord(language, "skin1"));
        GridPane.setConstraints(skin1Button, 0, 0);

        Button skin2Button = new Button(xmlReader.getWord(language, "skin2"));
        GridPane.setConstraints(skin2Button, 1, 0);

        Button skin3Button = new Button(xmlReader.getWord(language, "skin3"));
        GridPane.setConstraints(skin3Button, 2, 0);

        player1Field = new TextField();
        GridPane.setConstraints(player1Field, 2, 1);
        player1Field.setPromptText(xmlReader.getWord(language, "pl1"));

        player2Field = new TextField();
        GridPane.setConstraints(player2Field, 2, 2);
        player2Field.setPromptText(xmlReader.getWord(language, "pl2"));

        Button playButton = new Button(xmlReader.getWord(language, "play"));
        GridPane.setConstraints(playButton, 1, 1);

        Button scoreButton = new Button(xmlReader.getWord(language, "score"));
        GridPane.setConstraints(scoreButton, 1, 2);

        CustomButton exitButton = new CustomButton(xmlReader.getWord(language, "exit"));
        GridPane.setConstraints(exitButton, 1, 3);

        Label timeLabel = new Label(WebService.getResponse());
        GridPane.setConstraints(timeLabel, 2,3);
        grid.getChildren().addAll(playButton, skin1Button, skin2Button, skin3Button, player1Field, player2Field, scoreButton, exitButton, timeLabel);

        skin1Button.setOnAction( event -> {
            gameScene.getStylesheets().removeAll("skin2.css", "skin3.css");
            menuScene.getStylesheets().removeAll("skin2.css", "skin3.css");
            scoreScene.getStylesheets().removeAll("skin2.css", "skin3.css");
            menuScene.getStylesheets().add("skin1.css");
            scoreScene.getStylesheets().add("skin1.css");
            gameScene.getStylesheets().add("skin1.css");
            window.sizeToScene();
        });
        skin2Button.setOnAction( event -> {
            gameScene.getStylesheets().removeAll("skin1.css", "skin3.css");
            menuScene.getStylesheets().removeAll("skin1.css", "skin3.css");
            scoreScene.getStylesheets().removeAll("skin1.css", "skin3.css");
            menuScene.getStylesheets().add("skin2.css");
            scoreScene.getStylesheets().add("skin2.css");
            gameScene.getStylesheets().add("skin2.css");
            window.sizeToScene();
        });
        skin3Button.setOnAction( event -> {
            gameScene.getStylesheets().removeAll("skin1.css", "skin2.css");
            menuScene.getStylesheets().removeAll("skin1.css", "skin2.css");
            scoreScene.getStylesheets().removeAll("skin1.css", "skin2.css");
            menuScene.getStylesheets().add("skin3.css");
            scoreScene.getStylesheets().add("skin3.css");
            gameScene.getStylesheets().add("skin3.css");
            window.sizeToScene();
        });

        playButton.setOnAction(playButtonHandler);
        scoreButton.setOnAction(event -> {
            window.setScene(scoreScene);
            logger.info("Changing scene to scoreScene");
        });
        exitButton.setOnAction(event -> {
            window.close();
            logger.info("Exiting game.");
        });

        return grid;
    }

    public Parent scoreContent() throws IOException, SAXException, ParserConfigurationException, WrongArgumentException {
        db.getDataFromDatabase();
        GridPane scorePane = new GridPane();
        Button backButton = new Button(xmlReader.getWord(language, "backToMenu"));
        GridPane.setConstraints(backButton, 0, 2);
        backButton.setOnAction(event -> {
            window.setScene(menuScene);
            logger.info("Changing scene to menuScene");
        });
        Button playButton = new Button(xmlReader.getWord(language, "play"));
        GridPane.setConstraints(playButton, 0, 0);
        playButton.setOnAction(playButtonHandler);


        for (int i = 0; i < DataModel.nameList.size(); i++) {
            data.add(new DataModel(DataModel.nameList.get(i), DataModel.scoreList.get(i)));
        }

        TableView<DataModel> scoreTable = new TableView<>();
        GridPane.setConstraints(scoreTable, 0,1);
        TableColumn name = new TableColumn(xmlReader.getWord(language, "plName"));
        TableColumn score = new TableColumn(xmlReader.getWord(language, "plScore"));

        name.setCellValueFactory(new PropertyValueFactory<DataModel, String>("name"));
        score.setCellValueFactory(new PropertyValueFactory<DataModel, Integer>("score"));

        scoreTable.setItems(data);
        scoreTable.getColumns().addAll(name, score);
        scorePane.getChildren().addAll(scoreTable,backButton, playButton);

        return scorePane;
    }
    public void endGame() throws IOException, SAXException, ParserConfigurationException, WrongArgumentException {

        if(Tile.pieceCounter == 64){
            Move.countPieces();
            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setTitle(xmlReader.getWord(language, "winTitle"));
            winAlert.setHeaderText(xmlReader.getWord(language, "winTitle"));

            if(Tile.countBlack > Tile.countWhite) {
                winAlert.setContentText(xmlReader.getWord(language, "pl") + " " + player1Name + " " + xmlReader.getWord(language, "winText"));
                db.updateDatabase(player1Name, Tile.countBlack, player2Name, Tile.countWhite);
            } else {
                winAlert.setContentText(xmlReader.getWord(language, "pl") + " " + player2Name + " " + xmlReader.getWord(language, "winText"));
                db.updateDatabase(player2Name, Tile.countWhite, player1Name, Tile.countBlack);
            }

            winAlert.showAndWait();
            window.setScene(menuScene);
            Tile.pieceCounter = 0;
        }
    }
}
