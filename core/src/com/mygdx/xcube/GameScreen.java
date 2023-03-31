package com.mygdx.xcube;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

public class GameScreen implements Screen {
        final XCube game;
        public static OrthographicCamera camera;
        public static SpriteBatch spriteBatch;
        Viewport viewport = new FitViewport(800,480);
        public static Terrain terrain;
        public static PlayerManager players;
        private final End end;
        private boolean touchOff = true;
        //private boolean setup= false;
        private final float startTime = 180;
        private float timeLeftBlue = startTime;
        private float timeLeftRed = startTime;
        private int minutesBlue;
        private int secondsBlue;
        private int tenthsBlue;
        private int minutesRed;
        private int secondsRed;
        private int tenthsRed;
        private BitmapFont font;
        private int unitSquare = new HollowSquare(0,0).getSize()[0];
        private int unitX = new HollowBar(false,0,0).getSize()[0];
        private int unitY = new HollowBar(false,0,0).getSize()[1];


        public GameScreen(final XCube game) {
                this.game = game;
                terrain = new Terrain();
                players = new PlayerManager();
                this.end = new End(terrain, players,this.game,this);
                camera = new OrthographicCamera();
                spriteBatch = new SpriteBatch();
                camera.setToOrtho(false, 6*unitY + 5*unitX, 2*(6*unitY + 5*unitX));

        }


        //render s'éxecute toutes les frames

        @SuppressWarnings("DefaultLocale")
        @Override
        public void render(float delta){

                ScreenUtils.clear(1,1,1,1);
                camera.update();
                spriteBatch.setProjectionMatrix(camera.combined);
                if(players.getPlayer()) { //gestion du chronometre bleu
                        timeLeftBlue-=delta;
                        minutesBlue = (int) (timeLeftBlue / 60);
                        secondsBlue = (int) ((timeLeftBlue) % 60);
                        tenthsBlue = (int) ((timeLeftBlue*10) % 10);
                        if(timeLeftBlue<0) { //vérifie si le temps bleu n'est pas écoulé
                                setVictoryScreen(false);
                        }
                }
                else { //gestion du chronometre rouge
                        timeLeftRed-=delta;
                        minutesRed = (int) (timeLeftRed/ 60);
                        secondsRed = (int) ((timeLeftRed) % 60);
                        tenthsRed = (int) ((timeLeftRed*10) % 10);
                        if(timeLeftRed<0) { //vérifie que le temps rouge n'est pas écoulé
                                setVictoryScreen(true);
                        }
                }
                //Affichage des 2 chronomètres rouge et bleu
                spriteBatch.begin();
                font.setColor(Color.BLUE); // Police bleue pour le premier chronomètre
                font.draw(spriteBatch, String.format("%01d:%02d.%d",minutesBlue,secondsBlue,tenthsBlue), unitY, ((6*unitY + 5*unitX)*7/4));
                font.setColor(Color.RED); // Police rouge pour le deuxième chronomètre
                font.draw(spriteBatch, String.format("%01d:%02d.%d",minutesRed,secondsRed,tenthsRed), unitY, (6*unitY + 5*unitX)/4);
                spriteBatch.end();
                for (HollowBar b : terrain.getBar()) {
                        b.drawBlock();                         // Dessine le terrain
                }
                for (HollowSquare b : terrain.getSquare()) {
                        b.drawBlock();                         // Dessine le terrain
                }
                        if (Gdx.input.isTouched() && touchOff) {
                                touchOff = false;
                                for (HollowBar b : terrain.getBar()) {
                                        if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("blue_bar_previous.png", end);
                                        } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("red_bar_previous.png", end);
                                        }
                                }
                                for (int i=0; i<terrain.getSquare().size; i++) {
                                        if (players.getPlayer()) {
                                                terrain.getSquare().get(i).clickBlock("blue_cross_previous.png", end);
                                        } else {
                                                terrain.getSquare().get(i).clickBlock("red_cross_previous.png", end);
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
                font.getData().setScale(20); // Augmente l'échelle de la police d'écriture
                minutesBlue = (int) (timeLeftBlue/ 60);
                secondsBlue = (int) ((timeLeftBlue) % 60);
                tenthsBlue = (int) ((timeLeftBlue*10) % 10);
                minutesRed = (int) (timeLeftRed/ 60);
                secondsRed = (int) ((timeLeftRed) % 60);
                tenthsRed = (int) ((timeLeftRed*10) % 10);
                // Définition du temps de départ et du temps restant (10 secondes)
                /*startTime = TimeUtils.millis();
                Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                                long elapsedTime = TimeUtils.timeSinceMillis(startTime);
                                timeLeft = Math.max(0, 120000 - elapsedTime);
                        }
                }, 0, 0.1f);*/
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
