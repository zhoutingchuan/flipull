package flipull.test;

import flipull.desktop.DesktopLauncher;
import flipull.level.GameParam;
import flipull.screen.GameScreen;

public class GameScreenWinTest
{
    
    public static void main(String[] args)
    {
        GameParam.setCurrentLevelPass(true);
        DesktopLauncher.launchScreen(new GameScreen());
    }
    
}
