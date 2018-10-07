package flipull.actor.label;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import flipull.constant.GameConstant;
import flipull.font.ScoreFont;
import flipull.level.LevelHolder;

public class ClearLeftNumLabel extends Actor
{
    
    private Label clearLabel;
    
    private Label clearLeftNumLabel;
    
    public ClearLeftNumLabel()
    {
        LabelStyle labelStyle = new LabelStyle(new ScoreFont(), Color.WHITE);
        clearLabel = new Label("CLEAR", labelStyle);
        clearLabel.setPosition(505, GameConstant.Game.GAME_H - 140);
        
        clearLeftNumLabel = new Label(String.valueOf(LevelHolder.getCurrentLevel().getMinLevelPassLeftNum()), labelStyle);
        clearLeftNumLabel.setPosition(580, GameConstant.Game.GAME_H - 140);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        clearLabel.draw(batch, parentAlpha);
        clearLeftNumLabel.draw(batch, parentAlpha);
    }
}
