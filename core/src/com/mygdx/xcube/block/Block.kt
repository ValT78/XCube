package com.mygdx.xcube.block

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.mygdx.xcube.GameScreen
import com.mygdx.xcube.PlayerManager

open class Block(var x: Float, var y: Float) {
    @JvmField
    var sprite: Sprite? = null
    var rectangle: Rectangle? = null
    var dx = 0f
    var dy = 0f
    var isFree = false
    fun clickBlock(texture: String?) {
        //rectangle.contains permet de savoir si le point que l'on indique appartient au rectangle
        //Gdx.input.get renvoie automatiquement la coordonnée X/Y sur laquelle on clique.
        val touchPos = Vector3() //Création d'un vecteur à 3 coordonnées x,y,z
        touchPos[Gdx.input.x.toFloat(), Gdx.input.y.toFloat()] =
            0f // On récupère les coordonnées de touché
        GameScreen.camera.unproject(touchPos) // On adapte les coordonnées à la camera
        if (rectangle!!.contains(
                touchPos.x,
                touchPos.y
            ) && isFree
        ) { // On test si l'endroit touché est un rectangle et s'il est libre
            isFree = false
            println(isFree)
            PlayerManager.setCoup(GameScreen.players)
            sprite = Sprite(Texture(Gdx.files.internal(texture)))
            drawBlock()
        }
    }

    fun drawBlock() {
        GameScreen.camera.update()
        GameScreen.spriteBatch.begin()
        GameScreen.spriteBatch.draw(sprite, x, y, 0f, 0f, dx, dy, 1f, 1f, 0f)
        GameScreen.spriteBatch.end()
    }
}