package mapManager;

import stateManager.sprites.Botany;
import stateManager.sprites.Sprite;

import java.awt.Image;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Map;

public class TileMap {

    String mapname;
    String nextmapname;

    private int width;                  //地图的宽度
    private int height;                 //地图的高度

    private char[][] tilemap;
    private Image[][] tilemapImanges;

    private Sprite player;              //游戏者
    private LinkedList spritesList;     //sprite
    
    public TileMap(int width, int height) {
        //初始化地图尺寸
        this.width = width;
        this.height = height;
        //初始化地图内容
        tilemap = new char[this.width][this.height];
        tilemapImanges = new Image[this.width][this.height];
        spritesList = new LinkedList();
    }

    /**
     * 获取地图的宽度
     * @return 地图数组的宽度
     */
    public int getWidth() {
        return width;
    }
    /**
     * 获取地图的宽度
     * @return 地图数组的宽度
     */
    public int getHeight() {
        return height;
    }
    /**
     * 获取地图指定数据
     * @param x 横坐标
     * @param y 纵坐标
     * @return  地图指定位置数据 char
     */
    public char getTilechar(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return '\0';
        }
        else {
            return tilemap[x][y];
        }
    }
    /**
     * 获取地图指定位置的图片
     * @param x 横坐标
     * @param y 纵坐标
     * @return  地图指定位置的图片
     */
    public Image getTileImage(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return null;
        }
        else {
            return tilemapImanges[x][y];
        }
    }
    /**
     * 设置地图指定位置的数据与图片
     * @param x 横坐标
     * @param y 纵坐标
     * @param c 数据
     * @param image 地图指定位置的图片
     */
    public void setTile(int x, int y, char c, Image image) {
        //此处允许地图位置为空
        tilemap[x][y] = c;
        tilemapImanges[x][y] = image;
    }

    public Sprite getPlayer() {
        return player;
    }
    public void setPlayer(Sprite player) {
        this.player = player;
    }

    public void addSprite(Botany sprite) {
        spritesList.add(sprite);
    }
    public void removeSprite(Botany sprite) {
        spritesList.remove(sprite);
    }

    public String getMapname() {
        return mapname;
    }
    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public String getNextmapname() {
        return nextmapname;
    }
    public void setNextmapname(String nextmapname) {
        this.nextmapname = nextmapname;
    }

    public Iterator getSprites() {
        return spritesList.iterator();
    }
}
