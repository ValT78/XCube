package com.mygdx.xcube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {
    final XCube game;

    OrthographicCamera camera;
    boolean player;
    public EndScreen(final XCube game,boolean player){
            this.game = game;
            this.player = player;
            camera = new OrthographicCamera();
            camera.setToOrtho(false,400,400);
        }

        public void render(float delta){            // Boucle infinie d'exécution

            if(player) {
                ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Blue team win ! ", 200, 200);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(!player){
                ScreenUtils.clear(0.2f,0,0,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                game.font.draw(game.batch, "Red team win ! ", 200, 200);
                game.batch.end();       // Fin des éléments à afficher

            }

            if (Gdx.input.isTouched()) {
                game.dispose();
                game.create();
            }


        }

        // Fonctions non utilisées recquises par l'implementation de screen
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

        }

        public void show(){

        }
        public void rezise(int x,int y){

        }
    }

