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
import flipull.level.GameParam;
import flipull.level.Level;
import flipull.level.LevelHolder;
import flipull.timer.GameTimer;

public class StageLoadScreen extends ScreenAdapter
{
    Stage stage;
    
    Music stageLoadMusic;
    
    Game game;
    
    GameTimer gameTimer;
    
    Level level;
    
    public StageLoadScreen()
    {
    
    }
    
    public StageLoadScreen(Game game)
    {
        this.game = game;
    }
    
    @Override
    public void show()
    {
        stage = new Stage(new StretchViewport(GameConstant.Game.GAME_W, GameConstant.Game.GAME_H));
        level = new Level(GameParam.getLevelNum());
        
        LevelHolder.setCurrentLevel(level);
        
        LabelStyle labelStyle = new LabelStyle(new StageFont(), Color.WHITE);
        Label label = new Label("STAGE  " + level.getLevelNum(), labelStyle);
        label.setPosition(stage.getWidth() / 2 - label.getWidth() / 2, stage.getHeight() / 2);
        stage.addActor(label);
        
        stageLoadMusic = Gdx.audio.newMusic(Gdx.files.internal("music/stage_load.wav"));
        stageLoadMusic.setLooping(false);
        stageLoadMusic.play();
        
        gameTimer = new GameTimer(3400);
        gameTimer.start();
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        stage.draw();
        
        if (game == null)
        {
            System.out.println("warning : game is null");
            return;
        }
        
        if (gameTimer.timeEnd())
        {
            game.setScreen(new GameScreen(game));
        }
    }
    
    @Override
    public void dispose()
    {
        stage.dispose();
        stageLoadMusic.dispose();
        
    }
    
    public static void main(String[] args)
    {
        DesktopLauncher.launchScreen(new StageLoadScreen());
    }
    
}
