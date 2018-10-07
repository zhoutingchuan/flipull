package flipull.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.Rectangle;

public class MapWallLoader
{
    
    public void load(TiledMap tiledMap)
    {
        
        TiledMapTileSets tiledMapTileSets = tiledMap.getTileSets();
        
        TiledMapTileSet tiledMapTileSet = tiledMapTileSets.getTileSet("wall");
        
        Iterator<TiledMapTile> tiledMapTileIterator = tiledMapTileSet.iterator();
        TiledMapTile tiledMapTile = null;
        
        List<Integer> noPassIds = new ArrayList<Integer>();
        while (tiledMapTileIterator.hasNext())
        {
            tiledMapTile = tiledMapTileIterator.next();
            if ("false".equals(tiledMapTile.getProperties().get("pass")))
            {
                noPassIds.add(tiledMapTile.getId());
            }
        }
        
        MapLayers mapLayers = tiledMap.getLayers();
        TiledMapTileLayer mapLayer = (TiledMapTileLayer)mapLayers.get("wall");
        
        int h = mapLayer.getHeight();
        int w = mapLayer.getWidth();
        
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        
        Rectangle rectangle = null;
        Cell cell;
        for (int i = 0; i < w; i++)
        {
            for (int j = 0; j < h; j++)
            {
                cell = mapLayer.getCell(i, j);
                if (cell != null && noPassIds.contains(cell.getTile().getId()))
                {
                    rectangle = new Rectangle(i * mapLayer.getTileWidth(),
                        j * mapLayer.getTileHeight(), mapLayer.getTileWidth(),
                        mapLayer.getTileHeight());
                    rectangles.add(rectangle);
                }
            }
        }
        
        MapWallHolder.setWallRectangle(rectangles);
    }
    
}
