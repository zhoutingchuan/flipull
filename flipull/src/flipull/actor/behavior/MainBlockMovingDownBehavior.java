package flipull.actor.behavior;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import flipull.action.MainBlockActionEnd;
import flipull.actor.MainBlock;
import flipull.constant.GameConstant;

public class MainBlockMovingDownBehavior extends MainBlockMovingTemplate
{
    
    public MainBlockMovingDownBehavior(MainBlock block, Sound movingSound)
    {
        super(block, movingSound);
    }
    
    @Override
    public void move()
    {
        if (mainBlock.isMoving())
        {
            return;
        }
        
        super.move();
        
        float y = mainBlock.getY() - step;
        if (y <= GameConstant.MainBlock.MAINBLOCK_Y)
        {
            y = GameConstant.MainBlock.MAINBLOCK_Y;
        }
        
        setMainBlockNextY(y);
        
        Action moveDown = Actions.moveTo(GameConstant.MainBlock.MAINBLOCK_X, y, 0.2f);
        Action moveEnd = Actions.run(new MainBlockActionEnd());
        SequenceAction seqAction = Actions.sequence(moveDown, moveEnd);
        mainBlock.addAction(seqAction);
        
    }
    
}
