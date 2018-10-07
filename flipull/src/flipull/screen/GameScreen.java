package flipull.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import flipull.actor.BlockGroup;
import flipull.actor.Egg;
import flipull.actor.MainBlock;
import flipull.actor.PointArrow;
import flipull.actor.StageHolder;
import flipull.actor.label.BlockNumLabel;
import flipull.actor.label.ClearLeftNumLabel;
import flipull.actor.label.LightningLabel;
import flipull.actor.label.ScoreLabel;
import flipull.actor.label.StageNumLabel;
import flipull.actor.label.TimeLeftNumLabel;
import flipull.constant.GameConstant;
import flipull.desktop.DesktopLauncher;
import flipull.factory.EggFactory;
import flipull.factory.MainBlockFactory;
import flipull.level.GameParam;
import flipull.level.Level;
import flipull.level.LevelHolder;
import flipull.listener.CollisionDetector;
import flipull.listener.CollisionListener;
import flipull.listener.KeypressDetector;
import flipull.listener.PointOutDetector;
import flipull.listener.TapDetector;
import flipull.map.MapLoader;
import flipull.map.MapWallLoader;

/**
 * Created by ztc on 2015/6/18.
 */
public class GameScreen extends ScreenAdapter
{
    
    private Stage stage;
    
    private OrthogonalTiledMapRenderer mapRenderer;
    
    private CollisionDetector collisionDetector;
    
    private InputMultiplexer multiplexer;
    
    private Game game;
    
    private Music backgroundMusic;
    
    private Music backgroundFastMusic;
    
    private MainBlock mainBlock;
    
    private Egg egg;
    
    private BlockGroup group;
    
    public GameScreen()
    {
    
    }
    
    public GameScreen(Game game)
    {
        this.game = game;
    }
    
    @Override
    public void show()
    {
        if (LevelHolder.getCurrentLevel() == null)
        {
            LevelHolder.setCurrentLevel(new Level(1));
        }
        
        stage = new Stage(new StretchViewport(GameConstant.Game.GAME_W, GameConstant.Game.GAME_H), new PolygonSpriteBatch());
        group = new BlockGroup();
        group.setName("blockGroup");
        stage.addActor(group);
        
        mainBlock = MainBlockFactory.newMainBlock();
        stage.addActor(mainBlock);
        
        egg = EggFactory.newEgg();
        stage.addActor(egg);
        
        stage.addActor(PointArrow.getInstance());
        
        StageHolder.setStage(stage);
        
        collisionDetector = new CollisionDetector(stage);
        
        MapLoader mapLoader = new MapLoader();
        TiledMap gameMap = mapLoader.load();
        
        MapWallLoader mapWallLoader = new MapWallLoader();
        mapWallLoader.load(gameMap);
        
        mapRenderer = new OrthogonalTiledMapRenderer(gameMap);
        OrthographicCamera camera = (OrthographicCamera)stage.getCamera();
        mapRenderer.setMap(gameMap);
        mapRenderer.setView(camera);
        
        multiplexer = new InputMultiplexer();
        TapDetector tapDetector = new TapDetector(this.stage);
        
        final PointOutDetector pointOutDetector = new PointOutDetector(this.stage);
        KeypressDetector keypressDetector = new KeypressDetector(this.stage);
        keypressDetector.setPointOutDetector(new PointOutDetector(stage));
        
        collisionDetector.addCollisionListener(new CollisionListener()
        {
            @Override
            public void blockCollision()
            {
                pointOutDetector.point();
            }
        });
        
        multiplexer.addProcessor(tapDetector);
        multiplexer.addProcessor(keypressDetector);
        Gdx.input.setInputProcessor(multiplexer);
        
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/play_back.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        
        backgroundFastMusic = Gdx.audio.newMusic(Gdx.files.internal("music/play_back_fast.wav"));
        backgroundFastMusic.setLooping(true);
        
        ClearLeftNumLabel clearLeftNumLabel = new ClearLeftNumLabel();
        stage.addActor(clearLeftNumLabel);
        
        TimeLeftNumLabel timeLeftNumLabel = new TimeLeftNumLabel();
        stage.addActor(timeLeftNumLabel);
        
        LevelHolder.getCurrentLevel().setTimeLeftNumLabel(timeLeftNumLabel);
        
        BlockNumLabel blockNumLabel = new BlockNumLabel();
        stage.addActor(blockNumLabel);
        
        StageNumLabel stageNumLabel = new StageNumLabel();
        stage.addActor(stageNumLabel);
        
        LightningLabel lightningLabel = new LightningLabel();
        stage.addActor(lightningLabel);
        
        ScoreLabel scoreLabel = new ScoreLabel();
        stage.addActor(scoreLabel);
        
        GameScreenHolder.getInstance().setGameScreen(this);
        
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        mapRenderer.render();
        
        stage.act();
        stage.draw();
        
        if (mainBlock.isMoving())
        {
            collisionDetector.check();
        }
        
        if (GameParam.isGameOver())
        {
            this.dispose();
            this.stopBackMusic();
            game.setScreen(new GameOverScreen(game));
        }
        
        if (GameParam.isCurrentLevelPass())
        {
            GameParam.addLevelNum();
            GameParam.setCurrentLevelPass(false);
            game.setScreen(new StageLoadScreen(game));
            this.dispose();
        }
        
        // TODO  for test
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT))
        {
            LevelHolder.getCurrentLevel().levelPass();
        }
        
    }
    
    public void stopBackMusic()
    {
        this.backgroundMusic.stop();
        this.backgroundFastMusic.stop();
    }
  
    public void playBackMusicFast()
    {
        this.backgroundFastMusic.play();
    }
    
    @Override
    public void dispose()
    {
        stage.dispose();
        mapRenderer.dispose();
        backgroundMusic.dispose();
        
        for (int i = multiplexer.size() - 1; i >= 0; i--)
        {
            multiplexer.removeProcessor(i);
        }
        
    }
    
    public static void main(String[] args)
    {
        DesktopLauncher.launchScreen(new GameScreen());
    }
}
