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
import com.mygdx.xcube.block.TerrainBlock;

import java.util.Random;


public class GameScreen implements Screen {
        final XCube game;
        private boolean color;
        public static OrthographicCamera camera;
        public Terrain terrain;
        public PlayerManager players;
        private boolean touchOff = true;
        private boolean touchOff2 = true;
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
        private final BitmapFont font;
        private final Items grid;
        private boolean gameStarted = false;
        private final Random random = new Random();
        private final ShapeRenderer shape;
        private boolean winner;
        private final Button turnBack;
        private final Button reDo;
        private final Button finishGame;
        private final Button pause;
        private Thread iaThread = new Thread(new IAClass(this));
        private boolean IAStart=false;
        private float startTime;

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
                pause = new Button((7*unitY + 7*unitX)*6/7,(7*unitY + 7*unitX)*13/7,"pause.png","",0.5f);
                this.startTime=startTime;
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
        @Override
        public void render(float delta){ //s'exécute une fois par frame
                ScreenUtils.clear(1,1,1,1);
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                if(mode==3) {
                        EndScreen();
                }
                else {
                        game.batch.begin();
                        pause.drawButton(game, 0);
                        game.batch.end();
                        if (Gdx.input.isTouched() && touchOff2) {
                                touchOff2 = false;
                                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                                camera.unproject(touchPos);
                                if(pause.contains(touchPos.x,touchPos.y)) {
                                        gameStarted=!gameStarted;
                                }
                        }
                        if (!Gdx.input.isTouched()) {
                                touchOff2 = true;
                        }
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
                        }
                        else { //gestion du chronometre rouge
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
                                        WaitToTouch();
                                        break;
                                case 1:
                                        if(color==players.getPlayer()) {
                                                WaitToTouch();
                                        }
                                        break;
                                case 2:
                                        if(players.getPlayer()) {
                                                iaThread.interrupt();
                                                WaitToTouch();
                                                IAStart=false;

                                        }
                                        else if(!players.getPlayer() && !IAStart){
                                                iaThread = new Thread(new IAClass(this));
                                                iaThread.start();
                                                IAStart=true;
                                        }
                                        break;
                        }
                }
                else  {
                        game.batch.begin();
                        finishGame.drawButton(game, 90);
                        game.batch.end();
                        if (finishGame.contains(touchPos.x, touchPos.y)) {
                                if(mode==1) {
                                        Multiplayer.disconnected();
                                }
                                game.setScreen(new MainMenuScreen(game, dlc, startTime));
                                dispose();
                        }
                }
                game.batch.begin(); //On affiche tous les éléments à l'écran, dans l'ordre : chronomètre, terrain puis grille
                font.setColor(Color.BLUE);
                font.draw(game.batch, String.format("%01d:%02d:%d",minutesBlue,secondsBlue,tenthsBlue), unitY, ((6*unitY + 5*unitX)*7/4));
                font.setColor(Color.RED);
                font.draw(game.batch, String.format("%01d:%02d.%d",minutesRed,secondsRed,tenthsRed), unitY, (6*unitY + 5*unitX)/4);
                for (int i=0; i<terrain.getBar().size;i++) {
                        terrain.getBar().get(i).drawBlock(game.batch);                         // Dessine le terrain
                }
                for (int i=0; i<terrain.getSquare().size;i++) {
                        terrain.getSquare().get(i).drawBlock(game.batch);                         // Dessine le terrain
                }
                for (int i=0; i<terrain.getBullet().size;i++) {
                        terrain.getBullet().get(i).drawItems(game, 1);
                }
                grid.drawItems(game,(float)(1));
                game.batch.end();

        }
        public void ProcessIA() {
                if (terrain.getCanPlay().size>40) {
                        int[] coups = terrain.Minimax(1);
                        TerrainBlock coup1=terrain.getCanPlay().get(coups[0]);
                        TerrainBlock coup2=terrain.getCanPlay().get(coups[1]);
                        coup1.changeBlock(GameScreen.this);
                        coup2.changeBlock(GameScreen.this);
                }
                else if(terrain.getCanPlay().size>0){
                        int[] coups = terrain.Minimax(3);
                        TerrainBlock coup1=terrain.getCanPlay().get(coups[0]);
                        TerrainBlock coup2=terrain.getCanPlay().get(coups[1]);
                        coup1.changeBlock(GameScreen.this);
                        coup2.changeBlock(GameScreen.this);
                }
                else {
                        int[] coups = terrain.Minimax(5);
                        TerrainBlock coup1=terrain.getCanPlay().get(coups[0]);
                        TerrainBlock coup2=terrain.getCanPlay().get(coups[1]);
                        coup1.changeBlock(GameScreen.this);
                        coup2.changeBlock(GameScreen.this);
                }


                /*boolean hasPlay = false;
                Array<HollowSquare> oversaturate = terrain.HaveNeighbors(0,1);
                Array<HollowSquare> insaturate = terrain.HaveNeighbors(3,4);
                if(oversaturate.size > 0) {
                        HollowSquare nearAlign = NearAlign(oversaturate);
                        Array<HollowSquare> four = terrain.HaveNeighbors(0,0);
                        if(nearAlign != null) {
                                OverSaturatePlay(nearAlign);
                                if(!players.getPlayer()) {
                                        nearAlign.changeBlock(GameScreen.this);
                                }
                                hasPlay=true;
                        }
                        else if(four.size > 0) {
                                four.get(random.nextInt(four.size)).changeBlock(GameScreen.this);
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
                        terrain.FindInsaturation(insaturate).changeBlock(GameScreen.this);
                        hasPlay=true;
                }
                if(!hasPlay) {
                        for (int i=0; i<terrain.getSquare().size; i++) {
                                HollowSquare square = terrain.getSquare().get(i);
                                if (square.isFree && !CouldAlign(square)) {
                                        int[] coord = square.getCoords();
                                        HollowSquare squareR = terrain.locateSquare(coord[0]+spaceBlock, coord[1]);
                                        HollowSquare squareL = terrain.locateSquare(coord[0]-spaceBlock, coord[1]);
                                        HollowSquare squareU = terrain.locateSquare(coord[0], coord[1]+spaceBlock);
                                        HollowSquare squareD = terrain.locateSquare(coord[0], coord[1]-spaceBlock);
                                        if(square.neighbors.get(2).isFree && (squareL==null || !CouldAlign(squareL))) {
                                                square.neighbors.get(2).changeBlock(GameScreen.this);
                                                hasPlay=true;
                                                break;
                                        }
                                        else if(square.neighbors.get(3).isFree && (squareR==null || !CouldAlign(squareR))) {
                                                square.neighbors.get(3).changeBlock(GameScreen.this);
                                                hasPlay=true;
                                                break;
                                        }
                                        else if(square.neighbors.get(0).isFree && (squareD==null || !CouldAlign(squareD))) {
                                                square.neighbors.get(0).changeBlock(GameScreen.this);
                                                hasPlay=true;
                                                break;
                                        }
                                        else if(square.neighbors.get(1).isFree && (squareU==null || !CouldAlign(squareU))) {
                                                square.neighbors.get(1).changeBlock(GameScreen.this);
                                                hasPlay=true;
                                                break;
                                        }
                                }
                        }
                        if(!hasPlay) {
                                OverSaturatePlay(oversaturate.get(random.nextInt(oversaturate.size)));
                        }
                }*/
        }
        public void EndScreen() {
                if (winner) {
                        shape.begin(ShapeRenderer.ShapeType.Filled);
                        shape.rect(0, 0, 7 * unitY + 7 * unitX, (7 * unitY + 7 * unitX) * 2, Color.CYAN, Color.SKY, new Color(0x029FFA), new Color(0x029FFA)); // gradient
                        shape.end();
                        game.batch.begin();     // Début des éléments à afficher
                        font.setColor(Color.BLUE);
                        font.draw(game.batch, "ViCTOiRE DU BLEU !", spaceBlock, (7 * unitY + 7 * unitX) * 15 / 8);
                        game.batch.end();
                }
                if (!winner) {
                        shape.begin(ShapeRenderer.ShapeType.Filled);
                        shape.rect(0, 0, 7 * unitY + 7 * unitX, (7 * unitY + 7 * unitX) * 2, Color.SALMON, Color.SALMON, Color.CORAL, Color.CORAL); // gradient
                        shape.end();
                        game.batch.begin();     // Début des éléments à afficher
                        font.setColor(Color.RED);
                        font.draw(game.batch, "ViCTOiRE DU ROUGE !", spaceBlock, (7 * unitY + 7 * unitX) * 15 / 8);
                        game.batch.end();
                }
                game.batch.begin();     // Début des éléments à afficher
                turnBack.drawButton(game, 0);
                reDo.drawButton(game, 0);
                finishGame.drawButton(game, 90);
                game.batch.end();
                if(WaitToTouch()) {
                        if (finishGame.contains(touchPos.x, touchPos.y)) {
                                game.setScreen(new MainMenuScreen(game, dlc, startTime));
                                dispose();
                        }
                        else if (turnBack.contains(touchPos.x, touchPos.y)) {
                                terrain.unPlay(GameScreen.this);
                        }
                        else if (reDo.contains(touchPos.x, touchPos.y)) {
                                terrain.rePlay(GameScreen.this);
                        }
                }
        };
        private boolean WaitToTouch() {
                if (Gdx.input.isTouched() && touchOff) {
                        touchOff = false;
                        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                        camera.unproject(touchPos);
                        if(mode==1) {
                                Multiplayer.send(touchPos);
                        }
                        UpdateBlock(touchPos);
                        return true;
                }
                if (!Gdx.input.isTouched()) {
                        touchOff = true;
                }
                return false;
        }
        private void UpdateBlock(Vector3 touchPos) {
                for (int i = 0; i < terrain.getBar().size; i++) { //Pareil pour les barres
                        if (terrain.getBar().get(i).isClickable(touchPos)) {
                                terrain.getBar().get(i).changeBlock(GameScreen.this);
                                return;
                        }
                }
                for (int i = 0; i < terrain.getSquare().size; i++) {  //On détecte si le joueur a appuyé sur un carré puis de quelle couleur est le joueur
                        if (terrain.getSquare().get(i).isClickable(touchPos)) {
                                terrain.getSquare().get(i).changeBlock(GameScreen.this);
                                return;
                        }
                }

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
                if(terrain.checkEveryAlign(players.getPlayer())) {
                        square.isFree=true;
                        return true;
                }
                square.isBlue=!players.getPlayer();
                if(terrain.checkEveryAlign(!players.getPlayer())) {
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
        private void OverSaturatePlay(HollowSquare square){
                for (HollowBar bar : square.neighbors) {
                        if (bar.isFree) {
                                bar.changeBlock(GameScreen.this);
                        }
                }
        }

        public void SendTouchPos(Vector3 touchPos){ //renvoie les coordonnées où le joueur a appuyé
                UpdateBlock(touchPos);
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

//                game.batch.dispose();
        }
}
