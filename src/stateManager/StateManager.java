package stateManager;

import inputManager.InputManager;
import resourceManager.ResourceManager;

import java.awt.Image;
import java.awt.Graphics2D;
import java.util.*;

public class StateManager {

    public static final String EXIT_GAME = "_ExitGame";

    private Map gameStates;
    private Image defaultImage;
    private State currentState;
    private InputManager inputManager;
    private boolean done;

    public StateManager(InputManager inputManager, Image defaultImage) {
        this.inputManager = inputManager;
        this.defaultImage = defaultImage;
        gameStates = new HashMap();
    }

    public void addState(State state) {
        gameStates.put(state.getName(), state);
    }

    public Iterator getStates() {
        return gameStates.values().iterator();
    }

    public void loadAllResources(ResourceManager resourceManager) {
        Iterator i = getStates();
        while (i.hasNext()) {
            State gameState = (State)i.next();
            gameState.loadResources(resourceManager);
        }

        //结束加载状态
        if(currentState instanceof STLoadState)
            ((STLoadState)currentState).setloading(false);
    }

    public boolean isDone() {
        return done;
    }

    public void setState(String name) {

        if (currentState != null) {
            currentState.stop();
        }
        inputManager.clearAllMap();

        if (name == EXIT_GAME) {
            done = true;
        }
        else {

            currentState = (State)gameStates.get(name);
            if (currentState != null) {
                currentState.start(inputManager);
            }
        }
    }

    /**
     * 状态机更新函数
     * @param elapsedTime
     */
    public void update(long elapsedTime) {
        if (currentState == null) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException ex) { }
        }
        else {
            String nextState = currentState.checkForStateChange();
            if (nextState != null) {
                setState(nextState);
            }
            else {
                currentState.update(elapsedTime);
            }
        }
    }

    public void draw(Graphics2D g) {
        if (currentState != null) {
            currentState.draw(g);
        }
        else {
            //g.drawImage(defaultImage, 0, 0, null);
        }
    }
}