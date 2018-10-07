package flipull.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import flipull.constant.GameConstant;
import flipull.desktop.DesktopLauncher;
import flipull.timer.GameTimer;

/**
 * Created by ztc on 2015/6/18.
 */
public class StartScreen extends ScreenAdapter
{
    
    SpriteBatch batch;
    
    Texture img;
    
    Game game;
    
    GameTimer timer;
    
    int picX;
    
    public StartScreen()
    {
    
    }
    
    public StartScreen(Game game)
    {
        this.game = game;
    }
    
    @Override
    public void show()
    {
        batch = new SpriteBatch();
        img = new Texture("img/start.png");
        timer = new GameTimer(4000);
        timer.start();
        picX = (GameConstant.Game.GAME_W - img.getWidth()) / 2;
        
        batch.begin();
        batch.draw(img, picX, 0);
        batch.end();
    }
    
    @Override
    public void render(float delta)
    {
        batch.begin();
        batch.draw(img, picX, 0);
        batch.end();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) && game != null)
        {
            game.setScreen(new StageLoadScreen(game));
        }
    }
    
    public static void main(String[] args)
    {
        DesktopLauncher.launchScreen(new StartScreen());
    }
    
}
