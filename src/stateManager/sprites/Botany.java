package stateManager.sprites;

import java.awt.*;

public class Botany extends Entity{

    protected Animation anim;
    public Botany(Animation anim) {
        this.anim = anim;
    }

    public Botany(Image anim) {
        this.anim = new Animation();
        this.anim.addFrame(anim, 100);
    }
    public int getWidth() {
        return anim.getImage().getWidth(null);
    }
    public int getHeight() {
        return anim.getImage().getHeight(null);
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Image getImage() {
        try {
            return anim.getImage();
        }catch (Exception e){
            System.out.println(getEntityName());
            return anim.getImage();
        }

    }

    public void update(long elapsedTime) {
        //更新Sprite动画
        anim.update(elapsedTime);
    }
    public Object clone() {
        Botany botany = new Botany(anim);
        return botany;
    }
}
