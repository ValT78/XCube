package com.mygdx.xcube;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;
import com.mygdx.xcube.Terrain;
import com.mygdx.xcube.PlayerManager;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.xcube.XCube;
public class EndScreen implements Screen {
    private Array<HollowSquare> square;
    //private Terrain terrain;
    private PlayerManager players;
    public static OrthographicCamera camera;
    final XCube game;
    public EndScreen(Terrain terrain, PlayerManager players, XCube game){
        this.square = terrain.getSquare();
        this.players = players;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,400,800);
    }

    public void winTest(){
        if(players.getPlayer()==true){
            if(square.get(12).status==0 && square.get(13).status==0 && square.get(14).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(13).status==0 && square.get(14).status==0 && square.get(15).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(8).status==0 && square.get(9).status==0 && square.get(10).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(9).status==0 && square.get(10).status==0 && square.get(11).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(4).status==0 && square.get(5).status==0 && square.get(6).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(5).status==0 && square.get(6).status==0 && square.get(7).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(0).status==0 && square.get(1).status==0 && square.get(2).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(1).status==0 && square.get(2).status==0 && square.get(3).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(12).status==0 && square.get(8).status==0 && square.get(4).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(8).status==0 && square.get(4).status==0 && square.get(0).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(13).status==0 && square.get(9).status==0 && square.get(5).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(9).status==0 && square.get(5).status==0 && square.get(1).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(14).status==0 && square.get(10).status==0 && square.get(6).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(10).status==0 && square.get(6).status==0 && square.get(2).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(15).status==0 && square.get(11).status==0 && square.get(7).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(11).status==0 && square.get(7).status==0 && square.get(3).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(14).status==0 && square.get(9).status==0 && square.get(4).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(15).status==0 && square.get(10).status==0 && square.get(15).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(10).status==0 && square.get(5).status==0 && square.get(0).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(11).status==0 && square.get(6).status==0 && square.get(1).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(8).status==0 && square.get(5).status==0 && square.get(2).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(12).status==0 && square.get(9).status==0 && square.get(6).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(9).status==0 && square.get(6).status==0 && square.get(3).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(13).status==0 && square.get(10).status==0 && square.get(7).status==0){
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
        }
        else{
            if(square.get(12).status==1 && square.get(13).status==1 && square.get(14).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(13).status==1 && square.get(14).status==1 && square.get(15).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(8).status==1 && square.get(9).status==1 && square.get(10).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(9).status==1 && square.get(10).status==1 && square.get(11).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(4).status==1 && square.get(5).status==1 && square.get(6).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(5).status==1 && square.get(6).status==1 && square.get(7).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(0).status==1 && square.get(1).status==1 && square.get(2).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(1).status==1 && square.get(2).status==1 && square.get(3).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(12).status==1 && square.get(8).status==1 && square.get(4).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(8).status==1 && square.get(4).status==1 && square.get(0).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(13).status==1 && square.get(9).status==1 && square.get(5).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(9).status==1 && square.get(5).status==1 && square.get(1).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(14).status==1 && square.get(10).status==1 && square.get(6).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(10).status==1 && square.get(6).status==1 && square.get(2).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(15).status==1 && square.get(11).status==1 && square.get(7).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(11).status==1 && square.get(7).status==1 && square.get(3).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(14).status==1 && square.get(9).status==1 && square.get(4).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(15).status==1 && square.get(10).status==1 && square.get(15).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(10).status==1 && square.get(5).status==1 && square.get(0).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(11).status==1 && square.get(6).status==1 && square.get(1).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(8).status==1 && square.get(5).status==1 && square.get(2).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(12).status==1 && square.get(9).status==1 && square.get(6).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(9).status==1 && square.get(6).status==1 && square.get(3).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(square.get(13).status==1 && square.get(10).status==1 && square.get(7).status==1){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red Wins ! ",100,250);
                game.batch.end();       // Fin des éléments à afficher
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
        game.dispose();
    }
}

