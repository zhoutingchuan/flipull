package flipull.factory;

import flipull.actor.BlockTextures;
import flipull.actor.MainBlock;
import flipull.constant.GameConstant;

public class MainBlockFactory
{
    
    public static MainBlock newMainBlock()
    {
        MainBlock mainBlock = new MainBlock(GameConstant.MainBlock.MAINBLOCK_X, GameConstant.MainBlock.MAINBLOCK_Y,
            BlockTextures.newLightningTexture());
        mainBlock.setName("mainBlock");
        return mainBlock;
    }
}
