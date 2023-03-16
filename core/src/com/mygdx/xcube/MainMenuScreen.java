package com.mygdx.xcube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    final XCube game;
    Viewport viewport = new ExtendViewport(800, 480);
    Stage stage = new Stage(viewport);
    OrthographicCamera camera;

    public MainMenuScreen(final XCube game){
    this.game = game;

    camera = new OrthographicCamera();
        camera.setToOrtho(false,400,822);
}

    public void render(float delta){            // Boucle infinie d'exécution
        ScreenUtils.clear(0,0,0.2f,1);  // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();     // Début des éléments à afficher
        game.font.draw(game.batch, "Welcome Pipopipette ! ",100,250);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 200);
        game.batch.end();       // Fin des éléments à afficher

        if (Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));   // Si l'écran est touché, l'écran passe à GameScreen
            dispose();                              // Supprime les élements définie dans dispose ( ici aucun)
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
