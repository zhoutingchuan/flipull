package flipull.actor.label;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import flipull.font.ScoreFont;
import flipull.level.GameParam;

public class BlockNumLabel extends Actor
{
    
    private Label blockLabel;
    
    private Label blockNumLabel;
    
    public BlockNumLabel()
    {
        LabelStyle labelStyle = new LabelStyle(new ScoreFont(), Color.WHITE);
        blockLabel = new Label("BLOCK", labelStyle);
        blockLabel.setPosition(515, 190);
        
        blockNumLabel = new Label(String.valueOf(GameParam.getBlockNums()), labelStyle);
        blockNumLabel.setPosition(535, 170);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        blockLabel.draw(batch, parentAlpha);
     
        blockNumLabel.setText(String.valueOf(GameParam.getBlockNums()));
        blockNumLabel.draw(batch, parentAlpha);
    }
}
