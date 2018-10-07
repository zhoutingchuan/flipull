package flipull.level;

public class LevelHolder
{

    private static Level currentLevel;

    public static Level getCurrentLevel()
    {
        return currentLevel;
    }

    public static void setCurrentLevel(Level currentLevel)
    {
        LevelHolder.currentLevel = currentLevel;
    }
    
    
    
}
