package flipull.map;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader
{

    public TiledMap load()
    {
        TiledMap map = new TmxMapLoader(new InternalFileHandleResolver()).load("map/level.tmx");
        return map;
    }
    
}
