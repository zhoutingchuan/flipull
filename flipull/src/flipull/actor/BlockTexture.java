package flipull.actor;

import com.badlogic.gdx.graphics.Texture;

public class BlockTexture
{
    
    private Texture texture;
    
    private int val;
    
    public BlockTexture(Texture texture, int val)
    {
        this.texture = texture;
        this.val = val;
    }
    
    public Texture getTexture()
    {
        return texture;
    }
    
    public void setTexture(Texture texture)
    {
        this.texture = texture;
    }
    
    public int getVal()
    {
        return val;
    }
    
    public void setVal(int val)
    {
        this.val = val;
    }
    
}
