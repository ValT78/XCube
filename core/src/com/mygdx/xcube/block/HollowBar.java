package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.math.Rectangle;

public class HollowBar extends Block {

    public HollowBar(boolean rotation, int x, int y){
        super(x,y);
        this.setSprite("grey_bar.png");
        dx=round(this.getSprite().getWidth());
        dy=round(this.getSprite().getHeight());
        rectangle = new Rectangle(x, y, dx, dy);

        //La rotation semble assez inutile, il faudra la virer plus tard. Quand tu dessines un
        //sprite avec une certaine rotation (méthode batch.draw()), ça ne modifie pas la hitbox
        //du rectangle. Du coup, on a le sprite qui est tourné de 90° par rapport à la hitbox
        //qui n'a pas bougé. Et je n'ai pas trouvé de paramètre "rotation" pour le rectangle.
        //A la place j'invers la largeur et la longueur du rectangle en fonction de la rotation
        //que l'on met en argument
        if (rotation) {
            rectangle.setSize(dy, dx);
            dx = dy;
            dy = round(this.getSprite().getWidth());
        }
        isSquare=false;
    }

}
