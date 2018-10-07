package flipull.actor.behavior;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import flipull.action.MoveAction;
import flipull.action.MoveBackAction;
import flipull.actor.MainBlock;

public class RunBehavior implements MovingBehavior
{
    
    MainBlock mainBlock;
    
    Sound runSound;
    
    public RunBehavior(MainBlock mainBlock, Sound runSound)
    {
        this.mainBlock = mainBlock;
        this.runSound = runSound;
    }
    
    @Override
    public void move()
    {
        if (!mainBlock.isMoving())
        {
            runSound.play();
            
            mainBlock.setMoving(true);
            mainBlock.setMovingStartY(mainBlock.getY());
            
            Action moveTo = MoveAction.newAction(mainBlock.getY());
            Action moveBack = MoveBackAction.newAction(mainBlock.getX(), mainBlock.getY(), mainBlock.getY());
            
            SequenceAction seqAction = Actions.sequence(moveTo, moveBack);
            mainBlock.addAction(seqAction);
        }
    }
    
}
