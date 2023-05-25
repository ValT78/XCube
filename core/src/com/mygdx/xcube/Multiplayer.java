package com.mygdx.xcube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.json.JSONException;
import org.json.JSONObject;
import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class Multiplayer implements Screen {
    private AsyncExecutor asyncExecutor;
    private boolean color;
    private static Socket socket;
    final XCube game;
    private final GameScreen gamescreen;
    private boolean duel = false;
    Viewport viewport = new ExtendViewport(800, 480);
    Stage stage = new Stage(viewport);
    OrthographicCamera camera;

    public Multiplayer(XCube game, float startTime, boolean dlc){
        asyncExecutor = new AsyncExecutor(1);

        asyncExecutor.submit(new AsyncTask<Void>() {
            @Override
            public Void call() {
                connectSocket();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // Faire quelque chose avec la connexion socket.io
                        configSocketEvents();
                    }
                });
                return null;
            }
        });
        this.game = game;
        this.gamescreen = new GameScreen(game,1, startTime, dlc);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,400,822);
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
                JSONObject bool = (JSONObject) args[1];
                try {
                    String id = data.getString("id");
                    color = bool.getBoolean("bool");
                    gamescreen.setColor(color);
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch(JSONException e){
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("playerPlayed", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerID = data.getString("id");
                    double x = data.getDouble("x");
                    double y = data.getDouble("y");
                    Vector3 touchPos = new Vector3();
                    touchPos.x = (float) x;
                    touchPos.y = (float) y;
                    gamescreen.SendTouchPos(touchPos);
                } catch(JSONException e){

                }
            }
        }).on("twoPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                duel=true;
            }
        });
    }
    public void connectSocket(){
        try{
            socket= IO.socket("http://157.159.195.91:8080");
            socket.connect();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void send(Vector3 vector){
        JSONObject data = new JSONObject();
        try{
            data.put("x", vector.x);
            data.put("y", vector.y);
            socket.emit("coord", data);
        } catch (JSONException e){
            Gdx.app.log("SOCKET.IO", "Error sending update data");
        }
    }

    public static void disconnected(){
        socket.disconnect();
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
        game.font.draw(game.batch, "Waiting for another player ",100,250);
        game.batch.end();       // Fin des éléments à afficher

        if(duel){
            game.setScreen(gamescreen);
            dispose();
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
