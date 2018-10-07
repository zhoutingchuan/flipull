package flipull.timer;

/*
 * simple timer used to countdown remaining time in the game
 */
public class GameTimer
{
    
    long timeStarted;
    
    long timeToFinish;
    
    boolean isStarted = false;
    
    long countDownMillis;
    
    long pauseTime;
    
    long pauseEndTime;
    
    public GameTimer(long countDownMillis)
    {
        // set initial time remaining
        this.timeToFinish = System.currentTimeMillis() + countDownMillis;
        this.countDownMillis = countDownMillis;
    }
    
    public void start()
    {
        if (!isStarted)
        {
            timeStarted = System.currentTimeMillis();
            this.timeToFinish = System.currentTimeMillis() + countDownMillis;
            isStarted = true;
        }
    }
    
    public void restart()
    {
        timeStarted = System.currentTimeMillis();
    }
    
    public void addTime(long timeToAdd)
    {
        timeToFinish += (timeToAdd * 1000);
    }
    
    public long getTimeRemaining()
    {
        if (isPause())
        {
            return timeToFinish - pauseEndTime;
        }
        
        return (timeToFinish - System.currentTimeMillis());
    }
    
    public int getTimeRemainingInSeconds()
    {
        return (int)(getTimeRemaining() / 1000);
    }
    
    public boolean timeEnd()
    {
        return this.getTimeRemaining() <= 0;
    }
    
    public void pause(long ms)
    {
        if (isPause())
        {
            return;
        }
        
        pauseTime = System.currentTimeMillis();
        
        pauseEndTime = pauseTime + ms;
        
        timeToFinish += ms;
    }
    
    public boolean isPause()
    {
        return System.currentTimeMillis() < pauseEndTime;
    }
}
