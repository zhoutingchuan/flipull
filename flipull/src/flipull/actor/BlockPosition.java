package flipull.actor;

public class BlockPosition
{
    private int x;
    
    private int y;
    
    public BlockPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "BlockPosition [x=" + x + ", y=" + y + "]";
    }
    
}
