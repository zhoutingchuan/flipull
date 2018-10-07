package flipull.action;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import flipull.constant.GameConstant;

public class MoveBackAction
{
    
    public static SequenceAction newAction(float sourceX, float sourceY, float targetY)
    {
        Action moveBackStart = Actions.run(new MoveBackActionStart());
        
        Bezier<Vector2> bezier = new Bezier<Vector2>(new Vector2(sourceX, sourceY),
            new Vector2((sourceX + GameConstant.MainBlock.MAINBLOCK_X) / 2, 400), new Vector2(GameConstant.MainBlock.MAINBLOCK_X, targetY));
        Action moveBack = CHBezierToAction.obtain(bezier, 1);
        
        Action moveEnd = Actions.run(new MainBlockActionEnd());
        return Actions.sequence(moveBackStart, moveBack, moveEnd);
    }
    
}
