package flipull.screen;

public class GameScreenHolder
{
    private static GameScreenHolder gameScreenHolder = new GameScreenHolder();
    
    private GameScreen gameScreen;
    
    private GameScreenHolder()
    {
    
    }
    
    public static GameScreenHolder getInstance()
    {
        return gameScreenHolder;
    }
    
    public GameScreen getGameScreen()
    {
        return gameScreen;
    }
    
    public void setGameScreen(GameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
    }
    
}
