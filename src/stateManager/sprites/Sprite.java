package stateManager.sprites;

import java.awt.Image;
import java.lang.reflect.Constructor;

public class Sprite extends Entity{

    protected Animation anim;
    protected Animation backanim;
    protected Animation leftanim;
    protected Animation rightanim;


    private float dx;
    private float dy;

    private long stateTime;
    public Sprite(Animation anim) {
        this.anim = anim;
        stateTime = 0;
    }

    public Sprite(Animation anim, Animation backanim,
                  Animation leftanim, Animation rightanim) {
        this(anim);
        this.backanim = backanim;
        this.leftanim = leftanim;
        this.rightanim = rightanim;
    }

    public float getVelocityX() {
        return dx;
    }
    public float getVelocityY() {
        return dy;
    }

    public void setVelocityX(float dx) {
        this.dx = dx;
    }
    public void setVelocityY(float dy) {
        this.dy = dy;
    }

    public Image getImage() {
        if(getVelocityX() > 0)
            return leftanim.getImage();
        if(getVelocityX() < 0)
            return rightanim.getImage();
        if(getVelocityY() > 0)
            return backanim.getImage();

        //返回主动画
        return anim.getImage();
    }

    public int getWidth() {
        return anim.getImage().getWidth(null);
    }
    public int getHeight() {
        return anim.getImage().getHeight(null);
    }
    public float getMaxSpeed() {
        return 0;
    }

    public void setState(int state) {
        if (this.state != state) {
            this.state = state;
            stateTime = 0;
            if (state == STATE_DYING) {
                setVelocityX(0);
                setVelocityY(0);
            }
        }
    }
    public long getStateTime() {
    return stateTime;
}


    public boolean isAlive() {
        return (state == STATE_NORMAL);
    }

    public boolean isFlying() {
        return false;
    }
    public boolean isJumping() {
        return false;
    }

    public void update(long elapsedTime) {
        //更新Sprite动画
        anim.update(elapsedTime);

        //更新人物位置
        x += Math.round(dx * elapsedTime);
        y += Math.round(dy * elapsedTime);

        stateTime += elapsedTime;
    }
    public Object clone() {

        Constructor constructor = getClass().getConstructors()[0];
        try {
            return constructor.newInstance(new Object[] {
                    (Animation)anim.clone(),
                    (Animation)backanim.clone(),
                    (Animation)leftanim.clone(),
                    (Animation)rightanim.clone()
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

