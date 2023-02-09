package com.mygdx.xcube

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class XCube : Game() {
    var batch: SpriteBatch? = null
    var font: BitmapFont? = null
    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        setScreen(MainMenuScreen(this)) // Lance l'écran de départ.
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        batch!!.dispose()
        font!!.dispose()
    }
}