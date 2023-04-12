package com.mygdx.xcube;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;

import javax.swing.Renderer;

public class GameScreen implements Screen {
        final XCube game;
        private boolean color;
        public static OrthographicCamera camera;
        //Viewport viewport = new FitViewport(800,480);
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
        private static int mode;
        private int coupIA;
        private Vector3 touchPos = new Vector3();
        //private final int unitSquare = new HollowSquare(0,0).getSize()[0];
        private final int unitX = new HollowBar(false,0,0).getSize()[0];
        private final int unitY = new HollowBar(false,0,0).getSize()[1];
        private Renderer RenderMode;
        private FreeTypeFontGenerator fontGenerator;
        private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
        private BitmapFont font;
        //private SpriteBatch batch;

        public GameScreen(final XCube game,int mode) {
                this.game = game;
                terrain = new Terrain();
                players = new PlayerManager();
                this.end = new End(terrain, players,this.game,this);
                camera = new OrthographicCamera();
                GameScreen.mode = mode;
                camera.setToOrtho(false, 6*unitY + 5*unitX, 2*(6*unitY + 5*unitX));
                //game.batch = new SpriteBatch();
                fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
                fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                fontParameter.size = 150;
                //fontParameter.color = Color.BLUE;
                font = fontGenerator.generateFont(fontParameter);
        }


        //render s'éxecute toutes les frames

        @SuppressWarnings("DefaultLocale")

        @Override
        public void render(float delta){
                ScreenUtils.clear(1,1,1,1);
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
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
                game.batch.begin();
                //font.setColor(Color.BLUE);  Police bleue pour le premier chronomètre
                //font.draw(game.batch, String.format("%01d:%02d.%d",minutesBlue,secondsBlue,tenthsBlue), unitY, ((6*unitY + 5*unitX)*7/4));
                //font.setColor(Color.RED);  Police rouge pour le deuxième chronomètre
                font.setColor(Color.BLUE);
                font.draw(game.batch, String.format("%01d : %02d.%d",minutesBlue,secondsBlue,tenthsBlue), unitY, ((6*unitY + 5*unitX)*7/4));
                font.setColor(Color.RED);
                font.draw(game.batch, String.format("%01d:%02d.%d",minutesRed,secondsRed,tenthsRed), unitY, (6*unitY + 5*unitX)/4);
                game.batch.end();
                for (HollowBar b : terrain.getBar()) {
                        b.drawBlock(game.batch);                         // Dessine le terrain
                }
                for (HollowSquare b : terrain.getSquare()) {
                        b.drawBlock(game.batch);                         // Dessine le terrain
                }

                switch (mode) {
                        case 0:
                                RendererLocal.run();
                                break;
                        case 1:
                                RendererMulti.run();
                                break;
                        case 2:
                                RendererIA.run();
                                 break;
                }




        }
        public void setTouchPos(Vector3 touchPos){
                this.touchPos = touchPos;
        }
        public void setVictoryScreen(boolean winner){
                if(mode == 1) {
                        Multiplayer.disconnected();
                }
                game.setScreen(new EndScreen(game, winner));
        }
        @Override
        public void show() {


                // Initialisation de la police d'écriture
                //font = new BitmapFont();
                //font.getData().setScale(20);  Augmente l'échelle de la police d'écriture
                minutesBlue = (int) (timeLeftBlue / 60);
                secondsBlue = (int) ((timeLeftBlue) % 60);
                tenthsBlue = (int) ((timeLeftBlue * 10) % 10);
                minutesRed = (int) (timeLeftRed / 60);
                secondsRed = (int) ((timeLeftRed) % 60);
                tenthsRed = (int) ((timeLeftRed * 10) % 10);
                // Définition du temps de départ et du temps restant (10 secondes)
        }
        Runnable RendererLocal = new Runnable() {
                @Override
                public void run() {
                        if (Gdx.input.isTouched() && touchOff) {
                                touchOff = false;
                                for (HollowBar b : terrain.getBar()) {
                                        if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("V2/bluebar1.png",game.batch, end);

                                        } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("V2/redbar1.png", game.batch,end);

                                        }
                                }
                                for (int i = 0; i < terrain.getSquare().size; i++) {
                                        if (players.getPlayer()) {
                                                terrain.getSquare().get(i).clickBlock("bluecross1.png",game.batch, end);

                                        } else {
                                                terrain.getSquare().get(i).clickBlock("redcross1.png",game.batch, end);
                                        }
                                }
                        }

                        if (!Gdx.input.isTouched()) {
                                touchOff = true;
                        }
                }

        };
        Runnable RendererMulti = new Runnable() {
                @Override
                public void run() {
                        for (HollowBar b : terrain.getBar()) {
                                if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                        if(color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        b.clickBlock("V2/bluebar1.png.png", game.batch, end);
                                                }
                                        }
                                        b.clickBlock("V2/bluebar1.png.png",game.batch, end, touchPos);

                                } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                        if(!color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        b.clickBlock("red_bar_previous.png", game.batch, end);
                                                }
                                        }
                                        b.clickBlock("red_bar_previous.png",game.batch, end, touchPos);
                                }
                        }
                        for (int i = 0; i < terrain.getSquare().size; i++) {
                                if (players.getPlayer()) {
                                        if(color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        terrain.getSquare().get(i).clickBlock("blue_cross_previous.png", game.batch, end);
                                                }
                                        }
                                        terrain.getSquare().get(i).clickBlock("blue_cross_previous.png",game.batch, end, touchPos);

                                } else {
                                        if (!color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        terrain.getSquare().get(i).clickBlock("red_cross_previous.png", game.batch, end);
                                                }
                                        }
                                                terrain.getSquare().get(i).clickBlock("red_cross_previous.png", game.batch, end, touchPos);

                                }
                        }
                        if (!Gdx.input.isTouched()) {
                                touchOff = true;
                        }
                }
        };
        Runnable RendererIA = new Runnable() {
                @Override
                public void run() {
                        if(players.getPlayer()) {
                                if (Gdx.input.isTouched() && touchOff) {
                                        touchOff = false;
                                        for (HollowBar b : terrain.getBar()) {
                                                coupIA=2;
                                                b.clickBlock("V2/bluebar1.png.png", game.batch, end);
                                        }
                                        for (int i = 0; i < terrain.getSquare().size; i++) {
                                                coupIA=2;
                                                terrain.getSquare().get(i).clickBlock("V2/bluebar1.png.png", game.batch, end);
                                        }
                                }
                                if (!Gdx.input.isTouched()) {
                                        touchOff = true;
                                }
                        }
                        else {
                                for (HollowBar b : terrain.getBar()) {
                                        if(coupIA>0 && b.isFree) {
                                                b.iaPlaceBlock("red_bar_previous.png", game.batch, end);
                                                coupIA -=1;
                                        }
                                }
                                for (int i =0; i < terrain.getSquare().size; i++) {
                                        if(coupIA>0 && terrain.getSquare().get(i).isFree) {
                                                terrain.getSquare().get(i).iaPlaceBlock("red_cross_previous.png", game.batch, end);
                                                coupIA -=1;
                                        }
                                }

                        }

                }
        };
        public void resetCoupIA() {
                coupIA=2;
        }



        static public int getMode(){
                return mode;
        }
        public void setColor(boolean bool){ this.color = bool;}
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

                game.batch.dispose();
        }
}
