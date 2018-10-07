package flipull.score;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class GameScore
{
    
    private Sound scoreSound;
    
    public GameScore()
    {
        scoreSound = Gdx.audio.newSound(Gdx.files.internal("sound/scores.wav"));
    }
    
    public void countScore()
    {
        scoreSound.play();
    
        System.out.println("score sound play");
        
        Timer timer = new Timer();
        timer.schedule(new ScoreCountTask(), 5000);
    }
    
    class ScoreCountTask extends TimerTask
    {
        
        @Override
        public void run()
        {
            scoreSound.stop();
        }
        
    }
}
