package sample.Board;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.xml.sax.SAXException;
import sample.App.GUI;
import sample.Exception.LackOfStylesheetFileException;
import sample.Exception.WrongArgumentException;
import sample.Exception.WrongFieldException;
import sample.Exception.WrongPieceTypeException;
import sample.ReversiApp;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static sample.App.GUI.TILE_SIZE;
import static sample.App.GUI.board;
import static sample.App.GUI.emptyTileList;


public class Tile extends Rectangle {
    private Piece piece;
    private Move move = new Move();
    private static PieceType player = PieceType.BLACK;
    static ArrayList<Piece> changePieceList = new ArrayList<>();
    public static int pieceCounter = 4;

    public boolean hasPiece() {
        return piece != null;
    }
    public Piece getPiece(){
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }


    public static int countBlack = 0;
    public static int countWhite = 0;
    private GUI gui = new GUI();

    public Tile(int x, int y){
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x* TILE_SIZE, y* TILE_SIZE);
        setStroke(Color.BLACK);
        setFill(Color.GREEN);

        setOnMouseClicked( e -> {
            if(!hasPiece()) {
                try {
                    piece = new Piece(player, x, y);
                } catch (WrongPieceTypeException exc) {
                    GUI.logger.error("Exception in Tile constructor", exc);
                }
                try {
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
                        sample.App.GUI.pieceGroup.getChildren().add(piece);
                        GUI.emptyTileList.remove(this);

                        player = changePlayer(player);
                        pieceCounter++;
                    }
                    else {
                        piece = null;
                    }
                } catch (WrongFieldException exc) {
                    GUI.logger.error("WrongFieldException in Tile class.", exc);
                }
            }
            try {
                gui.endGame();
            } catch (IOException | SAXException | WrongArgumentException | ParserConfigurationException exc) {
                GUI.logger.error("Exception in endGame method.", exc);
            }
            canMove();
        });
    }

    private void canMove(){
        int tilesWithNoMoves = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (!board[i][j].hasPiece()) {
                    if (i <= 1) {
                        if (j <= 1) {
                            if ((board[i + 1][j].hasPiece() && board[i + 1][j].getPiece().getType() != player && board[i + 2][j].hasPiece() && board[i + 2][j].getPiece().getType() == player) |
                                    (board[i][j + 1].hasPiece() && board[i][j + 1].getPiece().getType() != player && board[i][j + 2].hasPiece() && board[i][j + 2].getPiece().getType() == player) |
                                    (board[i + 1][j + 1].hasPiece() && board[i + 1][j + 1].getPiece().getType() != player && board[i + 2][j + 2].hasPiece() && board[i + 2][j + 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                        if (j >= 6) {
                            if ((board[i][j - 1].hasPiece() && board[i][j - 1].getPiece().getType() != player && board[i][j - 2].hasPiece() && board[i][j - 2].getPiece().getType() == player) |
                                    (board[i + 1][j].hasPiece() && board[i + 1][j].getPiece().getType() != player && board[i + 2][j].hasPiece() && board[i + 2][j].getPiece().getType() == player) |
                                    (board[i + 1][j - 1].hasPiece() && board[i + 1][j - 1].getPiece().getType() != player && board[i + 2][j - 2].hasPiece() && board[i + 2][j - 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                        if(j >= 2 && j <= 5) {
                            if ((board[i][j - 1].hasPiece() && board[i][j - 1].getPiece().getType() != player && board[i][j - 2].hasPiece() && board[i][j - 2].getPiece().getType() == player) |
                                    (board[i][j + 1].hasPiece() && board[i][j + 1].getPiece().getType() != player && board[i][j + 2].hasPiece() && board[i][j + 2].getPiece().getType() == player) |
                                    (board[i + 1][j].hasPiece() && board[i + 1][j].getPiece().getType() != player && board[i + 2][j].hasPiece() && board[i + 2][j].getPiece().getType() == player) |
                                    (board[i + 1][j - 1].hasPiece() && board[i + 1][j - 1].getPiece().getType() != player && board[i + 2][j - 2].hasPiece() && board[i + 2][j - 2].getPiece().getType() == player) |
                                    (board[i + 1][j + 1].hasPiece() && board[i + 1][j + 1].getPiece().getType() != player && board[i + 2][j + 2].hasPiece() && board[i + 2][j + 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                    }
                    if (i >= 6) {
                        if (j <= 1) {
                            if ((board[i][j + 1].hasPiece() && board[i][j + 1].getPiece().getType() != player && board[i][j + 2].hasPiece() && board[i][j + 2].getPiece().getType() == player) |
                                    (board[i - 1][j].hasPiece() && board[i - 1][j].getPiece().getType() != player && board[i - 2][j].hasPiece() && board[i - 2][j].getPiece().getType() == player) |
                                    (board[i - 1][j + 1].hasPiece() && board[i - 1][j + 1].getPiece().getType() != player && board[i - 2][j + 2].hasPiece() && board[i - 2][j + 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                        if (j >= 6) {
                            if ((board[i - 1][j].hasPiece() && board[i - 1][j].getPiece().getType() != player && board[i - 2][j].hasPiece() && board[i - 2][j].getPiece().getType() == player) |
                                    (board[i][j - 1].hasPiece() && board[i][j - 1].getPiece().getType() != player && board[i][j - 2].hasPiece() && board[i][j - 2].getPiece().getType() == player) |
                                    (board[i - 1][j - 1].hasPiece() && board[i - 1][j - 1].getPiece().getType() != player && board[i - 2][j - 2].hasPiece() && board[i - 2][j - 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                        if(j >= 2 && j <= 5) {
                            if ((board[i - 1][j].hasPiece() && board[i - 1][j].getPiece().getType() != player && board[i - 2][j].hasPiece() && board[i - 2][j].getPiece().getType() == player) |
                                    (board[i][j - 1].hasPiece() && board[i][j - 1].getPiece().getType() != player && board[i][j - 2].hasPiece() && board[i][j - 2].getPiece().getType() == player) |
                                    (board[i - 1][j - 1].hasPiece() && board[i - 1][j - 1].getPiece().getType() != player && board[i - 2][j - 2].hasPiece() && board[i - 2][j - 2].getPiece().getType() == player) |
                                    (board[i][j + 1].hasPiece() && board[i][j + 1].getPiece().getType() != player && board[i][j + 2].hasPiece() && board[i][j + 2].getPiece().getType() == player) |
                                    (board[i - 1][j + 1].hasPiece() && board[i - 1][j + 1].getPiece().getType() != player && board[i - 2][j + 2].hasPiece() && board[i - 2][j + 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                    }
                    if (i >= 2 && i <= 5) {
                        if (j <= 1) {
                            if ((board[i - 1][j].hasPiece() && board[i - 1][j].getPiece().getType() != player && board[i - 2][j].hasPiece() && board[i - 2][j].getPiece().getType() == player) |
                                    (board[i + 1][j].hasPiece() && board[i + 1][j].getPiece().getType() != player && board[i + 2][j].hasPiece() && board[i + 2][j].getPiece().getType() == player) |
                                    (board[i][j + 1].hasPiece() && board[i][j + 1].getPiece().getType() != player && board[i][j + 2].hasPiece() && board[i][j + 2].getPiece().getType() == player) |
                                    (board[i - 1][j + 1].hasPiece() && board[i - 1][j + 1].getPiece().getType() != player && board[i - 2][j + 2].hasPiece() && board[i - 2][j + 2].getPiece().getType() == player) |
                                    (board[i + 1][j + 1].hasPiece() && board[i + 1][j + 1].getPiece().getType() != player && board[i + 2][j + 2].hasPiece() && board[i + 2][j + 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                        if (j >= 6) {
                            if ((board[i - 1][j].hasPiece() && board[i - 1][j].getPiece().getType() != player && board[i - 2][j].hasPiece() && board[i - 2][j].getPiece().getType() == player) |
                                    (board[i + 1][j].hasPiece() && board[i + 1][j].getPiece().getType() != player && board[i + 2][j].hasPiece() && board[i + 2][j].getPiece().getType() == player) |
                                    (board[i][j - 1].hasPiece() && board[i][j - 1].getPiece().getType() != player && board[i][j - 2].hasPiece() && board[i][j - 2].getPiece().getType() == player) |
                                    (board[i - 1][j - 1].hasPiece() && board[i - 1][j - 1].getPiece().getType() != player && board[i - 2][j - 2].hasPiece() && board[i - 2][j - 2].getPiece().getType() == player) |
                                    (board[i + 1][j - 1].hasPiece() && board[i + 1][j - 1].getPiece().getType() != player && board[i + 2][j - 2].hasPiece() && board[i + 2][j - 2].getPiece().getType() == player)) {
                            } else tilesWithNoMoves++;
                        }
                    }
                    if(i >= 2 && i <= 5 && j >= 2 && j <=5) {
                        if ((board[i - 1][j].hasPiece() && board[i - 1][j].getPiece().getType() != player && board[i - 2][j].hasPiece() && board[i - 2][j].getPiece().getType() == player) |
                                (board[i + 1][j].hasPiece() && board[i + 1][j].getPiece().getType() != player && board[i + 2][j].hasPiece() && board[i + 2][j].getPiece().getType() == player) |
                                (board[i][j - 1].hasPiece() && board[i][j - 1].getPiece().getType() != player && board[i][j - 2].hasPiece() && board[i][j - 2].getPiece().getType() == player) |
                                (board[i][j + 1].hasPiece() && board[i][j + 1].getPiece().getType() != player && board[i][j + 2].hasPiece() && board[i][j + 2].getPiece().getType() == player) |
                                (board[i - 1][j - 1].hasPiece() && board[i - 1][j - 1].getPiece().getType() != player && board[i - 2][j - 2].hasPiece() && board[i - 2][j - 2].getPiece().getType() == player) |
                                (board[i - 1][j + 1].hasPiece() && board[i - 1][j + 1].getPiece().getType() != player && board[i - 2][j + 2].hasPiece() && board[i - 2][j + 2].getPiece().getType() == player) |
                                (board[i + 1][j - 1].hasPiece() && board[i + 1][j - 1].getPiece().getType() != player && board[i + 2][j - 2].hasPiece() && board[i + 2][j - 2].getPiece().getType() == player) |
                                (board[i + 1][j + 1].hasPiece() && board[i + 1][j + 1].getPiece().getType() != player && board[i + 2][j + 2].hasPiece() && board[i + 2][j + 2].getPiece().getType() == player)) { }
                        else tilesWithNoMoves++;
                    }
                }
            }
        }
        if(emptyTileList.size() == tilesWithNoMoves)
            player = changePlayer(player);
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
