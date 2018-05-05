package stateManager;

import java.awt.Graphics2D;

import inputManager.InputManager;
import resourceManager.ResourceManager;
public abstract class State {

    protected String stateName;
    /**
     * 获取状态名称
     */
    public abstract String getName();
    /**
     * 检测状态改变
     * @return 新的状态名String
     */
    public abstract String checkForStateChange();
    /**
     * 资源加载函数，用于统一加载资源统一加载
     * @param resourceManager 资源加载接口
     */
    public abstract void loadResources(ResourceManager resourceManager);
    /**
     * 状态启动函数
     * @param inputManager 输入接口，获取用户输入
     */
    public abstract void start(InputManager inputManager);
    /**
     * 状态终止函数
     */
    public abstract void stop();
    /**
     * 游戏更新函数
     * @param elapsedTime 时间更新值
     */
    public abstract void update(long elapsedTime);
    /**
     * 图形绘制函数
     * @param g Graphics2D
     */
    public abstract void draw(Graphics2D g);
}