package flipull.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import flipull.actor.behavior.EggMovingDownBehavior;
import flipull.actor.behavior.EggMovingUpBehavior;
import flipull.actor.behavior.MovingBehavior;
import flipull.animation.EggMoveAnimation;
import flipull.animation.EggWinAnimation;
import flipull.timer.GameTimer;

/**
 * Created by ztc on 2015/6/22.
 */
public class Egg extends Actor
{
    
    private MovingBehavior movingUpBehavior;
    
    private MovingBehavior movingDownBehavior;
    
    private boolean isMoving = false;
    
    private float stateTime;
    
    private EggMoveAnimation eggMoveAnimation;
    
    private int startX;
    
    private int startY;
    
    private TextureRegion currentFrame;

    private GameTimer jumpTimer;

    private boolean isJumping = false;
    
    private EggWinAnimation eggWinAnimation;
    
    public Egg(int x, int y)
    {
        this.setX(x);
        this.setY(y);
        
        startX = x;
        startY = y;
        
        movingUpBehavior = new EggMovingUpBehavior(this);
        movingDownBehavior = new EggMovingDownBehavior(this);
        
        eggMoveAnimation = EggMoveAnimation.getInstance();
        eggWinAnimation = EggWinAnimation.getInstance();
        
        jumpTimer = new GameTimer(3000);
    }
    
    public void draw(Batch batch, float parentAlpha)
    {
        stateTime += Gdx.graphics.getDeltaTime();
        
        if (isMoving)
        {
            currentFrame = eggMoveAnimation.getAnimation().getKeyFrame(stateTime, true);
        }
        else
        {
            currentFrame = eggMoveAnimation.getAnimation().getKeyFrame(0, true);
        }
        
        if(jumpTimer.timeEnd())
        {
            isJumping = false;
        }
        
        if(isJumping)
        {
            currentFrame = eggWinAnimation.getAnimation().getKeyFrame(stateTime, true);
        }
        
        batch.draw(currentFrame, this.getX(), this.getY());
    }
    
    public void jump()
    {
        jumpTimer.start();
        this.isJumping = true;
    }
    
    
    public void positionReset()
    {
        this.setX(startX);
        this.setY(startY);
    }
    
    public void moveUp()
    {
        movingUpBehavior.move();
    }
    
    public void moveDown()
    {
        movingDownBehavior.move();
    }
    
    public boolean isMoving()
    {
        return isMoving;
    }
    
    
    public void setMoving(boolean isMoving)
    {
        this.isMoving = isMoving;
    }
    
}
