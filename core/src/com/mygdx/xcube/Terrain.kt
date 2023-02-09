package com.mygdx.xcube

import com.badlogic.gdx.utils.Array
import com.mygdx.xcube.block.HollowBar
import com.mygdx.xcube.block.HollowSquare

class Terrain {
    //Permet d'appeler les barres
    val bar: Array<HollowBar>

    // Permet d'appeler les carrés
    val square: Array<HollowSquare>

    init {
        bar = generateBar() as Array<HollowBar> // Stock la liste des barres
        square = generateSquare() // Stock la liste de carrés
    }

    fun generateBar(): Array<*> {
        val bar = Array<HollowBar>()
        var x = 300f
        var y = -400f
        for (i in 0..4) {
            y = y + 600
            x = 300f
            for (k in 0..3) {
                val b = HollowBar(0f, x, y)
                x = x + 600
                bar.add(b)
            }
        }
        x = -400f
        y = 300f
        for (i in 0..4) {
            x = x + 600
            y = 300f
            for (k in 0..3) {
                bar.add(HollowBar(90f, x, y))
                y = y + 600
            }
        }
        return bar
    }

    fun generateSquare(): Array<HollowSquare> {
        val block = Array<HollowSquare>()
        var x = -250f
        var y: Float
        for (i in 0..3) {
            x = x + 600
            y = 350f
            for (k in 0..3) {
                block.add(HollowSquare(x, y))
                y = y + 600
            }
        }
        return block
    }
}