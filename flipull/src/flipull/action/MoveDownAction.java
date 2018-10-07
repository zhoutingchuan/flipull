package flipull.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MoveDownAction
{
    public static SequenceAction newAction(float x)
    {
        Action moveDown = Actions.moveTo(x, 0, 0.8f);
        return Actions.sequence(moveDown);
    }
}
