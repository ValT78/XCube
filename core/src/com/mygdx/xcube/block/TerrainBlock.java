package com.mygdx.xcube.block;

import static com.mygdx.xcube.GameScreen.camera;
import static com.mygdx.xcube.GameScreen.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.xcube.End;
import com.mygdx.xcube.GameScreen;
import com.mygdx.xcube.Multiplayer;
import com.mygdx.xcube.PlayerManager;

import java.util.ArrayList;

public class TerrainBlock extends Block {
    public boolean isSquare;
    public boolean isFree;
    public boolean isBlue;
    public ArrayList<HollowBar> neighbors;

    public TerrainBlock(int x, int y) {
        this.x = x;
        this.y = y;
        isFree=true;
        this.neighbors = new ArrayList<>();

    }
    public void setSprite(String texture) {
        this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
    }
    public Sprite getSprite() {
        return this.sprite;
    }

    public int[] getSize() {
        int[] tab = {dx,dy};

        return tab;
    }

    public void clickBlock(String texture, End end) {
        //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
        //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.

        Vector3 touchPos = new Vector3();                              //Création d'un vecteur à 3 coordonnées x,y,z
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);        // On récupère les coordonnées de touché
        camera.unproject(touchPos);                                    // On adapte les coordonnées à la camera
        if(GameScreen.getMode() == 1){
           Multiplayer.send(touchPos);
        }
        if(this.rectangle.contains(touchPos.x, touchPos.y) && this.isClickable()){ // On test si l'endroit touché est un rectangle et s'il est libre
            isFree=false;
            isBlue=players.getPlayer();
            this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
            if(this.isSquare) { //vérifie si une condition de victoire est remplie
                end.checkAlign(GameScreen.players.getPlayer(), end);
            }

            GameScreen.terrain.getLastPlay().add(this);
            PlayerManager.setCoup(GameScreen.players);
            if(GameScreen.players.getCoup() && GameScreen.terrain.getLastPlay().size >= 3) {
                for (int i = 0; i < 2; i++) {
                    if (players.getPlayer()) {
                        if (GameScreen.terrain.getLastPlay().get(0).isSquare) {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("blue_cross.png");
                        }
                        else {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("blue_bar.png");
                        }
                    }

                    else {
                        if (GameScreen.terrain.getLastPlay().get(0).isSquare) {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("red_cross.png");
                        }
                        else {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("red_bar.png");
                        }
                    }
                GameScreen.terrain.getLastPlay().removeIndex(0);
                }



            }
        }

    }

    public void clickBlock(String texture, End end,Vector3 touchPos){
        //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
        //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.

        if(this.rectangle.contains(touchPos.x, touchPos.y) && this.isClickable()){ // On test si l'endroit touché est un rectangle et s'il est libre
            isFree=false;
            isBlue=players.getPlayer();
            this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
            if(this.isSquare) { //vérifie si une condition de victoire est remplie
                end.checkAlign(GameScreen.players.getPlayer(), end);
            }

            GameScreen.terrain.getLastPlay().add(this);
            PlayerManager.setCoup(GameScreen.players);
            if(GameScreen.players.getCoup() && GameScreen.terrain.getLastPlay().size >= 3) {
                for (int i = 0; i < 2; i++) {
                    if (players.getPlayer()) {
                        if (GameScreen.terrain.getLastPlay().get(0).isSquare) {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("blue_cross.png");
                        }
                        else {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("blue_bar.png");
                        }
                    }

                    else {
                        if (GameScreen.terrain.getLastPlay().get(0).isSquare) {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("red_cross.png");
                        }
                        else {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("red_bar.png");
                        }
                    }
                    GameScreen.terrain.getLastPlay().removeIndex(0);
                }



            }
        }


    }

    public void iaPlaceBlock(String texture, End end){
        //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
        //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.
        isFree=false;
        isBlue=players.getPlayer();
        this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)));
        if(this.isSquare) { //vérifie si une condition de victoire est remplie
            end.checkAlign(GameScreen.players.getPlayer(), end);
        }
            GameScreen.terrain.getLastPlay().add(this);
            PlayerManager.setCoup(GameScreen.players);
            if(GameScreen.players.getCoup() && GameScreen.terrain.getLastPlay().size >= 3) {
                for (int i = 0; i < 2; i++) {
                    if (players.getPlayer()) {
                        if (GameScreen.terrain.getLastPlay().get(0).isSquare) {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("blue_cross.png");
                        }
                        else {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("blue_bar.png");
                        }
                    }

                    else {
                        if (GameScreen.terrain.getLastPlay().get(0).isSquare) {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("red_cross.png");
                        } else {
                            GameScreen.terrain.getLastPlay().get(0).setSprite("red_bar.png");
                        }
                    }
                    GameScreen.terrain.getLastPlay().removeIndex(0);
                }
            }
        }

    public void drawBlock(SpriteBatch batch) {
        batch.draw(this.sprite,x,y,0,0,dx,dy,1,1,0);
    }

    public boolean isClickable() {
        boolean clickable = this.isFree;
        if(this.isSquare) {
            for (HollowBar bar : this.neighbors
            ) {
                if(bar.isFree) {
                    clickable = false;
                }
            }
        }
        return clickable;
    }
    public int[] getCoords() {
        int[] tab = {x,y};

        return tab;
    } //retourne les coordonnées d'un carré


}
