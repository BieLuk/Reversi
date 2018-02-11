package sample.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.ReversiApp;

import java.util.ArrayList;

import static sample.ReversiApp.board;

public class Tile extends Rectangle {
    public Piece piece;
    private Move move = new Move();
    private static PieceType player = PieceType.WHITE;
    public static ArrayList<Piece> changePieceList = new ArrayList<>();

    public boolean hasPiece() {
        return piece != null;
    }
    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(int x, int y){
        setWidth(ReversiApp.TILE_SIZE);
        setHeight(ReversiApp.TILE_SIZE);

        relocate(x* ReversiApp.TILE_SIZE, y* ReversiApp.TILE_SIZE);
        setStroke(Color.BLACK);
        setFill(Color.GREEN);

        setOnMouseClicked( e -> {
            if(!hasPiece()) {
                player = changePlayer(player);
                piece = new Piece(player, x, y);

                if((move.checkLeftX(piece, x, y) != 0) |
                (move.checkLeftX(piece, x, y) != 0) |
                (move.checkRightX(piece, x, y) != 0) |
                (move.checkUpY(piece, x, y) != 0) |
                (move.checkDownY(piece, x, y) != 0) |
                (move.checkDiagonalRightDown(piece, x, y) != 0) |
                (move.checkDiagonalRightUp(piece, x, y) != 0) |
                (move.checkDiagonalLeftDown(piece, x, y) != 0) |
                (move.checkDiagonalLeftUp(piece, x, y) != 0)){
                    setPiece(piece);
                    ReversiApp.pieceGroup.getChildren().add(piece);
                }
                else {
                    piece = null;
                    player = changePlayer(player);
                }
            }
        });
    }

    private PieceType changePlayer(PieceType player){
        if(player == PieceType.BLACK) {
            player = PieceType.WHITE;
            return player;
        }
        else if(player == PieceType.WHITE) {
            player = PieceType.BLACK;
            return player;
        }
        return null;
    }
}
