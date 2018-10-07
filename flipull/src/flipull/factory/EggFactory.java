package flipull.factory;

import flipull.actor.Egg;
import flipull.constant.GameConstant;

public class EggFactory
{
    public static Egg newEgg()
    {
        Egg egg = new Egg(GameConstant.Egg.EGG_X, GameConstant.Egg.EGG_Y);
        egg.setName("egg");
        return egg;
    }
}
