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

    public void checkAlign(boolean player, End end) { //vérifie si il y a un alignement avec les cases du joueur qui vient de jouer
        for (HollowSquare square: GameScreen.terrain.getSquare()) { //boucle for sur chaque carré

            if(!square.isFree && square.isBlue==player) { //on ne choisit que ceux qui sont pleins et de la couleur de celui qui vient de jouer
                if (square.isHorizontal) { //On vérifie si le bloc à droite et à gauche existent, puis s'ils sont de la même couleur que celui du milieu
                    if (square.horizontal[0].isBlue==player && square.horizontal[1].isBlue==player && !square.horizontal[0].isFree && !square.horizontal[1].isFree) {
                        end.screen.setVictoryScreen(player);
                    }
                }
                if (square.isVertical) {//pareil avec celui en bas et en haut
                    if (square.vertical[0].isBlue==player && square.vertical[1].isBlue==player && !square.vertical[0].isFree && !square.vertical[1].isFree ) {
                        end.screen.setVictoryScreen(player);
                    }
                }
                if (square.isDiagonal) {//pareil pour les 2 diagonales
                    if (square.diagonal[0].isBlue==player && square.diagonal[1].isBlue==player && !square.diagonal[0].isFree && !square.diagonal[1].isFree ) {
                        end.screen.setVictoryScreen(player);
                    }
                }
                if (square.isAntidiagonal) {
                    if (square.antidiagonal[0].isBlue==player && square.antidiagonal[1].isBlue==player && !square.antidiagonal[0].isFree && !square.antidiagonal[1].isFree ) {
                        end.screen.setVictoryScreen(player);
                    }
                }
            }
        }
    }
    /*public void winTest(){
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
    }*/


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

