package flipull.action;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.actor.Block;
import flipull.actor.BlockGroup;
import flipull.actor.BlocksHolder;
import flipull.actor.MainBlock;
import flipull.actor.MissLabel;
import flipull.actor.StageHolder;
import flipull.level.GameParam;
import flipull.level.Level;
import flipull.level.LevelHolder;
import flipull.listener.DestDetector;

public class MainBlockActionEnd implements Runnable
{
    
    @Override
    public void run()
    {
        Stage stage = StageHolder.getStage();
        BlockGroup blockGroup = BlocksHolder.getInstance(stage).getBlockGroup();
        blockGroup.enableAllCollision();
        
        MainBlock mainBlock = BlocksHolder.getInstance(stage).getMainBlock();
        
        mainBlock.setMoving(false);
        mainBlock.setHasRemovedBlock(false);
        
        DestDetector destDetector = new DestDetector(stage);
        Set<Block> destBlocks = destDetector.allDestBlocks();
        System.out.println(destBlocks);
        
        if (!mainBlock.hasSame(destBlocks))
        {
            System.out.println("no more moves!");
            
            Level level = LevelHolder.getCurrentLevel();
            if (level.getMinLevelPassLeftNum() >= blockGroup.getLeftBlocksSize())
            {
                System.out.println("level pass!!");
                
                level.levelPass();
                return;
            }
            
            if (GameParam.getLightningNum() <= 0)
            {
                GameParam.setGameOver(true);
            }
            else
            {
                GameParam.setLightningNum(GameParam.getLightningNum() - 1);
                
                MissLabel missLabel = new MissLabel();
                missLabel.setPosition(250, 300);
                stage.addActor(missLabel);
                
                Sound missSound = Gdx.audio.newSound(Gdx.files.internal("sound/miss.wav"));
                missSound.play();
                mainBlock.changeLightning();
            }

        }
        
    }
    
}