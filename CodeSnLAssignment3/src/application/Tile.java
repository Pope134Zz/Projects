package application;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int x, int y){

        setWidth(Main.tileSize);
        setHeight(Main.tileSize);

        setFill(Color.BLACK);
        setStroke(Color.WHITE);
    }
}
