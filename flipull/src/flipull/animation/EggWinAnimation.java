package flipull.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EggWinAnimation
{

    private Animation animation;
    
    private TextureRegion[] frames;
    
    private static EggWinAnimation eggWinAnimation = new EggWinAnimation();
    
    private EggWinAnimation()
    {
        frames = new TextureRegion[20];
        
        for (int i = 0; i < 20; i++)
        {
            frames[i] = new TextureRegion(new Texture(Gdx.files.internal("animation/egg/jump/jumpAnimation_" + i + ".png")));
        }
        
        animation = new Animation(0.05f, frames);
    }

    public static EggWinAnimation getInstance()
    {
        return eggWinAnimation;
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
