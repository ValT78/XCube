package com.mygdx.xcube;

import static com.badlogic.gdx.math.MathUtils.round;
import static com.mygdx.xcube.GameScreen.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.xcube.block.Block;
import com.mygdx.xcube.block.HollowBar;
import com.sun.org.apache.xpath.internal.operations.Mult;

public class MainMenuScreen implements Screen {
    final XCube game;
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("grey_bar.png")));
    private Rectangle local;
    private int lx;
    private int ly;
    private int ldx;
    private int ldy;
    private int mx;
    private int my;
    private int mdx;
    private int mdy;
    private Rectangle multiplayer;
    Viewport viewport = new ExtendViewport(800, 480);
    float inputTime = 0;
    OrthographicCamera camera;

    public MainMenuScreen(final XCube game){
    this.game = game;

    //Valeurs modifiable selon le menu désiré
    this.ly = 200;
    this.ldx = 200;
    this.ldy = 40;
    this.lx = 400-(ldx/2);
    this.my = 300;
    this.mdx = 200;
    this.mx = 400-(mdx/2);
    this.mdy = 40;
    local = new Rectangle(lx,ly,ldx,ldy);
    multiplayer = new Rectangle(mx,my,mdx,mdy);
    camera = new OrthographicCamera();
        camera.setToOrtho(false,800,822);
}

    public void render(float delta){            // Boucle infinie d'exécution
        inputTime += delta;
        ScreenUtils.clear(1,1,1,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();     // Début des éléments à afficher
        game.batch.draw(sprite,lx,ly,0,0,ldx,ldy,1,1,0);
        game.batch.draw(sprite,mx,my,0,0,mdx,mdy,1,1,0);
        game.font.draw(game.batch, "Local",lx+(ldx/4),ly+(ldy/2));
        game.font.draw(game.batch, "Multiplayer", mx+(mdx/4), my+(mdy/2));
        game.batch.end();       // Fin des éléments à afficher

        if (Gdx.input.isTouched() && inputTime>0.2f){
            Vector3 touchPos = new Vector3();                              //Création d'un vecteur à 3 coordonnées x,y,z
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);        // On récupère les coordonnées de touché
            camera.unproject(touchPos);                                    // On adapte les coordonnées à la camera

            if(this.multiplayer.contains(touchPos.x,touchPos.y)){
                game.setScreen(new Multiplayer(game));   // Si l'écran est touché, l'écran passe à GameScreen
                dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
            }
            if(this.local.contains(touchPos.x,touchPos.y)){
                game.setScreen(new GameScreen(game,false));   // Si l'écran est touché, l'écran passe à GameScreen
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
