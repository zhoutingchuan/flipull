package flipull.actor.label;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import flipull.constant.GameConstant;
import flipull.font.ScoreFont;
import flipull.level.GameParam;

public class ScoreLabel extends Actor
{
    
    private Label scoreLabel;
    
    private Label scoreNumLabel;
    
    public ScoreLabel()
    {
        LabelStyle labelStyle = new LabelStyle(new ScoreFont(), Color.WHITE);
        scoreLabel = new Label("SCORE", labelStyle);
        scoreLabel.setPosition(90, GameConstant.Game.GAME_H - 53);
        
        scoreNumLabel = new Label(formatScore(GameParam.getTotalScore()), labelStyle);
        scoreNumLabel.setPosition(200, GameConstant.Game.GAME_H - 53);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        scoreLabel.draw(batch, parentAlpha);
        
        scoreNumLabel.setText(formatScore(GameParam.getTotalScore()));
        scoreNumLabel.draw(batch, parentAlpha);
    }
    
    private String formatScore(long score)
    {
        String formatScore = "";
        int level;
        if (score == 0)
        {
            level = 1;
        }
        else
        {
            level = (int)Math.ceil(Math.log10(score));
        }
        
        // ฒน8ธ๖0
        int compleZeroNum = 8 - level;
        
        for (int i = 0; i < compleZeroNum; i++)
        {
            formatScore = formatScore + "0";
        }
        formatScore = formatScore + score;
        
        return formatScore;
    }
    
}
