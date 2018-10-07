package flipull.action;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class CHBezierToAction extends TemporalAction
{
    protected float x, y;
    
    private boolean rotate;
    
    private Bezier<Vector2> bezier;
    
    /**
     * 设置贝塞尔曲线参数
     * 
     * @param bezierParam
     */
    public void setBezier(Bezier<Vector2> bezierParam)
    {
        bezier = bezierParam;
    }
    
    public boolean isRotate()
    {
        return rotate;
    }
    
    public void setRotate(boolean rotate)
    {
        this.rotate = rotate;
    }
    
    @Override
    protected void begin()
    {
        lastPos = bezier.points.get(0);
        x = actor.getX();
        y = actor.getY();
    }
    
    @Override
    protected void end()
    {
        lastPos = null;
    }
    
    @Override
    public void reset()
    {
        super.reset();
        lastPos = null;
        bezier = null;
    }
    
    // 上个坐标
    private Vector2 lastPos;
    
    private Vector2 out = new Vector2();
    
    @Override
    protected void update(float percent)
    {
        
        bezier.valueAt(out, percent);
        // 更新坐标
        target.setPosition(out.x, out.y);
        // cache用于后面赋值
        Vector2 copyOut = out.cpy();
        // 计算当前坐标与上个坐标之间的角度，用于更新Actor的角度
        if (rotate)
        {
            float degrees = out.sub(lastPos).angle();
            target.setRotation(degrees);
        }
        // 赋值当前坐标
        lastPos = copyOut;
    }
    
    public static CHBezierToAction obtain(Bezier<Vector2> bezierParam, float duration)
    {
        Pool<CHBezierToAction> pool = Pools.get(CHBezierToAction.class);
        CHBezierToAction action = pool.obtain();
        action.setDuration(duration);
        action.setBezier(bezierParam);
        action.setPool(pool);
        return action;
    }
}