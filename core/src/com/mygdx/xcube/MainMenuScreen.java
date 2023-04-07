package com.mygdx.xcube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.xcube.block.Button;

public class MainMenuScreen implements Screen {
    final public XCube game;
    private Button local;
    private Button multiplayer;
    private Button IA;
    Viewport viewport = new ExtendViewport(800, 480);
    float inputTime = 0;
    OrthographicCamera camera;

    public MainMenuScreen(XCube game){
        this.game=game;
        //Valeurs modifiable selon le menu désiré
        local = new Button(400,300,"grey_bar.png","Local");
        multiplayer = new Button(400,200,"grey_bar.png","Multijoueur");
        IA = new Button(400,100,"grey_bar.png","Intelligence Artificiel");
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
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
        game.batch.end();       // Fin des éléments à afficher

        if (Gdx.input.isTouched() && inputTime>0.2f){
            Vector3 touchPos = new Vector3();                              //Création d'un vecteur à 3 coordonnées x,y,z
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);        // On récupère les coordonnées de touché
            camera.unproject(touchPos);                                    // On adapte les coordonnées à la camera

            if(this.multiplayer.contains(touchPos.x,touchPos.y)){

                game.setScreen(new Multiplayer(game));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
            else if(this.local.contains(touchPos.x,touchPos.y)){
                game.setScreen(new GameScreen(game,0));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
            else if(this.IA.contains(touchPos.x,touchPos.y)){
                game.setScreen(new GameScreen(game,2));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
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
