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

    public static void setCoup(PlayerManager players) {
        players.coup = !players.coup;           // Passe coup de True à False et inversement
        if(players.coup) {
            players.player=!players.player;     // Passe player à false et inversement
        }
    }
}
