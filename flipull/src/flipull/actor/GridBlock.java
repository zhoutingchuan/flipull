package flipull.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import flipull.actor.behavior.DropBehavior;
import flipull.actor.behavior.MovingBehavior;
import flipull.animation.BlockBoomAnimation;
import flipull.constant.GameConstant;

public class GridBlock extends Block
{
    
    private BlockPosition blockPosition;
    
    private BlockGroup blockGroup;
    
    private MovingBehavior dropBehavior;
    
    private BlockBoomAnimation blockBoomAnimation;
    
    private Sound boomSound;
    
    private float stateTime;
    
    private TextureRegion currentFrame;
    
    private boolean isBooming = false;
    
    public GridBlock(BlockPosition blockPosition, BlockTexture blockTexture)
    {
        init(blockTexture);
        this.blockPosition = blockPosition;
        this.setX(GameConstant.BlockGroup.BLOCKGROUP_X + blockPosition.getX() * width);
        this.setY(GameConstant.BlockGroup.BLOCKGROUP_Y + blockPosition.getY() * height);
        rectangle.setX(blockPosition.getX() * width);
        rectangle.setY(blockPosition.getY() * height);
        dropBehavior = new DropBehavior(this);
        
        boomSound = Gdx.audio.newSound(Gdx.files.internal("sound/boom.wav"));
        blockBoomAnimation = BlockBoomAnimation.getInstance();
    }
    
    public void fall()
    {
        
        this.setCanCollision(false);
        
        // 当前砖块如果已经被移除，不能move了，不然会影响后续别的砖块位置
        if (!this.isRemoved())
        {
            this.dropBehavior.move();
        }
        
        GridBlock aboveBlock = getAboveBlock();
        if (aboveBlock != null)
        {
            aboveBlock.fall();
        }
        
    }
    
    public GridBlock getAboveBlock()
    {
        if (blockGroup == null || blockPosition == null)
        {
            return null;
        }
        
        if (blockPosition.getY() == BlockGroup.Y_NUM - 1)
        {
            return null;
        }
        
        GridBlock aboveBlock = null;
        for (int y = blockPosition.getY() + 1; y <= BlockGroup.Y_NUM - 1; y++)
        {
            aboveBlock = blockGroup.getBlocks()[blockPosition.getX()][y];
            if (aboveBlock != null)
            {
                return aboveBlock;
            }
        }
        
        return null;
    }
    
    public List<GridBlock> getAboveBlockList()
    {
        if (blockGroup == null || blockPosition == null)
        {
            return null;
        }
        
        if (blockPosition.getY() == BlockGroup.Y_NUM - 1)
        {
            return null;
        }
        
        List<GridBlock> blocks = new ArrayList<GridBlock>();
        GridBlock block = null;
        for (int y = blockPosition.getY() + 1; y < BlockGroup.Y_NUM; y++)
        {
            block = blockGroup.getBlocks()[blockPosition.getX()][y];
            if (block != null)
            {
                blocks.add(block);
            }
            
        }
        
        return blocks;
    }
    
    public boolean remove()
    {
        blockGroup.getBlocks()[blockPosition.getX()][blockPosition.getY()] = null;
        
        return super.remove();
    }
    
    public void draw(Batch batch, float parentAlpha)
    {
        if (isBooming)
        {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = blockBoomAnimation.getAnimation().getKeyFrame(stateTime, true);
            super.textureRegion = this.currentFrame;
        }
        super.draw(batch, parentAlpha);
    }
    
    public void boom()
    {
        isBooming = true;
        boomSound.play();
        
        Timer timer = new Timer();
        timer.schedule(new BoomTask(this), 300);
        
    }
    
    class BoomTask extends TimerTask
    {
        
        private Block block;
        
        public BoomTask(Block block)
        {
            this.block = block;
        }
        
        @Override
        public void run()
        {
            block.remove();
        }
        
    }
    
    @Override
    public String toString()
    {
        return " blockPosition=" + blockPosition;
    }
    
    public BlockPosition getBlockPosition()
    {
        return blockPosition;
    }
    
    public void setBlockPosition(BlockPosition blockPosition)
    {
        this.blockPosition = blockPosition;
    }
    
    public BlockGroup getBlockGroup()
    {
        return blockGroup;
    }
    
    public void setBlockGroup(BlockGroup blockGroup)
    {
        this.blockGroup = blockGroup;
    }
}
