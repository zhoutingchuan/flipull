package flipull.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageHolder
{

    private static Stage stage;

    public static Stage getStage()
    {
        return stage;
    }

    public static void setStage(Stage stage)
    {
        StageHolder.stage = stage;
    }
    
}
