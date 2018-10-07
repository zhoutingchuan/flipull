package flipull.actor;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import flipull.constant.GameConstant;
import flipull.level.GameParam;

public class BlockGroup extends Group
{
    
    public static final int X_NUM = 5;
    
    public static final int Y_NUM = 5;
    
    private GridBlock[][] blocks = new GridBlock[X_NUM][Y_NUM];
    
    public BlockGroup()
    {
        GridBlock block = null;
        for (int i = 0; i < X_NUM; i++)
        {
            for (int j = 0; j < Y_NUM; j++)
            {
                block = new GridBlock(new BlockPosition(i, j), BlockTextures.randomTexture());
                block.setBlockGroup(this);
                this.addActor(block);
                blocks[i][j] = block;
            }
        }
        GameParam.setBlockNums(X_NUM * Y_NUM);
    }
    
    public GridBlock[][] getBlocks()
    {
        return blocks;
    }
    
    public int maxHeightY()
    {
        int maxY = 0;
        for (int i = 0; i < X_NUM; i++)
        {
            for (int j = 0; j < Y_NUM; j++)
            {
                if (blocks[i][j] != null && !blocks[i][j].isRemoved() && j > maxY)
                {
                    maxY = j;
                }
            }
        }
        return maxY;
    }
    
    public int maxHeightY(int x)
    {
        // 这里是0不对，有可能为0的砖块也被消除掉了
        int maxY = -1;
        for (int j = 0; j < Y_NUM; j++)
        {
            if (blocks[x][j] != null && !blocks[x][j].isRemoved() && j > maxY)
            {
                maxY = j;
            }
        }
        return maxY;
    }
    
    public Block mostTopBlock(int x)
    {
        if (x >= X_NUM)
        {
            throw new RuntimeException("x can not >= " + Y_NUM);
        }
        
        for (int j = Y_NUM - 1; j >= 0; j--)
        {
            if (blocks[x][j] != null && !blocks[x][j].isRemoved())
            {
                return blocks[x][j];
            }
        }
        return null;
    }
    
    public Block mostRightBlock(int y)
    {
        if (y >= Y_NUM)
        {
            throw new RuntimeException("y can not >= " + Y_NUM);
        }
        
        for (int i = X_NUM - 1; i >= 0; i--)
        {
            if (blocks[i][y] != null && !blocks[i][y].isRemoved())
            {
                return blocks[i][y];
            }
        }
        return null;
    }
    
    public float maxHeightPositionY()
    {
        return GameConstant.BlockGroup.BLOCKGROUP_Y + (maxHeightY() + 1) * GameConstant.Block.BLOCK_H;
    }
    
    public float maxHeightPositionY(float x)
    {
        return GameConstant.BlockGroup.BLOCKGROUP_Y + (maxHeightY(getBlockXnum(x)) + 1) * GameConstant.Block.BLOCK_H;
    }
    
    public int maxWidthPostitionX(int y)
    {
        int maxX = -1;
        for (int i = 0; i < X_NUM; i++)
        {
            if (blocks[i][y] != null && !blocks[i][y].isRemoved() && i > maxX)
            {
                maxX = i;
            }
        }
        return maxX;
    }
    
    private int getBlockXnum(float x)
    {
        return (int)((x - GameConstant.BlockGroup.BLOCKGROUP_X) / GameConstant.Block.BLOCK_W);
    }
    
    public void disableAllCollision()
    {
        GridBlock gridBlock = null;
        for (Actor actor : this.getChildren())
        {
            gridBlock = (GridBlock)actor;
            gridBlock.setCanCollision(false);
        }
    }
    
    public void enableAllCollision()
    {
        GridBlock gridBlock = null;
        for (Actor actor : this.getChildren())
        {
            gridBlock = (GridBlock)actor;
            gridBlock.setCanCollision(true);
        }
    }
    
    public int getLeftBlocksSize()
    {
        if (this.getChildren() == null)
        {
            return 0;
        }
        return this.getChildren().size;
    }
    
    public void boom()
    {
        Timer timer = new Timer();
        GridBlock gridBlock = null;
        int index = 0;
        
        for (int j = Y_NUM - 1; j >= 0; j--)
        {
            for (int i = 0; i < X_NUM; i++)
            {
                if (blocks[i][j] != null && !blocks[i][j].isRemoved())
                {
                    gridBlock = blocks[i][j];
                    timer.schedule(new GroupBoomTask(gridBlock), 200 * index);
                    index++;
                }
            }
        }
        
    }
    
    class GroupBoomTask extends TimerTask
    {
        private GridBlock gridBlock;
        
        public GroupBoomTask(GridBlock gridBlock)
        {
            this.gridBlock = gridBlock;
        }
        
        @Override
        public void run()
        {
            gridBlock.boom();
        }
        
    }
}
