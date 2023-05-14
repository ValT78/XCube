package com.mygdx.xcube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {
    final XCube game;
    float inputTime = 0;
    OrthographicCamera camera;
    boolean player;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;
    private int width_screen = 540;
    private int height_screen = 1200;
    public EndScreen(final XCube game,boolean player){
            this.game = game;
            this.player = player;
            camera = new OrthographicCamera();
            camera.setToOrtho(false,width_screen,height_screen);
            fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Avenir.ttf"));
            fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            fontParameter.size = 35;
            font = fontGenerator.generateFont(fontParameter);
        }

        public void render(float delta){            // Boucle infinie d'exécution
            inputTime += delta;
            if(player) {
                ScreenUtils.clear(222/255f,1,1,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();     // Début des éléments à afficher
                font.setColor(Color.BLUE);
                font.draw(game.batch,"Victoire du bleu !",width_screen/4,height_screen/2);
                game.batch.end();       // Fin des éléments à afficher
            }
            if(!player){
                ScreenUtils.clear(1,222/255f,1,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                game.batch.begin();     // Début des éléments à afficher
                font.setColor(Color.RED);
                font.draw(game.batch,"Victoire du rouge !",width_screen/4,height_screen/2);
                game.batch.end();       // Fin des éléments à afficher

            }

            if (Gdx.input.isTouched() && inputTime>0.2f) {
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

