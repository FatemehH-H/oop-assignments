package model;

import view.GameLauncher2;

public class Game2 {
    public final double WIDTH = 500;
    public final double HEIGHT = 500;

    public String mapNumber;

    public int score =  0;

    public GameLauncher2 gameLauncher2;

    public Game2(String mapNumber , GameLauncher2 gameLauncher2) {
        this.mapNumber = mapNumber;
        this.gameLauncher2 = gameLauncher2;
    }
}
