package model;

import view.GameLauncher;

public class Game {
    public final double WIDTH = 500;
    public final double HEIGHT = 500;

    public String mapNumber;

    public int score =  0;

    public GameLauncher gameLauncher;

    public Game(String mapNumber , GameLauncher gameLauncher) {
        this.mapNumber = mapNumber;
        this.gameLauncher = gameLauncher;
    }


}
