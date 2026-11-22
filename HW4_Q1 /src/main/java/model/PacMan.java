package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class PacMan extends Rectangle {

    public final double WIDTH = 40;
    public final double HEIGHT = 50;

    public final Game game;

    public PacMan(Game game) {
        super(40 , 50);
        setX((game.WIDTH -  WIDTH)/2);
        setY(game.HEIGHT -  WIDTH - 50);
        setFill(new ImagePattern(new Image(PacMan.class.getResource("/Images/up.png").toExternalForm())));
        this.game = game;
    }


}