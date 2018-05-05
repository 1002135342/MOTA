package stateManager.sprites;


import java.awt.*;

public abstract class Entity {

    private String EntityName;
    protected int x;                    //Entity的x坐标，用像素表示
    protected int y;                    //Entity的y坐标，用像素表示

    private static final int DIE_TIME = 1000;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DYING = 1;
    public static final int STATE_DEAD = 2;
    protected int state;

    public Entity(){

    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public String getEntityName() {
        return EntityName;
    }
    public void setEntityName(String entityName) {
        EntityName = entityName;
    }
    public int getState() {
        return state;
    }

    /**
     * 设置Entity状态
     */
    public abstract void setState(int state);

    /**
     * 获取当前帧
     * @return 当前动画帧Image
     */
    public abstract Image getImage();
}
