package flipull.action;

import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.BlockGroup;
import flipull.actor.BlocksHolder;
import flipull.actor.MainBlock;
import flipull.actor.StageHolder;

public class MoveBackActionStart implements Runnable
{
    
    @Override
    public void run()
    {
        Stage stage = StageHolder.getStage();
        
        BlockGroup blockGroup = BlocksHolder.getInstance(stage).getBlockGroup();
        blockGroup.disableAllCollision();
        
        MainBlock mainBlock = BlocksHolder.getInstance(stage).getMainBlock();
        mainBlock.setMoving(true);
    }
    
}