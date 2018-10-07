package flipull.actor.behavior;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import flipull.action.MainBlockActionEnd;
import flipull.actor.MainBlock;
import flipull.constant.GameConstant;

public class MainBlockMovingUpBehavior extends MainBlockMovingTemplate
{

    public MainBlockMovingUpBehavior(MainBlock mainBlock, Sound movingSound)
    {
        super(mainBlock, movingSound);
    }

    @Override
    public void move()
    {
        if (mainBlock.isMoving())
        {
            return;
        }
        
        super.move();
        
        float y = mainBlock.getY() + step;
        if (y >= GameConstant.MainBlock.MAINBLOCK_Y_TOP)
        {
            y = GameConstant.MainBlock.MAINBLOCK_Y_TOP;
        }
        
        setMainBlockNextY(y);
        
        Action moveUp = Actions.moveTo(GameConstant.MainBlock.MAINBLOCK_X, y, 0.2f);
        Action moveEnd = Actions.run(new MainBlockActionEnd());
        SequenceAction seqAction = Actions.sequence(moveUp, moveEnd);
        mainBlock.addAction(seqAction);
        

    }

}
