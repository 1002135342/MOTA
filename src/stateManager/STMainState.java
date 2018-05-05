package stateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;

import inputManager.GameAction;
import inputManager.InputManager;
import resourceManager.ResourceManager;
import mapManager.TileMap;
import mapManager.TileMapRenderer;
import stateManager.sprites.Botany;
import stateManager.sprites.Sprite;
import stateManager.sprites.Player;
import strangeManager.CollisionStrange;
import strangeManager.CollisionStrangeA;

public class STMainState extends State {

    private currentResourceManager resourceManager;
    private int width;                              //地图的宽
    private int height;                             //地图的高

    private TileMap currentMap;
    private TileMapRenderer renderer;
    private CollisionStrange collision;

    private String stateChange;
    private String nextMapName;                     //下一张地图名
    private GameAction moveUp;
    private GameAction moveDown;
    private GameAction moveLeft;
    private GameAction moveRight;
    private GameAction exit;

    private boolean isTalking;
    private boolean isAction;
    private boolean ischangeStair;                      //更改楼梯层数

    public STMainState(int width, int height) {

        this.width = width;
        this.height = height;

        moveUp = new GameAction("modeUp");
        moveDown = new GameAction("modeDown");
        moveLeft = new GameAction("moveLeft");
        moveRight = new GameAction("moveRight");
        exit = new GameAction("exit", GameAction.DETECT_INITAL_PRESS_ONLY);

        renderer = new TileMapRenderer();

        this.isTalking = false;
        this.isAction = false;
        this.nextMapName = "map000";
        this.ischangeStair = false;

        this.collision = new CollisionStrangeA();
    }

    /**
     * 处理输入事件
     * @param elapsedTime 经过的事件
     */
    private void actionForInput(long elapsedTime) {

            if (exit.isPressed()) {
                stateChange = StateManager.EXIT_GAME;
                return;
            }

            Player player = (Player)currentMap.getPlayer();
            float velocityX = 0;
            float velocityY = 0;
            if (player.isAlive()) {
                if (moveLeft.isPressed()) {
                    velocityX-=player.getMaxSpeed();
                    isAction = true;
                }
                if (moveRight.isPressed()) {
                    velocityX+=player.getMaxSpeed();
                    isAction = true;
                }
                if (moveUp.isPressed()) {
                    velocityY-=player.getMaxSpeed();
                    isAction = true;
                }
                if (moveDown.isPressed()) {
                    velocityY+=player.getMaxSpeed();
                    isAction = true;
                }
                player.setVelocityX(velocityX);
                player.setVelocityY(velocityY);
            }

            //int xx = TileMapRenderer.pixelsToTiles(player.getX());
            //int yy = TileMapRenderer.pixelsToTiles(player.getY());
            //System.out.println(currentMap.getTilechar(xx, yy));

            int x = TileMapRenderer.pixelsToTiles(player.getX() + velocityX * elapsedTime);
            int y = TileMapRenderer.pixelsToTiles(player.getY() + velocityY * elapsedTime);
            if(currentMap.getTilechar(x, y) == '1'){
                player.setVelocityX(0);
                player.setVelocityY(0);
            }

            if(isAction) {
                //执行碰撞检测
                Iterator i = currentMap.getSprites();
                while (i.hasNext()) {
                    Botany sprite = (Botany) i.next();
                    if (TileMapRenderer.pixelsToTiles(sprite.getX()) == x
                            && TileMapRenderer.pixelsToTiles(sprite.getY()) == y + 1)
                    {
                        collision.onCollison(currentMap, player, sprite);
                    }
                    //怪物更新
                    sprite.update(elapsedTime);
                }

                if(this.ischangeStair = ((CollisionStrangeA)collision).isChangeStair == true){
                    ((CollisionStrangeA)collision).isChangeStair = false;
                    currentMap = resourceManager.getMap(currentMap.getNextmapname());
                    currentMap.setPlayer(player);
                }

                isAction = false;
            }
        }

    @Override
    public String getName() {
        return "main";
    }
    @Override
    public String checkForStateChange() {
        return stateChange;
    }
    @Override
    public void loadResources(ResourceManager resManager) {

        resourceManager = (currentResourceManager)resManager;
        resourceManager.loadResources();

        renderer.setBackground(resourceManager.loadImage("background.png"));

        currentMap = resourceManager.getMap(nextMapName);
    }
    @Override
    public void start(InputManager inputManager) {
        inputManager.mapToKey(moveUp, KeyEvent.VK_UP);
        inputManager.mapToKey(moveDown, KeyEvent.VK_DOWN);
        inputManager.mapToKey(moveLeft, KeyEvent.VK_LEFT);
        inputManager.mapToKey(moveRight, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    }
    @Override
    public void stop() {

    }
    @Override
    public void draw(Graphics2D g) {
        renderer.draw(g, currentMap, width, height);
    }
    @Override
    public void update(long elapsedTime) {
        Sprite player = (Sprite)currentMap.getPlayer();

        //检测用户输入
        actionForInput(elapsedTime);

        // 更新玩家
        player.update(elapsedTime);

        // 怪物更新,删除已死亡的怪物
        Iterator i = currentMap.getSprites();
        while (i.hasNext()) {
            Botany sprite = (Botany)i.next();
                if (sprite.getState() == Sprite.STATE_DEAD) {
                    i.remove();
                }
            sprite.update(elapsedTime);
        }
    }
}