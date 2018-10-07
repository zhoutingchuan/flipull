package flipull.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MoveAction
{
    public static SequenceAction newAction(float y)
    {
        Action moveTo = Actions.moveTo(0, y, 1);
        Action moveDown = Actions.moveTo(0, 0, 1);
        return Actions.sequence(moveTo, moveDown);
    }
}
