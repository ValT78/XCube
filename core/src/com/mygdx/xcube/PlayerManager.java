package com.mygdx.xcube;

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
                    if (player) {
                        if (lastPlay.isSquare) {
                            lastPlay.setSprite("V2/bluecross2.png");
                        }
                        else {
                            lastPlay.setSprite("V2/bluebar2.png");
                        }
                    }

                    else {
                        if (lastPlay.isSquare) {
                            lastPlay.setSprite("V2/redcross2.png");
                        }
                        else {
                            lastPlay.setSprite("V2/redbar2.png");
                        }
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
        if(terrain.getLastPlay().size>0)
        terrain.getLastPlay().removeIndex(terrain.getLastPlay().size-1);
    }
}
