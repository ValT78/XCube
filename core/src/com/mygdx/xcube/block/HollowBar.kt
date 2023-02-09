package com.mygdx.xcube.block

import com.badlogic.gdx.math.Rectangle

class HollowBar(rotation: Float, x: Float, y: Float) : Block(x, y) {
    var rotation: Float

    init {
        dx = 500f
        dy = 96f
        rectangle = Rectangle(x, y, dx, dy)
        //La rotation semble assez inutile, il faudra la virer plus tard. Quand tu dessines un
        //sprite avec une certaine rotation (méthode batch.draw()), ça ne modifie pas la hitbox
        //du rectangle. Du coup, on a le sprite qui est tourné de 90° par rapport à la hitbox
        //qui n'a pas bougé. Et je n'ai pas trouvé de paramètre "rotation" pour le rectangle.
        //A la place j'invers la largeur et la longueur du rectangle en fonction de la rotation
        //que l'on met en argument
        this.rotation = rotation
        if (rotation == 90f) {
            rectangle!!.setSize(96f, 500f)
            dx = 96f
            dy = 500f
        }
        isFree = true
    } //public HollowBar[] AddNeighbor(HollowBar[] bars) {
    //    return bars.add(this);
    //}
}