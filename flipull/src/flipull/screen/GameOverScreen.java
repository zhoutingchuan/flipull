package flipull.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import flipull.constant.GameConstant;
import flipull.desktop.DesktopLauncher;
import flipull.font.StageFont;

public class GameOverScreen extends ScreenAdapter
{
    Stage stage;
    
    Music gameOverMusic;
    
    Game game;
    
    public GameOverScreen()
    {
    
    }
    
    public GameOverScreen(Game game)
    {
        this.game = game;
    }
    
    @Override
    public void show()
    {
        stage = new Stage(new StretchViewport(GameConstant.Game.GAME_W, GameConstant.Game.GAME_H));
        
        LabelStyle labelStyle = new LabelStyle(new StageFont(), Color.WHITE);
        Label label = new Label("GAME OVER ", labelStyle);
        label.setPosition(stage.getWidth() / 2 - label.getWidth() / 2, stage.getHeight() / 2);
        stage.addActor(label);
        
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("music/gameover.wav"));
        gameOverMusic.setLooping(false);
        gameOverMusic.play();
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        stage.draw();
        
    }
    
    @Override
    public void dispose()
    {
        stage.dispose();
        
    }
    
    public static void main(String[] args)
    {
        DesktopLauncher.launchScreen(new GameOverScreen());
    }
    
}
