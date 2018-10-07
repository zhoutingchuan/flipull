package flipull.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PointArrow extends Actor
{
    private static PointArrow pointArrow = new PointArrow();
    
    private Texture texture = new Texture(Gdx.files.internal("img/arrow.png"));;
    
    private TextureRegion textureRegion = new TextureRegion(texture);
    
    private PointArrow()
    {
        this.setVisible(false);
    }
    
    public static PointArrow getInstance()
    {
        return pointArrow;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(textureRegion, this.getX(), this.getY());
    }
    
}
