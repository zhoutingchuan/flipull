package flipull.actor.behavior;

import com.badlogic.gdx.audio.Sound;

import flipull.actor.MainBlock;
import flipull.constant.GameConstant;

public abstract class MainBlockMovingTemplate implements MovingBehavior
{
    
    protected float step = GameConstant.Block.BLOCK_MOVE_STEP;
    
    protected MainBlock mainBlock;
    
    protected Sound movingSound;
    
    public MainBlockMovingTemplate(MainBlock mainBlock, Sound movingSound)
    {
        this.mainBlock = mainBlock;
        this.movingSound = movingSound;
    }
    
    protected void setMainBlockNextY(float y)
    {
        mainBlock.setNextY(y);
    }
    
    public void move()
    {
        mainBlock.setMoving(true);
        
        movingSound.play();
    }
}
