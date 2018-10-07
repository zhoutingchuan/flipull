package flipull.listener;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.BlocksHolder;
import flipull.actor.Egg;
import flipull.actor.EggHolder;
import flipull.actor.MainBlock;

/**
 * Created by ztc on 2015/6/22.
 */
public class KeypressDetector extends GestureDetector
{
    
    private MainBlock mainBlock;
    
    private Egg egg;
    
    private PointOutDetector pointOutDetector;
    
    public KeypressDetector(Stage stage)
    {
        super(new GestureAdapter());
        mainBlock = BlocksHolder.getInstance(stage).getMainBlock();
        egg = EggHolder.getInstance(stage).getEgg();
    }
    
    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.W:
            case Input.Keys.UP:
            {
                pointOutArrow();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN:
            {
                pointOutArrow();
                break;
            }
        }
        return true;
    }
    
    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.W:
            case Input.Keys.UP:
            {
                egg.moveUp();
                mainBlock.moveUp();
                break;
            }
            case Input.Keys.S:
            case Input.Keys.DOWN:
            {
                egg.moveDown();
                mainBlock.moveDown();
                break;
            }
        }
        return true;
    }
    
    private void pointOutArrow()
    {
        pointOutDetector.point(mainBlock.getNextY());
    }
    
    public PointOutDetector getPointOutDetector()
    {
        return pointOutDetector;
    }
    
    public void setPointOutDetector(PointOutDetector pointOutDetector)
    {
        this.pointOutDetector = pointOutDetector;
    }
    
}
