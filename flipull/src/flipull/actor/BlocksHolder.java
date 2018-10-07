package flipull.actor;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class BlocksHolder
{
    private Stage stage;
    
    private static Map<Stage, BlocksHolder> blocksHolders = new HashMap<Stage, BlocksHolder>();
    
    private BlocksHolder(Stage stage)
    {
        this.stage = stage;
    }
    
    public static BlocksHolder getInstance(Stage stage)
    {
        if (blocksHolders.get(stage) != null)
        {
            return blocksHolders.get(stage);
        }
        
        BlocksHolder blocksHolder = new BlocksHolder(stage);
        blocksHolders.put(stage, blocksHolder);
        return blocksHolder;
    }
    
    public MainBlock getMainBlock()
    {
        return stage.getRoot().findActor("mainBlock");
    }
    
    public BlockGroup getBlockGroup()
    {
        return stage.getRoot().findActor("blockGroup");
    }
    
}
