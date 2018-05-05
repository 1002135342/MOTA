
import inputManager.InputManager;
import resourceManager.ResourceManager;
import screenManager.ScreenManager;
import stateManager.StateManager;
import stateManager.currentResourceManager;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public abstract class GameCore {

    protected ResourceManager resourceManager;
    protected InputManager inputManager;
    protected StateManager stateManager;
    protected ScreenManager screenManager;

    private boolean isRunning;                              //程序运行标志
    protected static final int FONT_SIZE = 24;
    private static final DisplayMode POSSIBLE_MODES[] = {
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(1024, 768, 32, 0),
    };


    /**
     * 启动函数
     */
    public void run() {
        try {
            init();
            gameLoop();
        }
        finally {
            screenManager.restoreScreen();
            lazilyExit();
        }
    }
    private void lazilyExit() {
        Thread thread = new Thread() {
            public void run() {
                // first, wait for the VM exit on its own.
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException ex) { }
                // system is still running, so force an exit
                System.exit(0);
            }
        };
        thread.setDaemon(true);
        thread.start();
    }
    /**
     * 终止标记函数
     */
    public void stop() {
        preStop();
        isRunning = false;
    }
    /**
     * 游戏初始化
     */
    private void init() {
        log.info("启动游戏核心进程。。。");
        screenManager = new ScreenManager();
        DisplayMode displayMode = screenManager.findFirstCompatibleMode(POSSIBLE_MODES);
        screenManager.setDisplayMode(displayMode, ScreenManager.SM_WMODE);

        Window window = screenManager.getFullScreenWindow();
        window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
        window.setBackground(Color.blue);
        window.setForeground(Color.white);

        isRunning = true;

        log.setLevel(Level.INFO);


        log.info("初始化输入管理器。。。");
        inputManager = new InputManager(screenManager.getFullScreenWindow());
        //设置为鼠标隐藏状态
        //inputManager.setCursor(InputManager.INVISIBLE_CURSOR);

        log.info("初始化资源工具集。。。");
        resourceManager = new currentResourceManager();

        addState();
        new Thread() {
            public void run() {
                setState();
            }
        }.start();
    }
    /**
     * 游戏主循环
     */
    private void gameLoop() {
        long startTime = System.currentTimeMillis();
        long currTime = startTime;

        while (isRunning) {
            long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;

            // 游戏更新
            update(elapsedTime);

            // 渲染更新
            Graphics2D g = screenManager.getGraphics();
            g.clearRect(0,0,screenManager.getWidth(), screenManager.getHeight());
            draw(g);
            g.dispose();
            screenManager.update();
        }
    }
    /**
     * 游戏更新函数
     * @param elapsedTime 时间更新值
     */
    private void update(long elapsedTime){
        if (stateManager.isDone()) {
            stop();
        }
        else {
            //时间是否应该平滑过渡，没这么精细
            //elapsedTime = timeSmoothie.getTime(elapsedTime);
            stateManager.update(elapsedTime);
        }
    }
    /**
     * 图形绘制函数
     * @param g Graphics2D
     */
    private void draw(Graphics2D g){
        stateManager.draw(g);
    }
    /**
     * 函数用于向状态机添加状态
     */
    protected abstract void addState();
    /**
     * 函数用于设置状态机状态
     */
    protected abstract void setState();
    /**
     * 函数用于结束预处理
     */
    protected abstract void preStop();

    static final Logger log = Logger.getLogger("./");   //日志接口
}
