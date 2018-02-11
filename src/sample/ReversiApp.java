package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Board.*;

public class ReversiApp extends Application {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public static Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    public static Group pieceGroup = new Group();

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                Tile tile = new Tile(x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                Piece piece = null;
                if((x == 3 && y == 3)  || (x == 4 && y == 4)){
                    piece = new Piece(PieceType.WHITE, x, y);
                }
                if((x == 4 && y == 3)  || (x == 3 && y == 4)){
                    piece = new Piece(PieceType.BLACK, x, y);
                }
                if(piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Reversi");
        primaryStage.getIcons().add(new Image("file:data/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
