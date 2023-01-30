package com.mygdx.pipopipette;



import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.pipopipette.block.HollowBar;
import com.mygdx.pipopipette.block.HollowBlock;

import org.graalvm.compiler.hotspot.HotSpotCounterOp;

public class GameScreen implements Screen {
        final Pipopipette game;
        OrthographicCamera camera;
        private SpriteBatch spriteBatch = new SpriteBatch();

        public GameScreen(final Pipopipette game) {
                this.game = game;
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 2900, 2900);
                System.out.println("gggg");
                ScreenUtils.clear(1,1,1,1);
                camera.update();
                spriteBatch.setProjectionMatrix(camera.combined);

                Array<HollowBar> bar = generateBar();
                Array<HollowBlock> block = generateBlock();

                spriteBatch.begin();
                for(HollowBar b : bar) {
                        spriteBatch.draw(b.sprite,b.x,b.y,0,0,b.dx,b.dy,1,1,b.rotation);
                        b.clicBar();
                }
                for(HollowBlock b : block) {
                        spriteBatch.draw(b.sprite,b.x,b.y,0,0,b.dx,b.dy,1,1, 0);
                }
                spriteBatch.end();

        }

        public Array generateBar() {

                Array<HollowBar> bar = new Array<HollowBar>();
                float x = 300;
                float y = -400;

                for(int i=0; i<5; i++){
                        y = y+ 600;
                        x = 300;
                        for(int k=0; k<4; k++ ){
                                HollowBar b= new HollowBar(0,x,y);
                                x = x + 600;
                                bar.add(b);
                        }
                }

                x = -300;
                y = 300;
                for(int i=0; i<5; i++) {
                        x = x + 600;
                        y = 300;
                        for (int k = 0; k < 4; k++) {
                                bar.add(new HollowBar(90,x,y));
                                y = y + 600;

                        }


                }
                return bar;
        }

        public Array<HollowBlock> generateBlock() {

                Array<HollowBlock> block = new Array<HollowBlock>();
                float x = -250;
                float y;

                for(int i=0; i<4; i++) {
                        x = x + 600;
                        y = 350;
                        for (int k = 0; k < 4; k++) {
                                block.add(new HollowBlock(x, y));
                                y = y + 600;
                        }
                }
                return block;
        }

        @Override
        public void render(float delta){




        }
        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {
                // start the playback of the background music
                // when the screen is shown

        }

        @Override
        public void hide() {
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {


        }
}
