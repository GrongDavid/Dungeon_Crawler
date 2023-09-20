package objects.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static helper.src.Constants.PPM;

public class Player extends Entity{
    Body body;

    public Player(float width, float height, Body body, Texture img){
        super(width, height, body, img);
        this.speed = 4f;
        this.img = img;
        this.body = body;

    }

    @Override
    public void update() {
        x = body.getPosition().x + PPM;
        y = body.getPosition().y + PPM;

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(img, x, y, img.getWidth(), img.getHeight());
    }
}
