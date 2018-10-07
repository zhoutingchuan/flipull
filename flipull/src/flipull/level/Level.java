package flipull.level;

import flipull.actor.label.TimeLeftNumLabel;

public class Level
{
    
    private int levelNum;
    
    private int minLevelPassLeftNum = 8;
    
    private int levelTime;
    
    private int currentTime;
    
    private LevelBehavior levelBehavior;

    private TimeLeftNumLabel timeLeftNumLabel;
    
    public Level(int levelNum)
    {
        this.levelNum = levelNum;
        levelBehavior = new LevelBehavior(this);
    }
    
    public void levelPass()
    {
        levelBehavior.levelPass();
    }
    
    public int getLevelNum()
    {
        return levelNum;
    }
    
    public void setLevelNum(int levelNum)
    {
        this.levelNum = levelNum;
    }
    
    public int getMinLevelPassLeftNum()
    {
        return minLevelPassLeftNum;
    }
    
    public void setMinLevelPassLeftNum(int minLevelPassLeftNum)
    {
        this.minLevelPassLeftNum = minLevelPassLeftNum;
    }
    
    public int getLevelTime()
    {
        return levelTime;
    }
    
    public void setLevelTime(int levelTime)
    {
        this.levelTime = levelTime;
    }
    
    public int getCurrentTime()
    {
        return currentTime;
    }
    
    public void setCurrentTime(int currentTime)
    {
        this.currentTime = currentTime;
    }

    public TimeLeftNumLabel getTimeLeftNumLabel()
    {
        return timeLeftNumLabel;
    }

    public void setTimeLeftNumLabel(TimeLeftNumLabel timeLeftNumLabel)
    {
        this.timeLeftNumLabel = timeLeftNumLabel;
    }

}
