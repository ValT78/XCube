package com.mygdx.xcube;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

public class GameScreen implements Screen {
        final XCube game;
        public static OrthographicCamera camera;
        public static SpriteBatch spriteBatch;
        public static Terrain terrain;
        public static PlayerManager players;
        private End end;
        private boolean touchOff = true;
        private boolean setup= false;
        private long startTime = 10000;
        private long timeLeft = 0;
        private BitmapFont font;
        public GameScreen(final XCube game) {
                this.game = game;
                this.terrain = new Terrain();
                this.players = new PlayerManager();
                this.end = new End(this.terrain, this.players,this.game,this);
                this.camera = new OrthographicCamera();
                this.spriteBatch = new SpriteBatch();
                camera.setToOrtho(false, 3000, 6150);
        }


        //render s'éxecute toutes les frames

        @Override
        public void render(float delta){

                ScreenUtils.clear(1,1,1,1);
                camera.update();
                spriteBatch.setProjectionMatrix(camera.combined);
                int minutes = (int) (timeLeft / 1000 / 60);
                int seconds = (int) ((timeLeft / 1000) % 60);
                int tenths = (int) ((timeLeft / 100) % 10);
                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                spriteBatch.begin();
                String str =Integer.toString(minutes) + Integer.toString(seconds) + Integer.toString(tenths);
                game.font.draw(spriteBatch, "ijio", 1000, 2000);
                spriteBatch.end();

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
                // Initialisation de la police d'écriture
                font = new BitmapFont();

                // Définition du temps de départ et du temps restant (10 secondes)
                startTime = TimeUtils.millis();
                Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                                long elapsedTime = TimeUtils.timeSinceMillis(startTime);
                                timeLeft = Math.max(0, 10000 - elapsedTime);
                        }
                }, 0, 0.1f);
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
        }
}
