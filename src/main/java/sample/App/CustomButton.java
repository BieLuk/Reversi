package sample.App;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class CustomButton extends Button{
    CustomButton(String text) throws FileNotFoundException {
       super(text);
       super.setStyle("-fx-font: 14 Calibri; -fx-base: #b6e7c9;");
       DropShadow shadow = new DropShadow();
       super.setEffect(shadow);
       FileInputStream inputstream = new FileInputStream("data/exit.png");
       Image image = new Image(inputstream);
       ImageView iv = new ImageView(image);
       iv.setFitHeight(20);
       iv.setFitWidth(20);
       super.setGraphic(iv);
    }


}
