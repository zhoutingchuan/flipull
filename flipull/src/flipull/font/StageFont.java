package flipull.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class StageFont extends BitmapFont
{
    public StageFont()
    {
        super(Gdx.files.internal("font/stage.fnt"));
    }
}
