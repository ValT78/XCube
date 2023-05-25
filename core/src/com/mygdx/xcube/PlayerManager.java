package com.mygdx.xcube;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.xcube.block.TerrainBlock;

public class PlayerManager {

    private boolean coup = true;                // Permet de gérer les coups restants
    private boolean player = true;              // Vaut True lorsque c'est à son tour de jouer

    public boolean getCoup() {
        return coup;
    }

    public boolean getPlayer() {
        return player;
    }

    public void setCoup(Terrain terrain) {
        coup = !coup;           // Passe coup de True à False et inversement
        if(coup) {
            player=!player;     // Passe player à false et inversement

            if(terrain.getLastPlay().size >= 3) {
                for (int i = 0; i < 2; i++) {
                    TerrainBlock lastPlay = terrain.getLastPlay().removeIndex(0);
                    if(lastPlay.isBlue) {
                        lastPlay.sprite=lastPlay.spritebleu2;
                    }
                    else {
                        lastPlay.sprite=lastPlay.spriterouge2;
                    }
                }
            }
        }


    }
    public void backCoup(Terrain terrain) {
        coup = !coup;           // Passe coup de True à False et inversement
        if(!coup) {
            player=!player;     // Passe player à false et inversement
        }
        if(terrain.getLastPlay().size>0) {
            terrain.getLastPlay().removeIndex(terrain.getLastPlay().size - 1);
        }
    }
}
