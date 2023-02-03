package com.mygdx.pipopipette;



import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pipopipette.block.HollowBar;
import com.mygdx.pipopipette.block.HollowSquare;

public class GameScreen implements Screen {
        final Pipopipette game;
        public static OrthographicCamera camera;
        public static SpriteBatch spriteBatch = new SpriteBatch();
        Array<HollowBar> bar = generateBar();
        Array<HollowSquare> square = generateSquare();

        public GameScreen(final Pipopipette game) {
                this.game = game;
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 3000, 3000);
                ScreenUtils.clear(1,1,1,1);
                spriteBatch.setProjectionMatrix(camera.combined);
                for(HollowBar b : bar) {
                        b.drawBlock(camera, spriteBatch, "grey_bar.png");
                        spriteBatch.begin();
                        GameScreen.spriteBatch.draw(b.sprite,b.x,b.y,0,0,b.dx,b.dy,1,1,0);
                        spriteBatch.end();
                }
                for(HollowSquare b : square) {
                        b.drawBlock(camera, spriteBatch, "grey_square.png");
                }


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

                x = -400;
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

        public Array<HollowSquare> generateSquare() {

                Array<HollowSquare> block = new Array<HollowSquare>();
                float x = -250;
                float y;

                for(int i=0; i<4; i++) {
                        x = x + 600;
                        y = 350;
                        for (int k = 0; k < 4; k++) {
                                block.add(new HollowSquare(x, y));
                                y = y + 600;
                        }
                }
                return block;
        }

        //render s'execute toutes les frames
        @Override
        public void render(float delta){
                for(HollowBar b : bar) {
                        b.clicBar();
                }
                for(HollowSquare b : square) {
                        b.clicSquare();
                }


        }
        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {


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
