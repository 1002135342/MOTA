

import java.awt.*;
import stateManager.*;

public class GameManager extends GameCore {

    public static void main(String[] args) {
        new GameManager().run();
    }

    @Override
    public void addState(){
        log.info("初始化游戏状态机。。。");
        Image image = resourceManager.loadImage("defaultImage.jpg");
        stateManager = new StateManager(inputManager, image);
        stateManager.addState(new STMainState(screenManager.getWidth(), screenManager.getHeight()));
        stateManager.addState(new STLoadState(image));
        stateManager.addState(new STTextState());
        stateManager.addState(new STStartState(screenManager.getWidth(), screenManager.getHeight()));
    }
    @Override
    public void setState(){
        log.info("加载游戏资源。。。");
        stateManager.setState("load");
        stateManager.loadAllResources(resourceManager);
    }
    @Override
    public void preStop(){
        log.info("正在保存游戏设置。。。");
        log.info("正在退出。。。");
    }
}
