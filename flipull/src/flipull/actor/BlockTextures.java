package flipull.actor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlockTextures
{

    private static List<Texture> textures = new ArrayList<Texture>();
    
    private static Texture lightningTexture;
    
    static
    {
        
        Texture texture0 = new Texture(Gdx.files.internal("img/xx30.png"));
        Texture texture1 = new Texture(Gdx.files.internal("img/square30.png"));
        Texture texture2 = new Texture(Gdx.files.internal("img/triangle30.png"));
        Texture texture3 = new Texture(Gdx.files.internal("img/circle30.png"));
        
        textures.add(texture0);
        textures.add(texture1);
        textures.add(texture2);
        textures.add(texture3);
        
        lightningTexture = new Texture(Gdx.files.internal("img/lightning30.png"));
    }
    
    public static BlockTexture randomTexture()
    {
        int size = textures.size();
        int n = (int)Math.floor(Math.random() * size);
        return new BlockTexture(textures.get(n), n);
    }
    
    public static BlockTexture newLightningTexture()
    {
        return new BlockTexture(lightningTexture, MainBlockVal.LIGHTNING);
    }
    
    static class MainBlockVal
    {
        public static int LIGHTNING = -1;
    }

    public static Texture getLightningTexture()
    {
        return lightningTexture;
    }
    
}