package stateManager;

import inputManager.GameAction;
import inputManager.InputManager;
import resourceManager.ResourceManager;
import stateManager.sprites.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;

public class STTextState extends State {

    private GameAction exit;
    Animation animations[];
    private boolean done;
    public STTextState() {
        animations = new Animation[4];
        exit = new GameAction("exit", GameAction.DETECT_INITAL_PRESS_ONLY);
    }

    @Override
    public String getName() {
        return "text";
    }

    @Override
    public void loadResources(ResourceManager resourceManager) {
        animations = resourceManager.getAnimations
                (resourceManager.loadImage("images/Characters/主角.png"));
    }
    @Override
    public String checkForStateChange(){
        return done?"load":null;
    }
    @Override
    public void start(InputManager inputManager) {
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
        done = false;
    }
    @Override
    public void stop() {
        // do nothing
    }
    @Override
    public void update(long elapsedTime) {
        animations[0].update(elapsedTime);
        if (exit.isPressed()) {
            done = true;
        }
    }
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(animations[0].getImage(), 100, 100, null);
    }
}
