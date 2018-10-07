package flipull.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EggMoveAnimation
{

    private Animation animation;
    
    private TextureRegion[] frames;
    
    private static EggMoveAnimation eggMoveAnimation = new EggMoveAnimation();
    
    private EggMoveAnimation()
    {
        frames = new TextureRegion[20];
        
        for (int i = 0; i < 20; i++)
        {
            frames[i] = new TextureRegion(new Texture(Gdx.files.internal("animation/egg/move/moveAnimation_" + i + ".png")));
        }
        
        animation = new Animation(0.05f, frames);
    }

    public static EggMoveAnimation getInstance()
    {
        return eggMoveAnimation;
    }

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }
    
}
