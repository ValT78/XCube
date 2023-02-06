package com.mygdx.pipopipette;

public class PlayerManager {

    private boolean coup = true;
    private boolean player = true;

    public boolean getCoup() {
        return coup;
    }

    public boolean getPlayer() {
        return player;
    }

    public static void setCoup(PlayerManager players) {
        players.coup = !players.coup;
        if(players.coup) {
            players.player=!players.player;
        }
    }
}
