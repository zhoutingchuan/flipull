package flipull.actor.behavior;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import flipull.actor.GridBlock;
import flipull.constant.GameConstant;

public class DropBehavior implements MovingBehavior
{
    
    GridBlock block;
    
    public DropBehavior(GridBlock block)
    {
        this.block = block;
    }
    
    @Override
    public void move()
    {
        if (block.getBlockPosition().getY() == 0)
        {
            return;
        }
        
        // blockGroupλ�øı�
        block.getBlockGroup().getBlocks()[block.getBlockPosition().getX()][block.getBlockPosition().getY() - 1] = block;
        block.getBlockGroup().getBlocks()[block.getBlockPosition().getX()][block.getBlockPosition().getY()] = null;
        block.getBlockPosition().setY(block.getBlockPosition().getY() - 1);
        // ʵ��λ�øı�
        block.addAction(Actions.moveTo(block.getX(), block.getY() - GameConstant.Block.BLOCK_H, 0.3f));
    }
    
}
