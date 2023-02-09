package com.mygdx.xcube

class PlayerManager {
    var coup = true // Permet de gérer les coups restants
        private set
    var player = true // Vaut True lorsque c'est à son tour de jouer
        private set

    companion object {
        fun setCoup(players: PlayerManager) {
            players.coup = !players.coup // Passe coup de True à False et inversement
            if (players.coup) {
                players.player = !players.player // Passe player à false et inversement
            }
        }
    }
}