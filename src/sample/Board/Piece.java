package sample.Board;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static sample.ReversiApp.TILE_SIZE;

public class Piece extends StackPane {
    private PieceType type;
    private Ellipse ellipse;
    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
        if (type == PieceType.BLACK) ellipse.setFill(Color.valueOf("#222222"));
        else ellipse.setFill(Color.valueOf("#EEEEEE"));
    }


    public Piece(PieceType type, int x, int y){
        this.type = type;

        relocate(x*TILE_SIZE, y*TILE_SIZE);


        ellipse = new Ellipse(TILE_SIZE*0.32, TILE_SIZE * 0.32);
        ellipse.setFill(type == PieceType.BLACK ? Color.valueOf("#222222"): Color.valueOf("#EEEEEE"));

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE*0.32*2)/2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE*0.32*2)/2);

        getChildren().add(ellipse);
    }

}
