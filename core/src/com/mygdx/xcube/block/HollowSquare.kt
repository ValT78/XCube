package com.mygdx.xcube.block

import com.badlogic.gdx.math.Rectangle

class HollowSquare(x: Float, y: Float) : Block(x, y) {
    lateinit var neighbors: Array<HollowBar>
    var jsp = Testeur()

    init {
        dx = 400f
        dy = 400f
        rectangle = Rectangle(x, y, dx, dy)
        isFree = false
    }
//public HollowBar[] FindNeighbors(Array<HollowBar> bar, int i) {
    //    this.neighbors.add();
    //}
}