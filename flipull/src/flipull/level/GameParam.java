package flipull.level;

public class GameParam
{
    private static int levelNum = 1;
    
    private static long totalScore;
    
    private static int lightningNum = 3;
    
    private static boolean isCurrentLevelPass = false;
    
    private static boolean isGameOver = false;
    
    private static int blockNums;
    
    public static void addLevelNum()
    {
        levelNum++;
    }
    
    public static int getLevelNum()
    {
        return levelNum;
    }
    
    public static void setLevelNum(int levelNum)
    {
        GameParam.levelNum = levelNum;
    }

    public static long getTotalScore()
    {
        //System.out.println("call getTotalScore, totalScore is " + totalScore);
        
        return totalScore;
    }

    public static void setTotalScore(long totalScore)
    {
        //System.out.println("call setTotalScore, totalScore is " + totalScore);

        GameParam.totalScore = totalScore;
    }

    public static int getLightningNum()
    {
        return lightningNum;
    }

    public static void setLightningNum(int lightningNum)
    {
        GameParam.lightningNum = lightningNum;
    }

    public static boolean isCurrentLevelPass()
    {
        return isCurrentLevelPass;
    }

    public static void setCurrentLevelPass(boolean isCurrentLevelPass)
    {
        GameParam.isCurrentLevelPass = isCurrentLevelPass;
    }

    public static boolean isGameOver()
    {
        return isGameOver;
    }

    public static void setGameOver(boolean isGameOver)
    {
        GameParam.isGameOver = isGameOver;
    }

    public static int getBlockNums()
    {
        return blockNums;
    }

    public static void setBlockNums(int blockNums)
    {
        GameParam.blockNums = blockNums;
    }
    
    public static void decreaseBlockNums()
    {
        GameParam.blockNums -- ;
    }
}
