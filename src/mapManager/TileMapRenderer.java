package mapManager;

import java.awt.*;
import java.util.Iterator;

import resourceManager.ResourceManager;
import stateManager.sprites.Botany;
import stateManager.sprites.Sprite;

public class TileMapRenderer {

    private static final int TILE_SIZE = 32;
    private static final int TILE_SIZE_BITS = 5;

    private Image background;

    /**
     * 像素坐标转数组坐标
     * @param pixels 像素坐标
     * @return
     */
    public static int pixelsToTiles(float pixels) {
        return pixelsToTiles(Math.round(pixels));
    }
    public static int pixelsToTiles(int pixels) {
        return pixels >> TILE_SIZE_BITS;
    }
    /**
     * 数组坐标转像素坐标
     * @param numTiles 数组坐标
     * @return 像素坐标
     */
    public static int tilesToPixels(int numTiles) {
        return numTiles << TILE_SIZE_BITS;
    }

    /**
     * 设置地图背景
     * @param background 图片对象
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    /**
     * 绘制函数
     * @param g Graphics2D
     * @param map 当前地图
     * @param screenWidth 屏幕宽度
     * @param screenHeight 屏幕高度
     */
    public void draw(Graphics2D g, TileMap map, int screenWidth, int screenHeight) {

        int mapWidth = tilesToPixels(map.getWidth());           //地图的像素宽
        int mapHeight = tilesToPixels(map.getHeight());         //地图的像素长

        Sprite player = map.getPlayer();


        int offsetX = screenWidth / 2 - mapWidth / 2;              //地图偏移
        int offsetY = screenHeight / 2 - mapHeight / 2;            //地图偏移


        if (background == null || screenHeight > background.getHeight(null)) {
            //此处键入处理函数
        }

        if (background != null) {
            //此处键入处理函数
        }

        for (int y=0; y<map.getHeight(); y++) {
            for (int x=0; x <= map.getWidth(); x++) {
                Image image = map.getTileImage(x, y);
                if (image != null) {
                    g.drawImage(image, tilesToPixels(x) + offsetX,
                            tilesToPixels(y) + offsetY,
                            null
                    );
                }
            }
        }

        //绘制冒险者
        g.drawImage(player.getImage(),
                player.getX() + offsetX,
                player.getY() + offsetY,
                null
        );

        //绘制Botany
        Iterator i = map.getSprites();
        while (i.hasNext()) {
            Botany sprite = (Botany)i.next();
            g.drawImage(sprite.getImage(),
                    sprite.getX() + offsetX,
                    sprite.getY() + offsetY,
                    null
            );

        }
    }
}
