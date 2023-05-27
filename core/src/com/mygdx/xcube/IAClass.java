package com.mygdx.xcube;

public class IAClass implements Runnable {
    GameScreen gameScreen;
    public IAClass(GameScreen gameScreen) {
        this.gameScreen=gameScreen;
    }
    @Override
    public void run() {
        gameScreen.ProcessIA();
    }
}
