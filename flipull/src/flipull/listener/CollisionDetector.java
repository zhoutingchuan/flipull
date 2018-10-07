package flipull.listener;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;

import flipull.action.MoveBackAction;
import flipull.action.MoveDownAction;
import flipull.actor.Block;
import flipull.actor.BlockGroup;
import flipull.actor.BlocksHolder;
import flipull.actor.GridBlock;
import flipull.actor.MainBlock;
import flipull.map.MapWallHolder;

public class CollisionDetector
{
    
    Stage stage;
    
    BlockGroup blockGroup;
    
    MainBlock mainBlock;
    
    List<CollisionListener> collisionListeners = new ArrayList<CollisionListener>();
    
    public CollisionDetector(Stage stage)
    {
        this.stage = stage;
        this.blockGroup = BlocksHolder.getInstance(stage).getBlockGroup();
        this.mainBlock = BlocksHolder.getInstance(stage).getMainBlock();
    }
    
    public void check()
    {
        checkWall();
        checkBlockGroup();
    }
    
    private void checkWall()
    {
        for (Rectangle rectangle : MapWallHolder.getWallRectangle())
        {
            if (mainBlock.overLaps(rectangle))
            {
                if (rectangle.getY() == 0)
                {
                    touchBottomWall(rectangle);
                    break;
                }
                touchLeftWall(rectangle);
                break;
            }
        }
    }
    
    private void touchBottomWall(Rectangle rectangle)
    {
        actionReset();
        mainBlock.touchWall();
        blockGroup.disableAllCollision();
        
        mainBlock.setY(rectangle.getY() + rectangle.getHeight());
        Action moveBack = MoveBackAction.newAction(mainBlock.getX(), mainBlock.getY(), mainBlock.getMovingStartY());
        mainBlock.addAction(moveBack);
    }
    
    private void touchLeftWall(Rectangle rectangle)
    {
        actionReset();
        mainBlock.touchWall();
        
        // 获取碰撞墙壁的右侧边界, 这里要把block的位置修正
        float realX = rectangle.getX() + rectangle.getWidth();
        mainBlock.setX(realX);
        Action moveDown = MoveDownAction.newAction(realX);
        mainBlock.addAction(moveDown);
        
    }
    
    private void checkBlockGroup()
    {
        SnapshotArray<Actor> blocks = blockGroup.getChildren();
        
        GridBlock block;
        for (Actor actor : blocks)
        {
            block = (GridBlock)actor;
            checkBlock(block);
        }
    }
    
    private void checkBlock(GridBlock block)
    {
        if (block.overLaps(mainBlock))
        {
            if (!block.isCanCollision())
            {
                return;
            }
            
            if (mainBlock.isLightning())
            {
                mainBlock.copy(block);
            }
            
            if (block.isSame(mainBlock))
            {
                matchSame(block);
            }
            else
            {
                matchDifferent(block);
            }
            
            for (CollisionListener collisionListener : collisionListeners)
            {
                collisionListener.blockCollision();
            }
        }
    }
    
    private void matchSame(GridBlock block)
    {
        
        mainBlock.touchSame();
        block.remove();
        block.fall();
        
        mainBlock.setHasRemovedBlock(true);
    }
    
    private void matchDifferent(Block block)
    {
        mainBlock.touchDiff();

        if (mainBlock.isHasRemovedBlock())
        {
            block.exchange(mainBlock);
        }
        
        actionReset();
        Action moveBack = MoveBackAction.newAction(mainBlock.getX(), mainBlock.getY(), mainBlock.getMovingStartY());
        mainBlock.addAction(moveBack);
    }
    
    private void actionReset()
    {
        mainBlock.getActions().get(0).reset();
    }
    
    public void addCollisionListener(CollisionListener collisionListener)
    {
        this.collisionListeners.add(collisionListener);
    }
    
    public void removeCollisionListener(CollisionListener collisionListener)
    {
        this.collisionListeners.remove(collisionListener);
    }
}
