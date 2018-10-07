package flipull.level;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;

import flipull.action.JumpAction;
import flipull.actor.BlockGroup;
import flipull.actor.BlocksHolder;
import flipull.actor.Egg;
import flipull.actor.EggHolder;
import flipull.actor.MainBlock;
import flipull.actor.StageHolder;
import flipull.actor.label.TimeLeftNumLabel;
import flipull.score.GameScore;
import flipull.screen.GameScreen;
import flipull.screen.GameScreenHolder;
import flipull.timer.GameTimer;

public class LevelBehavior
{
    
    private GameTimer gameWinTimer;
    
    private MainBlock mainBlock;
    
    private Egg egg;
    
    private BlockGroup group;
    
    private Music gameWinMusic;
    
    private Level level;
    
    public LevelBehavior(Level level)
    {
        this.level = level;
        
        gameWinTimer = new GameTimer(2200);
        gameWinMusic = Gdx.audio.newMusic(Gdx.files.internal("music/win.wav"));
        
    }
    
    public void levelPass()
    {
        GameScreen gameScreen = GameScreenHolder.getInstance().getGameScreen();
        gameScreen.stopBackMusic();
        
        Stage stage = StageHolder.getStage();
        group = BlocksHolder.getInstance(stage).getBlockGroup();
        
        egg = EggHolder.getInstance(stage).getEgg();
        
        egg.positionReset();
        
        mainBlock = BlocksHolder.getInstance(stage).getMainBlock();
        mainBlock.positionReset();
        
        level.getTimeLeftNumLabel().pause();
        gameWinTimer.start();
        
        JumpAction jumpAction = new JumpAction(egg.getState(), egg.getSkeleton());
        jumpAction.start();
        egg.addAction(jumpAction);

        gameWinMusic.play();
        group.boom();
        
        Timer timer = new Timer();
        timer.schedule(new GamePassTask(), 10000);
        
        timer.schedule(new ScoresCountTask(level.getTimeLeftNumLabel()), 3000);
        
    }
    
    class ScoresCountTask extends TimerTask
    {
        private TimeLeftNumLabel timeLeftNumLabel;
        
        public ScoresCountTask(TimeLeftNumLabel timeLeftNumLabel)
        {
            this.timeLeftNumLabel = timeLeftNumLabel;
        }
        
        @Override
        public void run()
        {
            timeLeftNumLabel.countScore();
            new GameScore().countScore();
        }
        
    }
    
    class GamePassTask extends TimerTask
    {
        
        @Override
        public void run()
        {
            GameParam.setCurrentLevelPass(true);
        }
        
    }
}
