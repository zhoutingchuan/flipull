package flipull.actor;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class EggHolder
{
    private Stage stage;
    
    private static Map<Stage, EggHolder> eggHolders = new HashMap<Stage, EggHolder>();
    
    private EggHolder(Stage stage)
    {
        this.stage = stage;
    }
    
    public static EggHolder getInstance(Stage stage)
    {
        if (eggHolders.get(stage) != null)
        {
            return eggHolders.get(stage);
        }
        
        EggHolder eggHolder = new EggHolder(stage);
        eggHolders.put(stage, eggHolder);
        return eggHolder;
    }
    
    public Egg getEgg()
    {
        return stage.getRoot().findActor("egg");
    }
    
}
