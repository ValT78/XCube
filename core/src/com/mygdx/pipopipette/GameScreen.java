package com.mygdx.pipopipette;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pipopipette.block.HollowBar;
import com.mygdx.pipopipette.block.HollowSquare;

public class GameScreen implements Screen {
        final Pipopipette game;
        public static OrthographicCamera camera;
        public static SpriteBatch spriteBatch = new SpriteBatch();

        private Terrain terrain = new Terrain();
        public static PlayerManager players= new PlayerManager();
        public GameScreen(final Pipopipette game) {
                this.game = game;
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 3000, 3000);
        }


        //render s'éxecute toutes les frames

        @Override
        public void render(float delta){
                ScreenUtils.clear(1,1,1,1);
                camera.update();
                spriteBatch.setProjectionMatrix(camera.combined);
                for(HollowBar b : terrain.getBar()) {
                        if(b.sprite == null) {                  // Initialise les sprites des blocks
                                b.sprite = new Sprite(new Texture(Gdx.files.internal("grey_bar.png")));
                        }
                        b.drawBlock();                          // Dessine le terrain

                }
                for(HollowSquare b : terrain.getSquare()) {
                        if(b.sprite == null) {                  // Initialise les sprites des blocks
                                b.sprite = new Sprite(new Texture(Gdx.files.internal("grey_square.png")));
                        }
                        b.drawBlock();                          // Dessine le terrain
                }
                if(Gdx.input.isTouched()) {

                        for (HollowBar b : terrain.getBar()) {
                                if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                        b.clickBlock("blue_bar.png");
                                } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                        b.clickBlock("red_bar.png");

                                }
                        }
                        for (HollowSquare b : terrain.getSquare()) {
                                if (players.getPlayer()) {
                                        b.clickBlock("blue_square.png");
                                } else {
                                        b.clickBlock("red_square.png");

                                }
                        }
                }


        }

        // Fonctions inutilisées
        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {


        }

        @Override
        public void hide() {
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {


        }
}
