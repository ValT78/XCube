package com.mygdx.xcube;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.xcube.block.Button;
import com.mygdx.xcube.block.Items;

public class MainMenuScreen implements Screen {
    final public XCube game;
    private final Button local;
    private final Button multiplayer;
    private final Items logo;
    private final Button IA;
    private final Button DLC;
    private final Button Chrono;
    private float startTime = 150;
    private boolean dlc = false;
    Viewport viewport = new ExtendViewport(800, 480);
    float inputTime = 0;
    private final int width_screen = 540;
    private final int height_screen = 1200;
    OrthographicCamera camera;
    private boolean touchOff;

    public MainMenuScreen(XCube game){
        this.game=game;

        //Valeurs modifiable selon le menu désiré
        local = new Button(400,300,"V2/bluebar1.png","Local");
        multiplayer = new Button(400,200,"V2/bluebar1.png","Multijoueur");
        IA = new Button(400,100,"V2/bluebar1.png","Intelligence Artificielle");
        DLC = new Button(400,400,"V2/redbar1.png","DLC Désactivés");
        Chrono = new Button(400,500,"V2/bluebar1.png","Temps : Medium (150 sec)");
        logo = new Items(width_screen/4,3*height_screen/4,"V2/title.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false,width_screen,height_screen);
    }

    public void render(float delta){            // Boucle infinie d'exécution
        inputTime += delta;
        ScreenUtils.clear(1,1,1,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();     // Début des éléments à afficher
        local.drawButton(game);
        multiplayer.drawButton(game);
        IA.drawButton(game);
        DLC.drawButton(game);
        Chrono.drawButton(game);
        logo.drawItems(game,(float)(0.5));
        game.batch.end();       // Fin des éléments à afficher

        if (Gdx.input.isTouched() && touchOff){
            touchOff=false;
            Vector3 touchPos = new Vector3();                              //Création d'un vecteur à 3 coordonnées x,y,z
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);        // On récupère les coordonnées de touché
            camera.unproject(touchPos);                                    // On adapte les coordonnées à la camera

            if(this.multiplayer.contains(touchPos.x,touchPos.y)){
                game.setScreen(new Multiplayer(game, startTime, dlc));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
            else if(this.local.contains(touchPos.x,touchPos.y)){
                game.setScreen(new GameScreen(game,0, startTime, dlc));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
            else if(this.IA.contains(touchPos.x,touchPos.y)){
                game.setScreen(new GameScreen(game,2, startTime, dlc));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
            else if(this.DLC.contains(touchPos.x,touchPos.y)){
                dlc=!dlc;
                if(dlc) {
                    DLC.setSprite("V2/bluebar1.png");
                    DLC.setText("DLC Activés");
                }
                else {
                    DLC.setSprite("V2/redbar1.png");
                    DLC.setText("DLC Désactivés");

                }
            }
            else if(this.Chrono.contains(touchPos.x,touchPos.y)){
                if(startTime==90) {
                    startTime=150;
                    Chrono.setSprite("V2/bluebar1.png");
                    Chrono.setText("Temps : Medium (150 sec)");
                }
                else if(startTime==150){
                    startTime=300;
                    Chrono.setSprite("grey_bar2.png");
                    Chrono.setText("Temps : Découverte (300 sec)");
                }
                else if(startTime==300){
                    startTime=90;
                    Chrono.setSprite("V2/redbar1.png");
                    Chrono.setText("Temps : Pro (90 sec)");
                }
            }
        }
        if (!Gdx.input.isTouched()) {
            touchOff = true;
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
}
