package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;
import model.Ghost;
import model.Map;
import javafx.scene.text.Font;
import model.PacMan;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.AudioClip;
import java.nio.file.Paths;


import javafx.scene.control.Label;


public class GameLauncher extends Application {
    public final double WIDTH = 500;
    public final double HEIGHT = 500;

    public final Game game;
    public Pane pane;
    private Pane overlayPane;
    private Text gameOverText;
    private Text winText;
    private AnimationTimer gameLoopTimer;
    private AnimationTimer ghostMovementTimer;
    private AudioClip dotEatSound;
    private AudioClip gameOverSound;
    private AudioClip winSound;
    private AudioClip currentlyPlayingSound;
    public PacMan pacMan;
    public Map map;
    public List<Rectangle> filledRectangles;
    private int score = 0;
    private Label scoreLabel;
    private final int ghostMovementBoundX = 20;
    private final int ghostMovementBoundY = 20;
    private final int ghostMovementBoundWidth = 458;
    private final int ghostMovementBoundHeight = 435;

    private List<Ghost> ghosts;

    public GameLauncher(String userName) {
        game = new Game(userName , this);
    }
    @Override
    public void start(Stage stage) throws Exception {
        pane = new Pane();
        setSize(pane);
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        createPacMan();
        filledRectangles = new ArrayList<>();
        dotEatSound = new AudioClip(Paths.get("src/main/resources/Media/pacman_eatfruit.wav").toUri().toString());
        gameOverSound = new AudioClip(Paths.get("src/main/resources/Media/pacman_death.wav").toUri().toString());
        winSound = new AudioClip(Paths.get("src/main/resources/Media/LUKRJDW-win.mp3").toUri().toString());
        winSound.setVolume(1.0);
        winSound.setBalance(0.0);
        winSound.setRate(1.0);


        ghosts = new ArrayList<>();
        initializeGhosts();
        gameOverText = new Text("Game Over");
        gameOverText.setFont(new Font("Arial", 48));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(WIDTH / 2 - 150);
        gameOverText.setY(HEIGHT / 2);
        gameOverText.setVisible(false);
        pane.getChildren().add(gameOverText);


        startGhostMovement();


        pane.getChildren().add(createMap(458,435,20,20));  //background

        filledRectangles.add(createMapFilled(70, 75, 70, 80)); // top left
        filledRectangles.add(createMapFilled(200, 90, 240, 300)); // bottom right
        filledRectangles.add(createMapFilled(200, 95, 200, 80)); // top right
        filledRectangles.add(createMapFilled(60, 150, 70, 240)); // bottom left

        for (Rectangle rect : filledRectangles) {
            pane.getChildren().add(rect);
        }

        createOrderedDots(pane, 40, 3, 20, 20, 500, 470);

        pane.getChildren().add(pacMan);

        scoreLabel = new Label("Score: 0");
        scoreLabel.setTextFill(Color.YELLOW);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        scoreLabel.setLayoutX(15);
        scoreLabel.setLayoutY(HEIGHT - 30);

        pane.getChildren().add(scoreLabel);
        // Initialize and configure the overlay pane
        overlayPane = new Pane();
        overlayPane.setStyle("-fx-background-color: black;");
        overlayPane.setVisible(false);
        overlayPane.setPrefSize(WIDTH, HEIGHT);

        gameOverText = new Text("Game Over");
        gameOverText.setFont(new Font("Arial", 48));
        gameOverText.setFill(Color.RED);
        gameOverText.setVisible(false);

// Center the text
        gameOverText.setX((WIDTH - gameOverText.getLayoutBounds().getWidth()) / 2);
        gameOverText.setY((HEIGHT - gameOverText.getLayoutBounds().getHeight()) / 2);


        winText = new Text("You Win");
        winText.setFont(new Font("Arial", 48));
        winText.setFill(Color.GREEN);
        winText.setVisible(false);

        winText.setX((WIDTH - winText.getLayoutBounds().getWidth()) / 2);
        winText.setY((HEIGHT - winText.getLayoutBounds().getHeight()) / 2);

        overlayPane.getChildren().addAll(gameOverText, winText);

        pane.getChildren().add(overlayPane);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case G:
                    try {

                        restartGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        });
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        pacMan.requestFocus();

        startGameLoop();
    }



    private void createPacMan() {
        pacMan = new PacMan(game);
        pacMan.setOnKeyPressed(keyEvent -> {
            double newX = pacMan.getX();
            double newY = pacMan.getY();

            if (keyEvent.getCode() == KeyCode.D) {
                newX = Math.min(pacMan.getX() + 20, WIDTH - pacMan.getWidth() - 20);
                pacMan.setFill(new ImagePattern(new Image(PacMan.class.getResource("/Images/right.png").toExternalForm())));
            } else if (keyEvent.getCode() == KeyCode.A) {
                newX = Math.max(pacMan.getX() - 20, 20);
                pacMan.setFill(new ImagePattern(new Image(PacMan.class.getResource("/Images/left.png").toExternalForm())));
            } else if (keyEvent.getCode() == KeyCode.S) {
                newY = Math.min(pacMan.getY() + 20, HEIGHT - pacMan.getHeight() - 50);
                pacMan.setFill(new ImagePattern(new Image(PacMan.class.getResource("/Images/down.png").toExternalForm())));
            } else if (keyEvent.getCode() == KeyCode.W) {
                newY = Math.max(pacMan.getY() - 20, 20);
                pacMan.setFill(new ImagePattern(new Image(PacMan.class.getResource("/Images/up.png").toExternalForm())));
            }

            if (!isCollidingWithFilledRectangle(newX, newY)) {
                pacMan.setX(newX);
                pacMan.setY(newY);
                checkCollisions();
            }
        });
    }


    private Rectangle createMap(int width, int height , int x , int y) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setStroke(Color.BLUE);
        rectangle.setFill(Color.TRANSPARENT);

        return rectangle;

    }

    private Rectangle createMapFilled(int width, int height , int x , int y) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x); // Set the X coordinate of the rectangle
        rectangle.setY(y); // Set the Y coordinate of the rectangle
        rectangle.setWidth(width); // Set the width of the rectangle
        rectangle.setHeight(height); // Set the height of the rectangle
        rectangle.setStroke(Color.BLUE); // Set the stroke color to blue
        rectangle.setFill(Color.BLUE); // Set the fill color to transparent

        return rectangle;

    }




    private void createOrderedDots(Pane pane, int spacing, int dotRadius, int startX, int startY, int width, int height) {
        int endX = startX + width;
        int endY = startY + height;
        int padding = dotRadius * 2;
        for (int x = startX + spacing; x < endX - spacing; x += spacing) {
            for (int y = startY + spacing; y < endY - spacing; y += spacing) {
                Circle dot;
                if ((x + y) % 30 == 0) { // Example condition to make some dots special
                    dot = new Circle(x, y, dotRadius * 2, Color.YELLOW); // Larger, bolder dot
                    dot.setId("special"); // Set an ID to identify special dots
                } else {
                    dot = new Circle(x, y, dotRadius, Color.WHITE); // Regular dot
                    dot.setId("regular"); // Set an ID to identify regular dots
                }
                if (!isDotCloseToFilledRectangle(dot, padding)) {
                    pane.getChildren().add(dot);
                }
            }
        }
    }



    private boolean isDotCloseToFilledRectangle(Circle dot, int padding) {
        for (Rectangle rect : filledRectangles) {
            if (dot.getCenterX() > rect.getX() - padding && dot.getCenterX() < rect.getX() + rect.getWidth() + padding &&
                    dot.getCenterY() > rect.getY() - padding && dot.getCenterY() < rect.getY() + rect.getHeight() + padding) {
                return true;
            }
        }
        return false;
    }


    private boolean isCollidingWithFilledRectangle(double newX, double newY) {
        for (Rectangle rect : filledRectangles) {
            if (newX + pacMan.getWidth() > rect.getX() && newX < rect.getX() + rect.getWidth() &&
                    newY + pacMan.getHeight() > rect.getY() && newY < rect.getY() + rect.getHeight()) {
                return true;
            }
        }
        return false;
    }




    private void checkCollisions() {
        List<Node> nodesToRemove = new ArrayList<>();

        for (Node node : pane.getChildren()) {
            if (node instanceof Circle) {
                Circle dot = (Circle) node;
                double pacManCenterX = pacMan.getX() + pacMan.getWidth() / 2;
                double pacManCenterY = pacMan.getY() + pacMan.getHeight() / 2;
                double dotCenterX = dot.getCenterX();
                double dotCenterY = dot.getCenterY();
                double distance = Math.sqrt(Math.pow(pacManCenterX - dotCenterX, 2) + Math.pow(pacManCenterY - dotCenterY, 2));
                if (distance < pacMan.getWidth() / 2 + dot.getRadius()) {
                    nodesToRemove.add(dot);
                    dotEatSound.play();
                    if ("special".equals(dot.getId())) {
                        score += 5;
                    } else {
                        score++;
                    }
                    scoreLabel.setText("Score: " + score);
                }
            }
        }

        pane.getChildren().removeAll(nodesToRemove);

        if (allDotsEaten()) {
            youWin();
        }

        for (Ghost ghost : ghosts) {
            if (pacMan.getBoundsInParent().intersects(ghost.getBoundsInParent())) {
                gameOver();
                return;
            }
        }
    }


    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move();
            if (isGhostCollidingWithBounds(ghost) || isGhostCollidingWithAnyRectangle(ghost)) {
                ghost.changeDirectionOnCollision();
            } else {
                ghost.resetCollision();
            }
        }
    }



    private boolean isGhostCollidingWithBounds(Ghost ghost) {
        double ghostX = ghost.getX();
        double ghostY = ghost.getY();
        double ghostWidth = ghost.getFitWidth();
        double ghostHeight = ghost.getFitHeight();

        return (ghostX <= ghostMovementBoundX || ghostX + ghostWidth >= ghostMovementBoundX + ghostMovementBoundWidth ||
                ghostY <= ghostMovementBoundY || ghostY + ghostHeight >= ghostMovementBoundY + ghostMovementBoundHeight);
    }


    private boolean isGhostCollidingWithAnyRectangle(Ghost ghost) {
        for (Rectangle rect : filledRectangles) {
            if (ghost.isCollidingWithRectangle(rect)) {
                return true;
            }
        }
        return false;
    }


    private void initializeGhosts() {

        double centerX1 = WIDTH / 2 - 15;
        double centerY1 = HEIGHT / 2 - 15;

        double centerX2 = WIDTH / 2 + 15;
        double centerY2 = HEIGHT / 2 + 15;

        double leftTopX = 25;
        double leftTopY = 25;

        double rightBottomX = WIDTH - 100;
        double rightBottomY = HEIGHT - 100;

        addGhostToPane(centerX1, centerY1);

        addGhostToPane(centerX2, centerY2);
        addGhostToPane(leftTopX, leftTopY);
        addGhostToPane(rightBottomX, rightBottomY);
    }

    private void addGhostToPane(double x, double y) {
        Ghost ghost = new Ghost("/Images/ghost.png");
        ghost.setX(x);
        ghost.setY(y);

        if (!isGhostCollidingWithAnyRectangle(ghost)) {
            ghosts.add(ghost);
            pane.getChildren().add(ghost);
        }
    }



    private void setSize(Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxHeight(HEIGHT);
        pane.setMaxWidth(WIDTH);
    }






    private void startGhostMovement() {
        if (ghostMovementTimer != null) {
            ghostMovementTimer.stop();
        }

        ghostMovementTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveGhosts();
            }
        };
        ghostMovementTimer.start();
    }

    private void checkPacManCollisionWithGhosts() {
        for (Ghost ghost : ghosts) {
            if (pacMan.getBoundsInParent().intersects(ghost.getBoundsInParent())) {
                gameOver();
                break;
            }
        }
    }
    private void gameOver() {
        overlayPane.setVisible(true);
        gameOverText.setVisible(true);
        if (currentlyPlayingSound != null) {
            currentlyPlayingSound.stop();
        }
        gameOverSound.play();

        stopGhostMovement();
    }


    private void stopGhostMovement() {
        for (Ghost ghost : ghosts) {
            ghost.setX(0);
            ghost.setY(0);
        }
    }


    private void restartGame() throws Exception {
        overlayPane.setVisible(false);
        gameOverText.setVisible(false);
        winText.setVisible(false);
        pane.getChildren().removeAll(ghosts);
        ghosts.clear();
        resetDots();
        initializeGhosts();
        score = 0;
        scoreLabel.setText("Score: " + score);
        pacMan.setX(WIDTH / 2);
        pacMan.setY(HEIGHT / 2);
        startGhostMovement();
        startGameLoop();
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
        start(new Stage());
    }

    private void startGameLoop() {
        if (gameLoopTimer != null) {
            gameLoopTimer.stop();
        }

        gameLoopTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkCollisions();
            }
        };
        gameLoopTimer.start();
    }


    private void checkDotCollisions() {
        List<Circle> dotsToRemove = new ArrayList<>();
        for (Node node : pane.getChildren()) {
            if (node instanceof Circle && pacMan.getBoundsInParent().intersects(node.getBoundsInParent())) {
                dotsToRemove.add((Circle) node);
            }
        }
        pane.getChildren().removeAll(dotsToRemove);

        if (allDotsEaten()) {
            youWin();
        }
    }

    private boolean allDotsEaten() {
        for (Node node : pane.getChildren()) {
            if (node instanceof Circle) {
                return false;
            }
        }
        return true;
    }

    private void youWin() {
        overlayPane.setVisible(true);
        winText.setVisible(true);
        stopGhostMovement();

        if (currentlyPlayingSound != null) {
            currentlyPlayingSound.stop();
        }

        winSound.play();

    }


    private void resetDots() {

        pane.getChildren().removeIf(node -> node instanceof Circle);

        createOrderedDots(pane, 30, 3, 20, 20, 458, 435);

    }


}


