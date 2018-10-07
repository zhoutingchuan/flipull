package flipull.listener;

import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.BlockGroup;
import flipull.actor.BlocksHolder;
import flipull.actor.PointArrow;
import flipull.constant.GameConstant;
import flipull.map.MapWallHolder;

public class PointOutDetector
{
    
    Stage stage;
    
    PointArrow pointArrow;
    
    BlocksHolder blocksHolder;
    
    BlockGroup blockGroup;
    
    float nextY;
    
    public PointOutDetector(Stage stage)
    {
        this.stage = stage;
        this.pointArrow = PointArrow.getInstance();
        blocksHolder = BlocksHolder.getInstance(stage);
        blockGroup = blocksHolder.getBlockGroup();
    }
    
    public void point()
    {
        if (nextY >= blockGroup.maxHeightPositionY())
        {
            calcArrowPosition();
            pointArrow.setVisible(true);
        }
        else
        {
            pointArrow.setVisible(false);
        }
    }
    
    public void point(float nextY)
    {
        this.nextY = nextY;
        
        if (nextY >= blockGroup.maxHeightPositionY())
        {
            calcArrowPosition();
            
            pointArrow.setVisible(true);
        }
        else
        {
            pointArrow.setVisible(false);
        }
    }
    
    private void calcArrowPosition()
    {
        float maxRightWallX = MapWallHolder.getMaxRightWallX(nextY);
        
        float arrowPositionX = maxRightWallX + GameConstant.Wall.WALL_W;
        float arrowPositionY = blockGroup.maxHeightPositionY(arrowPositionX);
        
        pointArrow.setPosition(arrowPositionX, arrowPositionY);
    }
    
}
