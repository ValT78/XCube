package com.mygdx.xcube.block;

import static com.badlogic.gdx.math.MathUtils.round;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.xcube.XCube;


public class Button extends Block {
    private String text;
    private BitmapFont font;
    private float scale;

    public Button(int x, int y, String sprite, String text, float scale) {
        this.x=x;
        this.y=y;
        this.sprite = new Sprite(new Texture(Gdx.files.internal(sprite)));
        this.dx=round(this.sprite.getWidth());
        this.dy=round(this.sprite.getHeight());
        this.rectangle = new Rectangle(x,y,dy*scale,dx*scale);
        this.text=text;
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Avenir.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = (int) (20*scale);
        font = fontGenerator.generateFont(fontParameter);
        font.setColor(Color.WHITE);
        this.scale=scale;
    }

    public void drawButton(XCube game, float rotation) {
        if(rotation==0) {
            game.batch.draw(sprite, x, y, 0, 0, dx, dy, scale, scale, rotation);
            font.draw(game.batch, text, x-dy*scale*15/16, y+dx*scale*3/4);
        }
        else {
            game.batch.draw(sprite, x+dy*scale, y, 0, 0, dx, dy, scale, scale, rotation);
            font.draw(game.batch, text, x+dy*scale*1/16, y+dx*scale*3/4);

        }

    }

    public void setSprite(String sprite) {
        this.sprite = new Sprite(new Texture(Gdx.files.internal(sprite)));
    }
    public void setText(String text) {
        this.text = text;
    }

    public boolean contains(float x, float y) {
        return this.rectangle.contains(x,y);
    }
}
