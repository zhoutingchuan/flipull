package flipull.action;

import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.Egg;
import flipull.actor.EggHolder;
import flipull.actor.StageHolder;

public class EggActionEnd implements Runnable
{
    
    @Override
    public void run()
    {
        Stage stage = StageHolder.getStage();
        
        Egg egg = EggHolder.getInstance(stage).getEgg();
        
        egg.setMoving(false);
        
    }
    
}