package flipull.timer;

/**
 * ��Ϸ��ͣ��
 * 
 * @author ztc
 *
 */
public class GamePauser
{
    
    private static GamePauser pauser = new GamePauser();
    
    public static GamePauser getInstance()
    {
        return pauser;
    }
    
    private GamePauser()
    {
    
    }
    
    public void pause(long ms)
    {
    
    }
    
    public void resume()
    {
    
    }
}
