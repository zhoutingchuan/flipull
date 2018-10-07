package flipull.listener;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.BlocksHolder;
import flipull.actor.MainBlock;

public class TapDetector extends GestureDetector
{
    
    Stage stage;
    
    public TapDetector(Stage stage)
    {
        super(new GestureAdapter());
        this.stage = stage;
        this.setLongPressSeconds(1f); // ���ó������жϱ�׼Ϊ1��
    }
    
    @Override
    public boolean touchUp(float x, float y, int pointer, int button)
    {
        
        MainBlock mainBlock = BlocksHolder.getInstance(stage).getMainBlock();
        
        mainBlock.run();
        
        return super.touchUp(x, y, pointer, button);
    }
    
}