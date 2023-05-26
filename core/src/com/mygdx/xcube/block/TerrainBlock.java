package com.mygdx.xcube.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.xcube.GameScreen;

public class TerrainBlock extends Block {
    public boolean isSquare;
    public boolean isFree;
    public boolean isBlue;
    public Sprite spritebleu;
    public Sprite spritebleu2;
    public Sprite spriterouge;
    public Sprite spriterouge2;

    public TerrainBlock(int x, int y) {
        this.x = x;
        this.y = y;
        isFree=true;

    }
    public Sprite setSprite(String texture) {
        return new Sprite(new Texture(Gdx.files.internal(texture)));
    }
    public Sprite getSprite() {
        return this.sprite;
    }

    public int[] getSize() {

        return new int[]{dx,dy};
    }
    public void changeBlock(GameScreen gameScreen) {
        isFree=false;                                                          //Le rectangle ne peut plus être cliqué
        isBlue=gameScreen.players.getPlayer();//Il prend la couleur du joueur dont c'est le tour
        if(isBlue) {
            sprite = spritebleu;
        }
        else {
            sprite = spriterouge;
        }
        if(this.isSquare && gameScreen.getMode()!=3 && gameScreen.terrain.checkEveryAlign(gameScreen.players.getPlayer())) {                                                   //vérifie si une condition de victoire est remplie
            gameScreen.setVictoryScreen(gameScreen.players.getPlayer());
        }
        for (int i=0;i<gameScreen.terrain.getCanPlay().size;i++) {
            if(gameScreen.terrain.getCanPlay().get(i)==this) {
                gameScreen.terrain.getCanPlay().removeIndex(i);
            }
        }
        gameScreen.terrain.getLastPlay().add(this);                           //Est enregistré comme le dernier coup joué pour apparaitre d'une couleur différente de la normal
        gameScreen.terrain.addPlay(this);
        gameScreen.players.setCoup(gameScreen.terrain);                       //Vérifie combien de coup il reste au joueur actuel pour jouer, et change la couleur des blocks récemment joués
    }


    public void drawBlock(SpriteBatch batch) {
        batch.draw(this.sprite,x,y,0,0,dx,dy,1,1,0);
    }

    public boolean isClickable(Vector3 touchPos) { //Vérifie si le rectangle cliqué est cliquable
        if(!isFree) {
            return false;
        }
        if(rectangle.contains(touchPos.x, touchPos.y)) {
            if (this.isSquare) {
                HollowSquare square = (HollowSquare) this;
                return square.FillNeighbors();
            }
            return true;
        }
        return false;
    }
    public int[] getCoords() {

        return new int[]{x,y};
    } //retourne les coordonnées d'un carré
    public void unPlay(GameScreen gameScreen) {
        isFree=true;                                                          //Le rectangle ne peut plus être cliqué
        if(isSquare) {
            sprite = setSprite("square_holder.png");
        }
        else {
            sprite = setSprite("greybarv2.png");
        }
        gameScreen.players.backCoup(gameScreen.terrain);                       //Vérifie combien de coup il reste au joueur actuel pour jouer, et change la couleur des blocks récemment joués

    }
    public void rePlay(GameScreen gameScreen) {
        isFree=false;                                                          //Le rectangle ne peut plus être cliqué
        isBlue=gameScreen.players.getPlayer();                                 //Il prend la couleur du joueur dont c'est le tour
        if(isBlue) {
            sprite = spritebleu;
        }
        else {
            sprite = spriterouge;
        }
        gameScreen.terrain.getLastPlay().add(this);                           //Est enregistré comme le dernier coup joué pour apparaitre d'une couleur différente de la normal
        gameScreen.players.setCoup(gameScreen.terrain);                       //Vérifie combien de coup il reste au joueur actuel pour jouer, et change la couleur des blocks récemment joués

    }



}
