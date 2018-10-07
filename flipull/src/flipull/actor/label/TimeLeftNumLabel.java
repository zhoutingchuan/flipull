package flipull.actor.label;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import flipull.constant.GameConstant;
import flipull.font.ScoreFont;
import flipull.level.GameParam;
import flipull.screen.GameScreen;
import flipull.screen.GameScreenHolder;
import flipull.timer.GameTimer;

/**
 * 
 * FIXME 这个类可以再抽象一下,把label的显示和行为分开
 * 
 * @author ztc
 *         
 */
public class TimeLeftNumLabel extends Actor
{
    
    private Label timeLeftNumLabel;
    
    private GameTimer gameTimer;
    
    private int timeRemainingSeconds;
    
    private boolean isPausing = false;
    
    private Sound timeislimitedSound;
    
    private boolean hasTimeislimited = false;
    
    public TimeLeftNumLabel()
    {
        gameTimer = new GameTimer(GameConstant.Game.TIME * 1000);
        gameTimer.start();
        
        LabelStyle labelStyle = new LabelStyle(new ScoreFont(), Color.WHITE);
        timeLeftNumLabel = new Label(formatTime(gameTimer.getTimeRemainingInSeconds()), labelStyle);
        timeLeftNumLabel.setPosition(510, 275);
        
        timeislimitedSound = Gdx.audio.newSound(Gdx.files.internal("sound/timeislimited.wav"));
        
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        
        if (!isPausing)
        {
            timeRemainingSeconds = gameTimer.getTimeRemainingInSeconds();
        }
        
        if (timeRemainingSeconds <= 0)
        {
            GameParam.setGameOver(true);
        }
        
        // 时间小于30时候，暂停2秒，并播放生效
        if (timeRemainingSeconds == 30 && !hasTimeislimited)
        {
            hasTimeislimited = true;
            gameTimer.pause(2 * 1000);
            timeislimitedSound.play();
            
            GameScreen gameScreen = GameScreenHolder.getInstance().getGameScreen();
            gameScreen.stopBackMusic();
            
            gameScreen.playBackMusicFast();
        }
        
        
        timeLeftNumLabel.setText(formatTime(timeRemainingSeconds));
        timeLeftNumLabel.draw(batch, parentAlpha);
    }
    
    private String formatTime(int seconds)
    {
        int minute = seconds / 60;
        int second = seconds % 60;
        
        if (second < 10)
        {
            return "0" + minute + " : " + "0" + second;
        }
        
        return "0" + minute + " : " + second;
        
    }
    
    public void pause()
    {
        isPausing = true;
        timeRemainingSeconds = gameTimer.getTimeRemainingInSeconds();
    }
    
    public void continueTiming()
    {
        isPausing = false;
    }
    
    public void countScore()
    {
        // GameParam.setTotalScore(GameParam.getTotalScore() + timeRemainingSeconds * 100);
        
        // timeRemainingSeconds = 0;
        
        this.addAction(new ScoreCountAction(GameParam.getTotalScore()));
        
    }
    
    public class ScoreCountAction extends TemporalAction
    {
        
        long sourceScore;
        
        public ScoreCountAction(long sourceScore)
        {
            this.sourceScore = sourceScore;
            this.setDuration(3f);
        }
        
        @Override
        protected void update(float percent)
        {
            GameParam.setTotalScore(sourceScore + (long)(timeRemainingSeconds * 100 * percent));
            
            //System.out.println("percent is " + percent);
        }
        
    }
    
}
