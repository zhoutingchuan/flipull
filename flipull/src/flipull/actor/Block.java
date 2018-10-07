package flipull.actor;

import java.util.Collection;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import flipull.actor.BlockTextures.MainBlockVal;
import flipull.constant.GameConstant;
import flipull.level.GameParam;

public abstract class Block extends Actor
{
    
    protected Rectangle rectangle;
    
    private BlockTexture blockTexture;
    
    protected TextureRegion textureRegion;
    
    protected float height = GameConstant.Block.BLOCK_H;
    
    protected float width = GameConstant.Block.BLOCK_W;
    
    private boolean canCollision = true;
    
    private boolean removed = false;
    
    protected void init(BlockTexture blockTexture)
    {
        rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        
        this.blockTexture = blockTexture;
        textureRegion = new TextureRegion(blockTexture.getTexture());
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(textureRegion, this.getX(), this.getY());
        rectangle.setX(this.getX());
        rectangle.setY(this.getY());
    }
    
    @Override
    public boolean remove()
    {
        removed = true;
        GameParam.decreaseBlockNums();
        return super.remove();
    }
    
    public boolean overLaps(Block block)
    {
        return this.rectangle.overlaps(block.rectangle);
    }
    
    public boolean overLaps(Rectangle rectangle)
    {
        return this.rectangle.overlaps(rectangle);
    }
    
    public void exchange(Block exBlock)
    {
        BlockTexture temp = exBlock.blockTexture;
        exBlock.blockTexture = this.blockTexture;
        this.blockTexture = temp;
        this.textureRegion = new TextureRegion(blockTexture.getTexture());
        exBlock.textureRegion = new TextureRegion(exBlock.blockTexture.getTexture());
    }
    
    public void copy(Block block)
    {
        this.blockTexture = block.blockTexture;
        this.textureRegion = new TextureRegion(blockTexture.getTexture());
    }
    
    public boolean isSame(Block block)
    {
        if (block == null)
        {
            return false;
        }
        
        return this.blockTexture.getVal() == block.blockTexture.getVal();
    }
    
    public boolean hasSame(Collection<Block> blocks)
    {
        if (this.isLightning())
        {
            return true;
        }
        
        for (Block block : blocks)
        {
            if (this.isSame(block))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isLightning()
    {
        return this.blockTexture.getVal() == MainBlockVal.LIGHTNING;
    }
    
    public void changeLightning()
    {
        this.blockTexture = BlockTextures.newLightningTexture();
        this.textureRegion = new TextureRegion(blockTexture.getTexture());
    }
    
    public boolean isCanCollision()
    {
        return canCollision;
    }
    
    public void setCanCollision(boolean canCollision)
    {
        this.canCollision = canCollision;
    }
    
    public boolean isRemoved()
    {
        return removed;
    }
    
}
