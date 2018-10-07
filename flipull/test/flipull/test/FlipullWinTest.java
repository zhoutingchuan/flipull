package flipull.test;

import flipull.Flipull;
import flipull.desktop.DesktopLauncher;
import flipull.level.GameParam;

public class FlipullWinTest
{
    
    public static void main(String[] args)
    {
        //GameParam.setCurrentLevelPass(true);
        DesktopLauncher.launchDesktopGame(new Flipull());
    }
    
}
