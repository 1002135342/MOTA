package stateManager.sprites;

public class door extends Botany{
    boolean active;

    public door(Animation animation){
        super(animation);
        active = false;
    }

    public void setActive() {
        setActive(true);
    }
    public void setActive(boolean active) {
        active = active;
    }

    @Override
    public Object clone() {
        return new door(anim);
    }

    @Override
    public void update(long elapsedTime) {
        if(active)
            super.update(elapsedTime);
    }
}
