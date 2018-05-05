package resourceManager.resource;

import java.awt.*;

/**
 * 对话框资源类
 */
public class showTe {

    private Graphics2D graphics;

    public showTe(Graphics2D graphics){
        this.graphics = graphics;
    }

    public void talkBox(int x,int y, int width,int height,String string){
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(3f));
        graphics.drawRoundRect(x, y, width, height, 15, 15);
        graphics.setColor(Color.BLACK);
        graphics.fillRoundRect(x+3, y+3, width-5, height-5, 10, 10);
        graphics.setColor(Color.WHITE);
        graphics.drawString(string, x+5 , y + 25);
    }
}
