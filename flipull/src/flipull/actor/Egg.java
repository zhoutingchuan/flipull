package flipull.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

import flipull.actor.behavior.EggMovingDownBehavior;
import flipull.actor.behavior.EggMovingUpBehavior;
import flipull.actor.behavior.MovingBehavior;
import flipull.animation.EggMoveAnimation;
import flipull.constant.GameConstant;

/**
 * Created by ztc on 2015/6/22.
 */
public class Egg extends Actor
{
    
    private MovingBehavior movingUpBehavior;
    
    private MovingBehavior movingDownBehavior;
    
    private boolean isMoving = false;
    
    private float stateTime;
    
    private EggMoveAnimation eggMoveAnimation;
    
    private int startX;
    
    private int startY;
    
    private SkeletonRenderer renderer;
    
    private TextureAtlas atlas;
    
    private Skeleton skeleton;
    
    private AnimationState state;
    
    public Egg(int x, int y)
    {
        this.setX(x);
        this.setY(y);
        
        startX = x;
        startY = y;
        
        movingUpBehavior = new EggMovingUpBehavior(this);
        movingDownBehavior = new EggMovingDownBehavior(this);
        
        eggMoveAnimation = EggMoveAnimation.getInstance();
        
        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(true); // PMA results in correct blending without outlines.
        
        atlas = new TextureAtlas(Gdx.files.internal("spine/egg/jump/egg.atlas"));
        SkeletonJson json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(1f); // Load the skeleton at 60% the size it was in Spine.
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("spine/egg/jump/egg.json"));
        
        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
        skeleton.setPosition(x, y);
        
        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between
                                                                             // animations.
        
        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time,
                                               // etc).
        state.setTimeScale(1.5f); // Slow all animations down to 50% speed.
        
        // Queue animations on track 0.
        state.setAnimation(0, "animation", false);
        state.addAnimation(0, "animation", false, 0);
    }
    
    public void draw(Batch batch, float parentAlpha)
    {
        stateTime += Gdx.graphics.getDeltaTime();
        
        // if (isMoving)
        // {
        // currentFrame = eggMoveAnimation.getAnimation().getKeyFrame(stateTime, true);
        // }
        // else
        // {
        // currentFrame = eggMoveAnimation.getAnimation().getKeyFrame(0, true);
        // }
        //
        state.apply(skeleton); // Poses skeleton using current animations. This sets the bones' local SRT.
        skeleton.updateWorldTransform(); // Uses the bones' local SRT to compute their world SRT.
        PolygonSpriteBatch polygonSpriteBatch = (PolygonSpriteBatch)batch;
        
        // spine绘制后会改变batch的混合属性，绘制完再改回来
        int blendSrc = polygonSpriteBatch.getBlendSrcFunc();
        int blendDst = polygonSpriteBatch.getBlendDstFunc();
        
        renderer.draw(polygonSpriteBatch, skeleton); // Draw the skeleton images.
        
        polygonSpriteBatch.setBlendFunction(blendSrc, blendDst);
    }
    
    public void positionReset()
    {
        this.setX(startX);
        this.setY(startY);
    }
    
    public void moveUp()
    {
        movingUpBehavior.move();
    }
    
    public void moveDown()
    {
        movingDownBehavior.move();
    }
    
    public boolean isMoving()
    {
        return isMoving;
    }
    
    public AnimationState getState()
    {
        return state;
    }
    
    public void setMoving(boolean isMoving)
    {
        this.isMoving = isMoving;
    }
    
    public Skeleton getSkeleton()
    {
        return skeleton;
    }
    
}
