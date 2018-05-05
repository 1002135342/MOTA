package stateManager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import inputManager.GameAction;
import inputManager.InputManager;
import resourceManager.ResourceManager;

public class STLoadState extends State {

    private final int MAXLOADTIME = 30000;          //最长加载时间
    private Image loadImage;

    private long totalElapsedTime;
    private boolean loading;

    private GameAction exit;                      //设置动作：退出

    public STLoadState(Image image) {
        this.loadImage = image;
        this.stateName = "load";
        exit = new GameAction("exit", GameAction.DETECT_INITAL_PRESS_ONLY);
    }

    /**
     * 设置 设置状态值
     * @param bool  false状态结束，true状态进行？？？？(错误的状态)
     */
    public void setloading(boolean bool){
        this.loading = bool;
    }

    /**
     * 设置状态名
     * @param stateName 状态名String
     */
    public void setName(String stateName) {
        this.stateName = stateName;
    }
    @Override
    public String getName() {
        return stateName;
    }
    @Override
    public void loadResources(ResourceManager resourceManager) {

    }
    @Override
    public String checkForStateChange() {
        if(exit.isPressed()){
            return StateManager.EXIT_GAME;
        }

        return loading ? null:"start";
    }
    @Override
    public void start(InputManager inputManager) {
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
        totalElapsedTime = 0;
        loading = true;
    }
    @Override
    public void stop() {

    }
    @Override
    public void update(long elapsedTime) {
        totalElapsedTime+=elapsedTime;
        if (totalElapsedTime > MAXLOADTIME) {
            loading = false;
        }
    }
    @Override
    public void draw(Graphics2D g) {
        //绘制载入动画
        g.drawImage(loadImage, 0, 0, null);
    }
}