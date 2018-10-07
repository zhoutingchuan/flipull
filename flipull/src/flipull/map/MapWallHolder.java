package flipull.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;

import flipull.constant.GameConstant;

public class MapWallHolder
{
    private static List<Rectangle> wallRectangle = new ArrayList<Rectangle>();
    
    public static List<Rectangle> getWallRectangle()
    {
        return wallRectangle;
    }
    
    public static void setWallRectangle(List<Rectangle> wallRectangle)
    {
        MapWallHolder.wallRectangle = wallRectangle;
    }
    
    public static float getMaxRightWallX(float postionY)
    {
        float maxRightWallX = 0;
        
        for (Rectangle rectangle : MapWallHolder.getWallRectangle())
        {
            if (rectangle.getY() == postionY && rectangle.getX() <= GameConstant.Game.GAME_W / 2
                && rectangle.getX() > maxRightWallX)
            {
                maxRightWallX = rectangle.getX();
            }
        }
        return maxRightWallX;
    }
}
