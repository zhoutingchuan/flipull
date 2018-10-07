package flipull.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlockBoomAnimation
{

    private Animation animation;
    
    private TextureRegion[] frames;
    
    private static BlockBoomAnimation blockBoomAnimation = new BlockBoomAnimation();
    
    private BlockBoomAnimation()
    {
        frames = new TextureRegion[4];
        
        for (int i = 0; i < 4; i++)
        {
            frames[i] = new TextureRegion(new Texture(Gdx.files.internal("animation/block/boom/boomAnimation_" + i + ".png")));
        }
        
        animation = new Animation(0.05f, frames);
    }

    public static BlockBoomAnimation getInstance()
    {
        return blockBoomAnimation;
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
