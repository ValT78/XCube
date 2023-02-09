package com.mygdx.xcube

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils

class MainMenuScreen(val game: XCube) : Screen {
    var camera: OrthographicCamera

    init {
        camera = OrthographicCamera()
        camera.setToOrtho(false, 400f, 800f)
    }

    override fun render(delta: Float) {            // Boucle infinie d'exécution
        ScreenUtils.clear(
            0f,
            0f,
            0.2f,
            1f
        ) // Supprime l'ancien background et en place un nouveau de la couleur rgb voulu
        camera.update()
        game.batch.projectionMatrix = camera.combined
        game.batch.begin() // Début des éléments à afficher
        game.font.draw(game.batch, "Welcome Pipopipette ! ", 100f, 250f)
        game.font.draw(game.batch, "Tap anywhere to begin!", 100f, 200f)
        game.batch.end() // Fin des éléments à afficher
        if (Gdx.input.isTouched) {
            game.screen = GameScreen(game) // Si l'écran est touché, l'écran passe à GameScreen
            dispose() // Supprime les élements définie dans dispose ( ici aucun)
        }
    }

    // Fonctions non utilisées recquises par l'implementation de screen
    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {}
    override fun show() {}
    fun rezise(x: Int, y: Int) {}
}