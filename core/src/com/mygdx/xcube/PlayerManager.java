package com.mygdx.xcube;

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
        }

        if(coup && terrain.getLastPlay().size >= 3) {
            for (int i = 0; i < 2; i++) {
                if (player) {
                    if (terrain.getLastPlay().get(0).isSquare) {
                        terrain.getLastPlay().get(0).setSprite("V2/bluecross2.png");
                    }
                    else {
                        terrain.getLastPlay().get(0).setSprite("V2/bluebar2.png");
                    }
                }

                else {
                    if (terrain.getLastPlay().get(0).isSquare) {
                        terrain.getLastPlay().get(0).setSprite("V2/redcross2.png");
                    }
                    else {
                        terrain.getLastPlay().get(0).setSprite("V2/redbar2.png");
                    }
                }
                terrain.getLastPlay().removeIndex(0);
            }



        }
    }
}
