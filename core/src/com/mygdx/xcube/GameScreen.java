package com.mygdx.xcube;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.xcube.block.Button;
import com.mygdx.xcube.block.HollowBar;
import com.mygdx.xcube.block.HollowSquare;
import com.mygdx.xcube.block.Items;

import java.util.Random;


public class GameScreen implements Screen {
        final XCube game;
        private boolean color;
        public static OrthographicCamera camera;
        public Terrain terrain;
        public PlayerManager players;
        private boolean touchOff = true;
        private float timeLeftBlue;
        private float timeLeftRed;
        private int minutesBlue;
        private int secondsBlue;
        private int tenthsBlue;
        private int minutesRed;
        private int secondsRed;
        private int tenthsRed;
        private int mode;
        private final boolean dlc;
        private Vector3 touchPos = new Vector3();
        private final int unitX = new HollowBar(false,0,0).getSize()[0];
        private final int unitY = new HollowBar(false,0,0).getSize()[1];
        private final int spaceBlock = unitX+unitY;
        //private Renderer RenderMode;
        private final BitmapFont font;
        private final Items grid;
        //private SpriteBatch batch;
        private boolean gameStarted = false;
        private final Random random = new Random();
        private ShapeRenderer shape;
        private boolean winner;
        private boolean hasTouch=false;
        private final Button turnBack;
        private final Button reDo;
        private final Button finishGame;


        public GameScreen(final XCube game, int mode, float startTime, boolean dlc) {
                grid = new Items(3*unitY/2-unitX,(9*unitY + 9*unitX)/2-unitX,"V2/dots.png");
                grid.resize(4*unitY+7*unitX,4*unitY+7*unitX);
                this.game = game;
                terrain = new Terrain();
                players = new PlayerManager();
                camera = new OrthographicCamera();
                this.timeLeftRed=startTime;
                this.timeLeftBlue=startTime;
                this.mode = mode;
                camera.setToOrtho(false, 7*unitY + 7*unitX, 2*(7*unitY + 7*unitX));
                game.batch = new SpriteBatch();
                FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                fontParameter.size = 150;
                font = fontGenerator.generateFont(fontParameter);
                this.dlc=dlc;
                shape= new ShapeRenderer();
                turnBack = new Button((7*unitY + 7*unitX)/3,(7*unitY + 7*unitX)/8,"turnback.png","",1);
                reDo = new Button((7*unitY + 7*unitX)*2/3,(7*unitY + 7*unitX)/8,"redo.png","",1);
                finishGame = new Button((7*unitY + 7*unitX)*1/5,(7*unitY + 7*unitX)*13/8,"V2/bluebar1.png","Retour au Menu",5);

        }
        @Override
        public void render(float delta){ //s'exécute une fois par frame
                ScreenUtils.clear(1,1,1,1);
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                if(mode==3) {
                        RendererEnd.run();
                }
                if(gameStarted) {
                        if (players.getPlayer()) { //gestion du chronometre bleu
                                timeLeftBlue -= delta;
                                minutesBlue = (int) (timeLeftBlue / 60);
                                secondsBlue = (int) ((timeLeftBlue) % 60);
                                tenthsBlue = (int) ((timeLeftBlue * 10) % 10);
                                if (timeLeftBlue < 0) { //vérifie si le temps bleu n'est pas écoulé
                                        setVictoryScreen(false);
                                }

                        } else { //gestion du chronometre rouge
                                timeLeftRed -= delta;
                                minutesRed = (int) (timeLeftRed / 60);
                                secondsRed = (int) ((timeLeftRed) % 60);
                                tenthsRed = (int) ((timeLeftRed * 10) % 10);
                                if (timeLeftRed < 0) { //vérifie que le temps rouge n'est pas écoulé
                                        setVictoryScreen(true);
                                }
                        }
                        switch (mode) { //On fait tourner une fonction différente selon le mode de jeu choisi sur le menu
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
                game.batch.begin(); //On affiche tous les éléments à l'écran, dans l'ordre : chronomètre, terrain puis grille
                font.setColor(Color.BLUE);
                font.draw(game.batch, String.format("%01d:%02d:%d",minutesBlue,secondsBlue,tenthsBlue), unitY, ((6*unitY + 5*unitX)*7/4));
                font.setColor(Color.RED);
                font.draw(game.batch, String.format("%01d:%02d.%d",minutesRed,secondsRed,tenthsRed), unitY, (6*unitY + 5*unitX)/4);
                for (HollowBar b : terrain.getBar()) {
                        b.drawBlock(game.batch);                         // Dessine le terrain
                }
                for (HollowSquare b : terrain.getSquare()) {
                        b.drawBlock(game.batch);                         // Dessine le terrain
                }
                grid.drawItems(game,(float)(1));
                game.batch.end();

        }
        public void setTouchPos(Vector3 touchPos){ //renvoie les coordonnées où le joueur a appuyé
                this.touchPos = touchPos;
        }

        public boolean checkEveryAlign(boolean player) { //vérifie si il y a un alignement avec les cases du joueur qui vient de jouer
                for (HollowSquare square: terrain.getSquare()) { //boucle for sur chaque carré
                        if(!square.isFree && square.isBlue==player) { //on ne choisit que ceux qui sont plein et de la couleur du joueur qui vient de jouer
                                if (square.isHorizontal) { //On vérifie si le bloc à droite et à gauche existent, puis s'ils sont de la même couleur que celui du milieu
                                        if (square.horizontal[0].isBlue==player && square.horizontal[1].isBlue==player && !square.horizontal[0].isFree && !square.horizontal[1].isFree) {
                                                return true;
                                        }
                                }
                                if (square.isVertical) {//pareil avec celui en bas et en haut
                                        if (square.vertical[0].isBlue==player && square.vertical[1].isBlue==player && !square.vertical[0].isFree && !square.vertical[1].isFree ) {
                                                return true;
                                        }
                                }
                                if (square.isDiagonal) {//pareil pour les 2 diagonales
                                        if (square.diagonal[0].isBlue==player && square.diagonal[1].isBlue==player && !square.diagonal[0].isFree && !square.diagonal[1].isFree ) {
                                                return true;
                                        }
                                }
                                if (square.isAntidiagonal) {
                                        if (square.antidiagonal[0].isBlue==player && square.antidiagonal[1].isBlue==player && !square.antidiagonal[0].isFree && !square.antidiagonal[1].isFree ) {
                                                return true;
                                        }
                                }
                        }
                }
                return false;
        }

        public HollowSquare NearAlign(Array<HollowSquare> overSaturate) {
                for (HollowSquare sat : overSaturate) {
                        if(CouldAlign(sat)) {
                                return sat;
                        }
                }
                return null;

        }

        private boolean CouldAlign(HollowSquare square) {
                square.isFree=false;
                square.isBlue=players.getPlayer();
                if(checkEveryAlign(players.getPlayer())) {
                        square.isFree=true;
                        return true;
                }
                square.isBlue=!players.getPlayer();
                if(checkEveryAlign(!players.getPlayer())) {
                        square.isFree=true;
                        return true;
                }
                square.isFree=true;
                return false;
        }

        public void setVictoryScreen(boolean winner){
                if(mode == 1) {
                        Multiplayer.disconnected();
                }
                gameStarted=false;
                this.winner=winner;
                mode=3;
        }
        @Override
        public void show() {
                minutesBlue = (int) (timeLeftBlue / 60);
                secondsBlue = (int) ((timeLeftBlue) % 60);
                tenthsBlue = (int) ((timeLeftBlue * 10) % 10);
                minutesRed = (int) (timeLeftRed / 60);
                secondsRed = (int) ((timeLeftRed) % 60);
                tenthsRed = (int) ((timeLeftRed * 10) % 10);
                // Définition du temps de départ et du temps restant
                chooseDLC();

        }
        void chooseDLC() {
                Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                                boolean alea = random.nextBoolean(); //Une chance sur 2 de créer un DLC
                                if (alea || !dlc) {                  //on démarre la partie
                                        terrain.setupAlign();
                                        gameStarted = true;
                                }
                                else {                               //On crée un DLC et on redonne une chance sur 2 de créer un deuxième DLC
                                        int place = random.nextInt(6);
                                        boolean side = random.nextBoolean();
                                        boolean turn = random.nextBoolean();
                                        terrain.createDLC(place, side, turn);
                                        chooseDLC();
                                }
                        }
                }, 2); // Attendre pendant 2 secondes
        }
        Runnable RendererLocal = new Runnable() {
                @Override
                public void run() {
                        if (Gdx.input.isTouched() && touchOff) { //On détecte une unique pression du joueur (pas de détection prolongée)
                                touchOff = false;
                                for (int i = 0; i < terrain.getSquare().size; i++) {  //On détecte si le joueur a appuyé sur un carré puis de quelle couleur est le joueur
                                        if (players.getPlayer()) {
                                                terrain.getSquare().get(i).clickBlock("V2/bluecross1.png", GameScreen.this);

                                        } else {
                                                terrain.getSquare().get(i).clickBlock("V2/redcross1.png", GameScreen.this);
                                        }
                                }
                                for (HollowBar b : terrain.getBar()) { //Pareil pour les barres
                                        if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("V2/bluebar1.png", GameScreen.this);

                                        } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("V2/redbar1.png", GameScreen.this);

                                        }
                                }

                        }

                        if (!Gdx.input.isTouched()) {
                                touchOff = true;
                        }
                }

        };
        Runnable RendererMulti = new Runnable() { //Fonction similaire à RenderLocal mais pour le multi
                @Override
                public void run() {
                        for (int i = 0; i < terrain.getSquare().size; i++) {
                                if (players.getPlayer()) {
                                        if(color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        terrain.getSquare().get(i).clickBlock("V2/bluecross1.png", GameScreen.this);
                                                }
                                        }
                                        terrain.getSquare().get(i).clickBlock("V2/bluecross1.png", GameScreen.this, touchPos);

                                } else {
                                        if (!color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        terrain.getSquare().get(i).clickBlock("V2/redcross1.png", GameScreen.this);
                                                }
                                        }
                                        terrain.getSquare().get(i).clickBlock("V2/redcross1.png", GameScreen.this, touchPos);

                                }
                        }
                        for (HollowBar b : terrain.getBar()) {
                                if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                        if(color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        b.clickBlock("V2/bluebar1.png", GameScreen.this);
                                                }
                                        }
                                        b.clickBlock("V2/bluebar1.png",GameScreen.this, touchPos);

                                } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                        if(!color) {
                                                if (Gdx.input.isTouched() && touchOff) {
                                                        b.clickBlock("V2/redbar1.png", GameScreen.this);
                                                }
                                        }
                                        b.clickBlock("V2/redbar1.png", GameScreen.this, touchPos);
                                }
                        }

                        if (!Gdx.input.isTouched()) {
                                touchOff = true;
                        }
                }
        };
        Runnable RendererIA = new Runnable() { //Similaire à RenderLocal mais pour l'IA
                @Override
                public void run() {
                        boolean hasPlay = false;
                        if(players.getPlayer()) {
                                if (Gdx.input.isTouched() && touchOff) {
                                        touchOff = false;
                                        for (int i = 0; i < terrain.getSquare().size; i++) {
                                                terrain.getSquare().get(i).clickBlock("V2/bluecross1.png", GameScreen.this);
                                        }
                                        for (HollowBar b : terrain.getBar()) {
                                                b.clickBlock("V2/bluebar1.png", GameScreen.this);
                                        }

                                }
                                if (!Gdx.input.isTouched()) {
                                        touchOff = true;
                                }
                        }
                        else {
                                Array<HollowSquare> oversaturate = terrain.HaveNeighbors(0,1);
                                Array<HollowSquare> insaturate = terrain.HaveNeighbors(3,4);
                                if(oversaturate.size > 0) {
                                        HollowSquare nearAlign = NearAlign(oversaturate);
                                        Array<HollowSquare> four = terrain.HaveNeighbors(0,0);
                                        if(nearAlign != null) {
                                                OverSaturatePlay(nearAlign);
                                                nearAlign.iaClickBlock("V2/redcross1.png", GameScreen.this);
                                                hasPlay=true;
                                        }
                                        else if(four.size > 0) {
                                                four.get(random.nextInt(four.size)).iaClickBlock("V2/redcross1.png", GameScreen.this);
                                                hasPlay=true;
                                        }
                                        else {
                                                for (HollowSquare square : oversaturate) {
                                                        int[] coord = square.getCoords();
                                                        HollowSquare squareR = terrain.locateSquare(coord[0] + spaceBlock, coord[1]);
                                                        HollowSquare squareL = terrain.locateSquare(coord[0] - spaceBlock, coord[1]);
                                                        HollowSquare squareU = terrain.locateSquare(coord[0], coord[1] + spaceBlock);
                                                        HollowSquare squareD = terrain.locateSquare(coord[0], coord[1] - spaceBlock);
                                                        if (square.neighbors.get(2).isFree && (squareL == null || !CouldAlign(squareL))) {
                                                                OverSaturatePlay(square);
                                                                hasPlay=true;
                                                                break;
                                                        } else if (square.neighbors.get(3).isFree && (squareR == null || !CouldAlign(squareR))) {
                                                                OverSaturatePlay(square);
                                                                hasPlay=true;
                                                                break;
                                                        } else if (square.neighbors.get(0).isFree && (squareD == null || !CouldAlign(squareD))) {
                                                                OverSaturatePlay(square);
                                                                hasPlay=true;
                                                                break;
                                                        } else if (square.neighbors.get(1).isFree && (squareU == null || !CouldAlign(squareU))) {
                                                                OverSaturatePlay(square);
                                                                hasPlay=true;
                                                                break;
                                                        }
                                                }
                                        }
                                }
                                else if (insaturate.size>0 && terrain.FindInsaturation(insaturate) != null) {
                                        terrain.FindInsaturation(insaturate).iaClickBlock("V2/redbar1.png", GameScreen.this);
                                        hasPlay=true;
                                }
                                if(!hasPlay) {
                                        for (HollowSquare square : terrain.getSquare()) {
                                                if (square.isFree && !CouldAlign(square)) {
                                                        int[] coord = square.getCoords();
                                                        HollowSquare squareR = terrain.locateSquare(coord[0]+spaceBlock, coord[1]);
                                                        HollowSquare squareL = terrain.locateSquare(coord[0]-spaceBlock, coord[1]);
                                                        HollowSquare squareU = terrain.locateSquare(coord[0], coord[1]+spaceBlock);
                                                        HollowSquare squareD = terrain.locateSquare(coord[0], coord[1]-spaceBlock);
                                                        if(square.neighbors.get(2).isFree && (squareL==null || !CouldAlign(squareL))) {
                                                                square.neighbors.get(2).iaClickBlock("V2/redbar1.png", GameScreen.this);
                                                                hasPlay=true;
                                                                break;
                                                        }
                                                        else if(square.neighbors.get(3).isFree && (squareR==null || !CouldAlign(squareR))) {
                                                                square.neighbors.get(3).iaClickBlock("V2/redbar1.png", GameScreen.this);
                                                                hasPlay=true;
                                                                break;
                                                        }
                                                        else if(square.neighbors.get(0).isFree && (squareD==null || !CouldAlign(squareD))) {
                                                                square.neighbors.get(0).iaClickBlock("V2/redbar1.png", GameScreen.this);
                                                                hasPlay=true;
                                                                break;
                                                        }
                                                        else if(square.neighbors.get(1).isFree && (squareU==null || !CouldAlign(squareU))) {
                                                                square.neighbors.get(1).iaClickBlock("V2/redbar1.png", GameScreen.this);
                                                                hasPlay=true;
                                                                break;
                                                        }

                                                }
                                        }
                                        if(!hasPlay) {
                                                OverSaturatePlay(oversaturate.get(random.nextInt(oversaturate.size)));
                                        }
                                }


                        }

                }
        };
        Runnable RendererEnd = new Runnable() {
                @Override
                public void run() {
                        if (winner) {
                                shape.begin(ShapeRenderer.ShapeType.Filled);
                                shape.rect(0, 0, 7 * unitY + 7 * unitX, (7 * unitY + 7 * unitX) * 2, Color.CYAN, Color.SKY, new Color(0x029FFA), new Color(0x029FFA)); // gradient
                                shape.end();
                                game.batch.begin();     // Début des éléments à afficher
                                font.setColor(Color.BLUE);
                                font.draw(game.batch, "ViCTOiRE DU BLEU !", spaceBlock, (7 * unitY + 7 * unitX) * 15 / 8);
                                game.batch.end();       // Fin des éléments à afficher
                        }
                        if (!winner) {
                                shape.begin(ShapeRenderer.ShapeType.Filled);
                                shape.rect(0, 0, 7 * unitY + 7 * unitX, (7 * unitY + 7 * unitX) * 2, Color.SALMON, Color.SALMON, Color.CORAL, Color.CORAL); // gradient
                                shape.end();
                                game.batch.begin();     // Début des éléments à afficher
                                font.setColor(Color.RED);
                                font.draw(game.batch, "ViCTOiRE DU ROUGE !", spaceBlock, (7 * unitY + 7 * unitX) * 15 / 8);
                                game.batch.end();       // Fin des éléments à afficher

                        }
                        game.batch.begin();     // Début des éléments à afficher
                        turnBack.drawButton(game, 0);
                        reDo.drawButton(game, 0);
                        finishGame.drawButton(game, 90);
                        game.batch.end();
                        if (!Gdx.input.isTouched()) {
                                touchOff=true;
                                hasTouch = true;
                        }
                        if (hasTouch && touchOff && Gdx.input.isTouched()) {
                                touchOff = false;
                                for (int i = 0; i < terrain.getSquare().size; i++) {  //On détecte si le joueur a appuyé sur un carré puis de quelle couleur est le joueur
                                        if (players.getPlayer()) {
                                                terrain.getSquare().get(i).clickBlock("V2/bluecross1.png", GameScreen.this);

                                        }
                                        else {
                                                terrain.getSquare().get(i).clickBlock("V2/redcross1.png", GameScreen.this);
                                        }
                                }
                                for (HollowBar b : terrain.getBar()) { //Pareil pour les barres
                                        if (players.getPlayer()) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("V2/bluebar1.png", GameScreen.this);

                                        } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                                                b.clickBlock("V2/redbar1.png", GameScreen.this);

                                        }
                                }
                                Vector3 touchPos = new Vector3();                              //Création d'un vecteur à 3 coordonnées x,y,z
                                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);        // On récupère les coordonnées de touché
                                camera.unproject(touchPos);                                    // On adapte les coordonnées à la camera
                                if (finishGame.contains(touchPos.x, touchPos.y)) {
                                        game.dispose();
                                        game.create();
                                }
                                else if (turnBack.contains(touchPos.x, touchPos.y)) {
                                        terrain.unPlay(GameScreen.this);
                                }
                                else if (reDo.contains(touchPos.x, touchPos.y)) {
                                        terrain.rePlay(GameScreen.this);
                                }
                        }
                }
        };

        private void OverSaturatePlay(HollowSquare square){
                for (HollowBar bar : square.neighbors) {
                        if (bar.isFree) {
                                bar.iaClickBlock("V2/redbar1.png", GameScreen.this);
                        }
                }
        }


        public int getMode(){
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
