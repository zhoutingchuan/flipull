package flipull.actor.label;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import flipull.actor.BlockTextures;
import flipull.font.ScoreFont;
import flipull.level.GameParam;

public class LightningLabel extends Actor
{
    
    private Texture lightningTexture;
    
    private Label lightningLabel;
    
    public LightningLabel()
    {
        lightningTexture = BlockTextures.getLightningTexture();
        
        LabelStyle labelStyle = new LabelStyle(new ScoreFont(), Color.WHITE);
        lightningLabel = new Label("* " + String.valueOf(GameParam.getLightningNum()), labelStyle);
        lightningLabel.setPosition(555, 35);
        
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(lightningTexture, 515, 35);
        lightningLabel.setText("* " + String.valueOf(GameParam.getLightningNum()));
        lightningLabel.draw(batch, parentAlpha);
    }
}
