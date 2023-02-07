package com.mygdx.pipopipette.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HollowBar extends Block {
    public float rotation;

    public HollowBar(float rotation, float x, float y){
        super(x,y);
        dx=500;
        dy=96;
        rectangle = new Rectangle(x, y, dx, dy);
        //La rotation semble assez inutile, il faudra la virer plus tard. Quand tu dessines un
        //sprite avec une certaine rotation (méthode batch.draw()), ça ne modifie pas la hitbox
        //du rectangle. Du coup, on a le sprite qui est tourné de 90° par rapport à la hitbox
        //qui n'a pas bougé. Et je n'ai pas trouvé de paramètre "rotation" pour le rectangle.
        //A la place j'invers la largeur et la longueur du rectangle en fonction de la rotation
        //que l'on met en argument
        this.rotation = rotation;
        if (rotation==90) {
            rectangle.setSize(96,500);
            dx=96;
            dy=500;
        }
        isFree=true;


    }

    //public HollowBar[] AddNeighbor(HollowBar[] bars) {
        //return bars.add(this);
    //}





}
