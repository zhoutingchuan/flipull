package flipull.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import flipull.constant.GameConstant;

/**
 * Created by ztc on 2015/6/18.
 */
public class DesktopLauncher
{
    
    static LwjglApplicationConfiguration configuration = null;
    
    static
    {
        configuration = new LwjglApplicationConfiguration();
        configuration.width = GameConstant.Game.GAME_W;
        configuration.height = GameConstant.Game.GAME_H;
        configuration.title = "flipull";
    }
    
    public static void launchDesktopGame(Game game)
    {
        new LwjglApplication(game, configuration);
    }
    
    public static void launchScreen(final Screen lScreen)
    {
        launchDesktopGame(new Game()
        {
            @Override
            public void create()
            {
                this.setScreen(lScreen);
            }
            
            @Override
            public void render()
            {
                super.render();
            }
        });
    }
    
}
