package flipull.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ScoreFont extends BitmapFont
{
    public ScoreFont()
    {
        super(Gdx.files.internal("font/score.fnt"));
    }
}
