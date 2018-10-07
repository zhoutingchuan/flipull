package flipull.actor.behavior;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import flipull.action.EggActionEnd;
import flipull.actor.BlocksHolder;
import flipull.actor.Egg;
import flipull.actor.MainBlock;
import flipull.actor.StageHolder;
import flipull.constant.GameConstant;

public class EggMovingUpBehavior implements MovingBehavior
{
    
    private float step = GameConstant.Egg.EGG_MOVE_STEP;
    
    private Egg egg;
    
    public EggMovingUpBehavior(Egg egg)
    {
        this.egg = egg;
    }
    
    @Override
    public void move()
    {
        if (egg.isMoving())
        {
            return;
        }
        
        MainBlock mainBlock = BlocksHolder.getInstance(StageHolder.getStage()).getMainBlock();
        
        if (mainBlock.isMoving())
        {
            return;
        }
        
        egg.setMoving(true);
        
        float y = egg.getY() + step;
        if (y >= GameConstant.Egg.EGG_Y_TOP)
        {
            y = GameConstant.Egg.EGG_Y_TOP;
        }
        
        Action moveUp = Actions.moveTo(GameConstant.Egg.EGG_X, y, 0.2f);
        Action moveEnd = Actions.run(new EggActionEnd());
        SequenceAction seqAction = Actions.sequence(moveUp, moveEnd);
        egg.addAction(seqAction);
        
    }
    
}
