package flipull;

import com.badlogic.gdx.Game;

import flipull.desktop.DesktopLauncher;
import flipull.screen.StartScreen;

/**
 * Created by ztc on 2015/6/18.
 */
public class Flipull extends Game
{
    
    @Override
    public void create()
    {
        screen = new StartScreen(this);
        this.setScreen(screen);
    }
    
    public void render()
    {
        super.render();
    }
    
    public static void main(String[] args)
    {
        DesktopLauncher.launchDesktopGame(new Flipull());
    }
}
