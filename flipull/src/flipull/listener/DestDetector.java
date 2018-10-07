package flipull.listener;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.Block;
import flipull.actor.BlockGroup;
import flipull.actor.BlocksHolder;
import flipull.constant.GameConstant;
import flipull.map.MapWallHolder;

/**
 * 
 * Ŀ��λ�ü��
 * 
 * ���Ŀ��λ�ã���������������ķ���
 * 
 * @author ztc
 *         
 */
public class DestDetector
{
    Stage stage;
    
    BlockGroup blockGroup;
    
    public DestDetector(Stage stage)
    {
        this.stage = stage;
        blockGroup = BlocksHolder.getInstance(stage).getBlockGroup();
    }
    
    // ����Ϊ����ϵ������num
    public Block destBlock(int mainBlockY)
    {
        int adaptY = mainBlockY - GameConstant.MainBlock.MAINBLOCK_Y_POS_BOTTOM;
        
        if (adaptY <= blockGroup.maxHeightY())
        {
            return blockGroup.mostRightBlock(adaptY);
        }
        
        // ��������ļ����ֱ����mainBlockY������adaptY
        int x =
            (int)(MapWallHolder.getMaxRightWallX(mainBlockY * GameConstant.Block.BLOCK_H) / GameConstant.Wall.WALL_W);
        return blockGroup.mostTopBlock(x);
        
    }
    
    public Set<Block> allDestBlocks()
    {
        Set<Block> blocks = new HashSet<Block>();
        for (int y =
            GameConstant.MainBlock.MAINBLOCK_Y_POS_BOTTOM; y <= GameConstant.MainBlock.MAINBLOCK_Y_POS_TOP; y++)
        {
            Block destBlock = destBlock(y);
            if (destBlock != null)
            {
                blocks.add(destBlock(y));
            }
        }
        return blocks;
    }
    
}
