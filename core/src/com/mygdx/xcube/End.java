package com.mygdx.xcube;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.xcube.block.HollowSquare;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class End implements Screen {
    private Array<HollowSquare> square;
    //private Terrain terrain;
    private PlayerManager players;
    public static OrthographicCamera camera;
    private GameScreen screen;
    final XCube game;
    public End(Terrain terrain, PlayerManager players, XCube game, GameScreen screen){
        this.square = terrain.getSquare();
        this.players = players;
        this.game = game;
        this.screen = screen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,400,800);
    }

    public void winTest(){
        if(players.getPlayer()==true){
            if(square.get(12).status==0 && square.get(13).status==0 && square.get(14).status==0){
                screen.setBlue();
            }
            if(square.get(13).status==0 && square.get(14).status==0 && square.get(15).status==0){
                screen.setBlue();
            }
            if(square.get(8).status==0 && square.get(9).status==0 && square.get(10).status==0){
                screen.setBlue();
            }
            if(square.get(9).status==0 && square.get(10).status==0 && square.get(11).status==0){
                screen.setBlue();
            }
            if(square.get(4).status==0 && square.get(5).status==0 && square.get(6).status==0){
                screen.setBlue();
            }
            if(square.get(5).status==0 && square.get(6).status==0 && square.get(7).status==0){
                screen.setBlue();
            }
            if(square.get(0).status==0 && square.get(1).status==0 && square.get(2).status==0){
                screen.setBlue();
            }
            if(square.get(1).status==0 && square.get(2).status==0 && square.get(3).status==0){
                screen.setBlue();
            }
            if(square.get(12).status==0 && square.get(8).status==0 && square.get(4).status==0){
                screen.setBlue();
            }
            if(square.get(8).status==0 && square.get(4).status==0 && square.get(0).status==0){
                screen.setBlue();
            }
            if(square.get(13).status==0 && square.get(9).status==0 && square.get(5).status==0){
                screen.setBlue();
            }
            if(square.get(9).status==0 && square.get(5).status==0 && square.get(1).status==0){
                screen.setBlue();
            }
            if(square.get(14).status==0 && square.get(10).status==0 && square.get(6).status==0){
                screen.setBlue();
            }
            if(square.get(10).status==0 && square.get(6).status==0 && square.get(2).status==0){
                screen.setBlue();
            }
            if(square.get(15).status==0 && square.get(11).status==0 && square.get(7).status==0){
                screen.setBlue();
            }
            if(square.get(11).status==0 && square.get(7).status==0 && square.get(3).status==0){
                screen.setBlue();
            }
            if(square.get(14).status==0 && square.get(9).status==0 && square.get(4).status==0){
                screen.setBlue();
            }
            if(square.get(15).status==0 && square.get(10).status==0 && square.get(15).status==0){
                screen.setBlue();
            }
            if(square.get(10).status==0 && square.get(5).status==0 && square.get(0).status==0){
                screen.setBlue();
            }
            if(square.get(11).status==0 && square.get(6).status==0 && square.get(1).status==0){
                screen.setBlue();
            }
            if(square.get(8).status==0 && square.get(5).status==0 && square.get(2).status==0){
                screen.setBlue();
            }
            if(square.get(12).status==0 && square.get(9).status==0 && square.get(6).status==0){
                screen.setBlue();
            }
            if(square.get(9).status==0 && square.get(6).status==0 && square.get(3).status==0){
                screen.setBlue();
            }
            if(square.get(13).status==0 && square.get(10).status==0 && square.get(7).status==0){
                screen.setBlue();
            }
        }
        else{
            if(square.get(12).status==1 && square.get(13).status==1 && square.get(14).status==1){
                screen.setRed();
            }
            if(square.get(13).status==1 && square.get(14).status==1 && square.get(15).status==1){
                screen.setRed();
            }
            if(square.get(8).status==1 && square.get(9).status==1 && square.get(10).status==1){
                screen.setRed();
            }
            if(square.get(9).status==1 && square.get(10).status==1 && square.get(11).status==1){
                screen.setRed();
            }
            if(square.get(4).status==1 && square.get(5).status==1 && square.get(6).status==1){
                screen.setRed();
            }
            if(square.get(5).status==1 && square.get(6).status==1 && square.get(7).status==1){
                screen.setRed();
            }
            if(square.get(0).status==1 && square.get(1).status==1 && square.get(2).status==1){
                screen.setRed();
            }
            if(square.get(1).status==1 && square.get(2).status==1 && square.get(3).status==1){
                screen.setRed();
            }
            if(square.get(12).status==1 && square.get(8).status==1 && square.get(4).status==1){
                screen.setRed();
            }
            if(square.get(8).status==1 && square.get(4).status==1 && square.get(0).status==1){
                screen.setRed();
            }
            if(square.get(13).status==1 && square.get(9).status==1 && square.get(5).status==1){
                screen.setRed();
            }
            if(square.get(9).status==1 && square.get(5).status==1 && square.get(1).status==1){
                screen.setRed();
            }
            if(square.get(14).status==1 && square.get(10).status==1 && square.get(6).status==1){
                screen.setRed();
            }
            if(square.get(10).status==1 && square.get(6).status==1 && square.get(2).status==1){
                screen.setRed();
            }
            if(square.get(15).status==1 && square.get(11).status==1 && square.get(7).status==1){
                screen.setRed();
            }
            if(square.get(11).status==1 && square.get(7).status==1 && square.get(3).status==1){
                screen.setRed();
            }
            if(square.get(14).status==1 && square.get(9).status==1 && square.get(4).status==1){
                screen.setRed();
            }
            if(square.get(15).status==1 && square.get(10).status==1 && square.get(15).status==1){
                screen.setRed();
            }
            if(square.get(10).status==1 && square.get(5).status==1 && square.get(0).status==1){
                screen.setRed();
            }
            if(square.get(11).status==1 && square.get(6).status==1 && square.get(1).status==1){
                screen.setRed();
            }
            if(square.get(8).status==1 && square.get(5).status==1 && square.get(2).status==1){
                screen.setRed();
            }
            if(square.get(12).status==1 && square.get(9).status==1 && square.get(6).status==1){
                screen.setRed();
            }
            if(square.get(9).status==1 && square.get(6).status==1 && square.get(3).status==1){
                screen.setRed();
            }
            if(square.get(13).status==1 && square.get(10).status==1 && square.get(7).status==1){
                screen.setRed();
            }
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //game.dispose();
    }
}

