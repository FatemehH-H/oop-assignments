package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.shape.Rectangle;

public class Ghost extends ImageView {
    private double dx = 0.5;
    private double dy = 0;
    private Direction currentDirection;
    private boolean collision;
    private Direction newDirection;


    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Ghost(String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        setImage(image);
        setFitWidth(30); // Set the width of the ghost image
        setFitHeight(30); // Set the height of the ghost image
        changeDirectionRandomly();
        collision = false;
    }

    public void move() {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public void changeDirectionOnCollision() {
//        if (collision) {
//            return; // Prevent changing direction multiple times during a single collision
//        }

//        newDirection = currentDirection;
        switch (currentDirection) {
            case RIGHT:
                currentDirection = Direction.UP;
                dx = 0;
                dy = -0.5;
                break;
            case LEFT:
                currentDirection = Direction.DOWN;
                dx = 0;
                dy = 0.5;
                break;
            case UP:
                currentDirection = Direction.LEFT;
                dx = -0.5;
                dy = 0;
                break;
            case DOWN:
                currentDirection = Direction.RIGHT;
                dx = 0.5;
                dy = 0;
                break;
        }
        collision = true; // Mark collision as handled

    }


    public void resetCollision() {
        collision = false;
    }

    public void changeDirectionRandomly() {
        Direction[] directions = Direction.values();
        currentDirection = directions[(int) (Math.random() * directions.length)];
        updateVelocity();
    }

    private void updateVelocity() {
        switch (currentDirection) {
            case RIGHT:
                dx = 0.5;
                dy = 0;
                break;
            case LEFT:
                dx = -0.5;
                dy = 0;
                break;
            case UP:
                dx = 0;
                dy = -0.5;
                break;
            case DOWN:
                dx = 0;
                dy = 0.5;
                break;
        }
    }

    public boolean isCollidingWithRectangle(Rectangle rect) {
        return getBoundsInParent().intersects(rect.getBoundsInParent());
    }
}
