package stateManager;

import inputManager.GameAction;
import inputManager.InputManager;
import resourceManager.ResourceManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class STStartState extends State{

    private Image[] selectImage;
    private int selectNum;                        //存储玩家选择
    private int enterNum;                         //存储玩家选择

    private GameAction exit;                      //设置动作：退出
    private GameAction select;                    //设置动作：选定
    private GameAction moveUp;                    //设置动作：上
    private GameAction moveDown;                  //设置动作：下

    private int width;
    private int height;

    private boolean isrepaint;
    public STStartState(int width, int height) {
        moveUp = new GameAction("modeUp", GameAction.DETECT_INITAL_PRESS_ONLY);
        moveDown = new GameAction("modeDown", GameAction.DETECT_INITAL_PRESS_ONLY);
        exit = new GameAction("exit", GameAction.DETECT_INITAL_PRESS_ONLY);
        select = new GameAction("select", GameAction.DETECT_INITAL_PRESS_ONLY);

        this.selectNum = 1;
        this.enterNum = 0;
        this.stateName = "start";

        this.width = width;
        this.height = height;

        this.isrepaint = true;
    }

    @Override
    public String getName() {
        return stateName;
    }
    @Override
    public void loadResources(ResourceManager resourceManager) {
        selectImage = new Image[4];
        selectImage[0] = resourceManager.loadImage("");
        selectImage[1] = resourceManager.loadImage("");
        selectImage[2] = resourceManager.loadImage("");
        selectImage[3] = resourceManager.loadImage("");
    }
    @Override
    public String checkForStateChange() {
        //进入退出状态
        if(exit.isPressed() || enterNum == 3){
            return StateManager.EXIT_GAME;
        }
        //进入游戏状态
        if(enterNum == 1)
            return "main";
        //进入读取存档状态
        if(enterNum == 2)
            return "read";

        return null;
    }
    @Override
    public void start(InputManager inputManager) {
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
        inputManager.mapToKey(select, KeyEvent.VK_ENTER);
        inputManager.mapToKey(moveUp, KeyEvent.VK_UP);
        inputManager.mapToKey(moveDown, KeyEvent.VK_DOWN);
    }
    @Override
    public void stop() {

    }
    @Override
    public void update(long elapsedTime) {
        if(select.isPressed()){
            enterNum = selectNum;
            this.isrepaint = true;
        }

        if(moveUp.isPressed()){
            if(selectNum > 1){
                selectNum--;
            }
            this.isrepaint = true;
        }

        if(moveDown.isPressed()){
            if(selectNum < 3){
                selectNum++;
            }
            this.isrepaint = true;
        }
    }
    @Override
    public void draw(Graphics2D g) {
        if(!isrepaint){
            return;
        }
        Font font1 = new Font("宋体",Font.BOLD , 24);
        Font font2 = new Font("宋体",Font.BOLD , 18);
        g.setColor(Color.WHITE);
        if(selectNum == 1){

            g.setFont(font1);
            g.drawString("开始游戏",width/2,height/3*2);
            g.setFont(font2);
            g.drawString("读取记录",width/2,height/3*2+40);
            g.drawString("退出游戏",width/2,height/3*2+80);
        }
        if(selectNum == 2){
            g.setFont(font2);
            g.drawString("开始游戏",width/2,height/3*2);
            g.setFont(font1);
            g.drawString("读取记录",width/2,height/3*2+40);
            g.setFont(font2);
            g.drawString("退出游戏",width/2,height/3*2+80);
        }
        if(selectNum == 3){
            g.setFont(font2);
            g.drawString("开始游戏",width/2,height/3*2);
            g.drawString("读取记录",width/2,height/3*2+40);
            g.setFont(font1);
            g.drawString("退出游戏",width/2,height/3*2+80);
        }
    }
}
