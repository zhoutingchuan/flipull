package flipull.constant;

public interface GameConstant
{
    
    interface Game
    {
        int GAME_W = 640;
        
        int GAME_H = 512;
        
        int GAME_X = GAME_W / 32;
        
        int GAME_Y = GAME_H / 32;
        
        int TIME = 180;
    }
    
    interface Wall
    {
        float WALL_W = 32;
        
        float WALL_H = 32;
    }
    
    interface Block
    {
        float BLOCK_W = 32;
        
        float BLOCK_H = 32;
        
        float BLOCK_MOVE_STEP = 32;
    }
    
    interface BlockGroup
    {
        
        float BLOCKGROUP_X = 32;
        
        float BLOCKGROUP_Y = 32;
        
    }
    
    interface MainBlock
    {
        int MAINBLOCK_X = 416;
        
        int MAINBLOCK_Y = 32;
        
        int MAINBLOCK_Y_POS_TOP = 13;
        
        int MAINBLOCK_Y_POS_BOTTOM = 1;
        
        float MAINBLOCK_Y_TOP = 32 * MAINBLOCK_Y_POS_TOP;
        
        float MAINBLOCK_Y_BOTTOM = 32 * MAINBLOCK_Y_POS_BOTTOM;
    }
    
    interface Egg
    {
        
        int EGG_X = 448;
        
        int EGG_Y = 32;
        
        float EGG_MOVE_STEP = 32;
        
        int EGG_Y_POS_TOP = 13;
        
        int EGG_Y_POS_BOTTOM = 1;
        
//        int EGG_SPINE_X = 16;
//        
//        int EGG_SPINE_Y = 18;
        
        float EGG_Y_TOP = 32 * EGG_Y_POS_TOP;
        
        float EGG_Y_BOTTOM = 32 * EGG_Y_POS_BOTTOM;
    }
    
}
