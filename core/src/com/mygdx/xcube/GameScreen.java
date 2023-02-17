package com.mygdx.xcube;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

public class GameScreen implements Screen {
        final XCube game;
        public static OrthographicCamera camera;
        public static SpriteBatch spriteBatch = new SpriteBatch();
        public static Terrain terrain = new Terrain();
        public static PlayerManager players= new PlayerManager();
        private End end;
        private boolean touchOff = true;
        private boolean setup= false;
        public GameScreen(final XCube game) {
                this.game = game;
                this.end = new End(this.terrain, this.players,this.game,this);
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 3000, 3000);
        }


        //render s'éxecute toutes les frames

        @Override
        public void render(float delta){
                ScreenUtils.clear(1,1,1,1);
                camera.update();
                spriteBatch.setProjectionMatrix(camera.combined);

                        for (HollowBar b : terrain.getBar()) {
                                b.drawBlock();                          // Dessine le terrain
                        }
                        for (HollowSquare b : terrain.getSquare()) {
                                b.drawBlock();                          // Dessine le terrain
                        }

                        if (Gdx.input.isTouched() && touchOff) {
                                touchOff = false;
                                for (HollowBar b : terrain.getBar()) {
                                        if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("blue_bar.png", end);
                                        } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("red_bar.png", end);
                                        }
                                }
                                for (int i=0; i<terrain.getSquare().size; i++) {
                                        if (players.getPlayer()) {
                                                terrain.getSquare().get(i).clickBlock("blue_square.png", end);
                                        } else {
                                                terrain.getSquare().get(i).clickBlock("red_square.png", end);
                                        }
                                }
                        }
                        if (!Gdx.input.isTouched()) {
                                touchOff = true;
                        }
        }
        public void setVictoryScreen(boolean winner){
                game.setScreen(new EndScreen(game, winner));

        }
        @Override
        public void show() {

        }
        // Fonctions inutilisées
        @Override
        public void resize(int width, int height) {
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
        game.dispose();
        spriteBatch.dispose();
        }
}
