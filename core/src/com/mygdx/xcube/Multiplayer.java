package com.mygdx.xcube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class Multiplayer implements Screen {
    private Socket socket;
    final XCube game;
    Viewport viewport = new ExtendViewport(800, 480);
    Stage stage = new Stage(viewport);
    OrthographicCamera camera;

    public Multiplayer(XCube game){
        connectSocket();
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,400,822);
        configSocketEvents();
    }

    public void configSocketEvents(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO","Connected");
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch(JSONException e){
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connect: " + id);
                } catch(JSONException e){
                    Gdx.app.log("SocketIO", "Error getting New Player ID ID");
                }
            }
        });
    }
    public void connectSocket(){
        try{
            socket= IO.socket("http://localhost:8080");
            socket.connect();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
}
