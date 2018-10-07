package flipull.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;

import flipull.timer.GameTimer;

public class JumpAction extends Action
{
    
    private AnimationState state;
    
    private Skeleton skeleton;
    
    private GameTimer jumpTimer;
    
    public JumpAction(AnimationState state, Skeleton skeleton)
    {
        this.state = state;
        this.skeleton = skeleton;
        this.jumpTimer = new GameTimer(3310);
        
        
        //state.addListener(new AnimationStateAdaptor);
    }
    
    public void start()
    {
        jumpTimer.start();
    }
    
    @Override
    public boolean act(float delta)
    {
        if (jumpTimer.timeEnd())
        {
            state.setAnimation(0, "animation", true);
            skeleton.setToSetupPose();
            return true;
        }
        state.update(delta);
        return false;
    }
    
}
