package com.mygdx.xcube

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class GameScreen(val game: XCube) : Screen {
    private val terrain = Terrain()

    init {
        camera = OrthographicCamera()
        camera.setToOrtho(false, 3000f, 3000f)
    }

    //render s'éxecute toutes les frames
    override fun render(delta: Float) {
        ScreenUtils.clear(1f, 1f, 1f, 1f)
        camera.update()
        spriteBatch.projectionMatrix = camera.combined
        for (b in terrain.bar) {
            if (b.sprite == null) {                  // Initialise les sprites des blocks
                b.sprite = Sprite(Texture(Gdx.files.internal("grey_bar.png")))
            }
            b.drawBlock() // Dessine le terrain
        }
        for (b in terrain.square) {
            if (b.sprite == null) {                  // Initialise les sprites des blocks
                b.sprite = Sprite(Texture(Gdx.files.internal("grey_square.png")))
            }
            b.drawBlock() // Dessine le terrain
        }
        if (Gdx.input.isTouched) {
            for (b in terrain.bar) {
                if (players.player) {     // Si le joueur bleue(valeur true) toûche, on cherche où et on adapte le sprite
                    b.clickBlock("blue_bar.png")
                } else {                       // Si le joueur rouge(valeur false) toûche, on cherche où et on adapte le sprite
                    b.clickBlock("red_bar.png")
                }
            }
            for (b in terrain.square) {
                if (players.player) {
                    b.clickBlock("blue_square.png")
                } else {
                    b.clickBlock("red_square.png")
                }
            }
        }
    }

    // Fonctions inutilisées
    override fun resize(width: Int, height: Int) {}
    override fun show() {}
    override fun hide() {}
    override fun pause() {}
    override fun resume() {}
    override fun dispose() {}

    companion object {
        lateinit var camera: OrthographicCamera
        var spriteBatch = SpriteBatch()
        var players = PlayerManager()
    }
}