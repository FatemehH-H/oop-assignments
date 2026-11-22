package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PacMan2 extends Rectangle {
    public final double WIDTH = 40;
    public final double HEIGHT = 50;

    public final Game2 game2;

    public PacMan2(Game2 game2) {
        super(40 , 50);
        setX((game2.WIDTH -  WIDTH)/2);
        setY(game2.HEIGHT -  WIDTH - 50);
        setFill(new ImagePattern(new Image(PacMan.class.getResource("/Images/up.png").toExternalForm())));
        this.game2 = game2;
    }
}
