package flipull.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import flipull.timer.GameTimer;

/**
 * Created by ztc on 2015/6/22.
 */
public class MissLabel extends Actor
{
    private Animation flashAnimation;
    
    private TextureRegion[] moveFrames;
    
    private float stateTime;
    
    private TextureRegion currentFrame;
    
    private GameTimer gameTimer;
    
    public MissLabel()
    {
        moveFrames = new TextureRegion[14];
        
        for (int i = 0; i < 14; i++)
        {
            moveFrames[i] =
                new TextureRegion(new Texture(Gdx.files.internal("animation/miss/flash/flashAnimation_" + i + ".png")));
        }
        
        flashAnimation = new Animation(0.1f, moveFrames);
        
        gameTimer = new GameTimer(1500);
        gameTimer.start();
    }
    
    public void draw(Batch batch, float parentAlpha)
    {
        if (gameTimer.timeEnd())
        {
            this.remove();
        }
        
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = flashAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, this.getX(), this.getY());
    }
    
    public void act(float delta)
    {
        super.act(delta);
    }
    
    public boolean remove()
    {
        return super.remove();
    }
    
}
