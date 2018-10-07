package flipull.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import flipull.actor.behavior.MovingBehavior;
import flipull.actor.behavior.MainBlockMovingDownBehavior;
import flipull.actor.behavior.MainBlockMovingUpBehavior;
import flipull.actor.behavior.RunBehavior;

public class MainBlock extends Block
{
    
    private MovingBehavior movingUpBehavior;
    
    private MovingBehavior movingDownBehavior;
    
    private MovingBehavior runBehavior;
    
    private float movingStartY;
    
    private float nextY;
    
    private boolean isMoving = false;
    
    private boolean hasRemovedBlock = false;
    
    private Sound movingSound;
    
    private Sound runSound;
    
    private Sound sameSound;
    
    private Sound notSameSound;
    
    private Sound wallSound;
    
    private int startX;
    
    private int startY;
    
    public MainBlock(int x, int y, BlockTexture blockTexture)
    {
        super();
        init(blockTexture);
        this.setX(x);
        this.setY(y);
        this.rectangle.setX(x);
        this.rectangle.setY(y);
        
        startX = x;
        startY = y;
        
        movingSound = Gdx.audio.newSound(Gdx.files.internal("sound/move.wav"));
        runSound = Gdx.audio.newSound(Gdx.files.internal("sound/run.wav"));
        sameSound = Gdx.audio.newSound(Gdx.files.internal("sound/same.wav"));
        notSameSound = Gdx.audio.newSound(Gdx.files.internal("sound/notsame.wav"));
        wallSound = Gdx.audio.newSound(Gdx.files.internal("sound/wall.wav"));
        
        movingUpBehavior = new MainBlockMovingUpBehavior(this, movingSound);
        movingDownBehavior = new MainBlockMovingDownBehavior(this, movingSound);
        runBehavior = new RunBehavior(this, runSound);
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
    
    public void run()
    {
        runBehavior.move();
    }
    
    public void clear()
    {
        super.clear();
        movingSound.dispose();
        runSound.dispose();
        sameSound.dispose();
        notSameSound.dispose();
    }
    
    public void touchSame()
    {
        sameSound.play();
    }
    
    public void touchDiff()
    {
        notSameSound.play();
    }
    
    public void touchWall()
    {
        wallSound.play();
    }
    
    public float getNextY()
    {
        return nextY;
    }
    
    public void setNextY(float nextY)
    {
        this.nextY = nextY;
    }
    
    public float getMovingStartY()
    {
        return movingStartY;
    }
    
    public void setMovingStartY(float movingStartY)
    {
        this.movingStartY = movingStartY;
    }

    public boolean isMoving()
    {
        return isMoving;
    }

    public void setMoving(boolean isMoving)
    {
        this.isMoving = isMoving;
    }

    public boolean isHasRemovedBlock()
    {
        return hasRemovedBlock;
    }

    public void setHasRemovedBlock(boolean hasRemovedBlock)
    {
        this.hasRemovedBlock = hasRemovedBlock;
    }
}
