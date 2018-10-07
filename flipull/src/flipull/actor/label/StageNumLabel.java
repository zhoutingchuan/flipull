package flipull.actor.label;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import flipull.font.ScoreFont;
import flipull.level.GameParam;

public class StageNumLabel extends Actor
{
    
    private Label stageLabel;
    
    private Label stageNumLabel;
    
    public StageNumLabel()
    {
        LabelStyle labelStyle = new LabelStyle(new ScoreFont(), Color.WHITE);
        stageLabel = new Label("STAGE", labelStyle);
        stageLabel.setPosition(515, 90);
        
        stageNumLabel = new Label(String.valueOf(GameParam.getLevelNum()), labelStyle);
        stageNumLabel.setPosition(550, 70);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        stageLabel.draw(batch, parentAlpha);
        stageNumLabel.draw(batch, parentAlpha);
    }
}
