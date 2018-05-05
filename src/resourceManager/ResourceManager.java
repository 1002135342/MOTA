package resourceManager;

import mapManager.TileMap;
import mapManager.TileMapRenderer;
import stateManager.sprites.Animation;
import stateManager.sprites.Botany;
import stateManager.sprites.Sprite;

import java.awt.*;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * ResourceManager资源工具类，用于为资源加载提供工具
 * 2017-12-11
 */
public class ResourceManager {

    protected Map imagelist;                //所有用到的图片
    protected Map maplist;                  //所有用到的地图
    protected Map animList;                 //所有用到的地图
    protected Map spriteList;               //所有用到的地图

    public ResourceManager(){
        imagelist = new HashMap();
        maplist = new HashMap();
        animList = new HashMap();
        spriteList = new HashMap();
    }

    /**
     * 获取地图指定位置的图片
     * @param string 指定地图块名
     * @return  地图指定位置的图片
     */
    public Image getTileImage(String string) {
        return (Image)imagelist.get(string);
    }

    /**
     * 资源加载：图片加载函数
     * @param name 处于images路径下的图片名
     * @return 图片image
     */
    public Image loadImage(String name) {
        return new ImageIcon(name).getImage();
    }

    //以下几个函数用于动画生成
    /**
     * 动画对象生成函数
     * @param image 传入一张4*4图片对象
     * @return
     */
    public Animation[] getAnimations48(Image image){
        Animation animation[] = new Animation[4];
        Image image_cut[][] = cutImage(image,4,4,0,0,
                32,48,32,48);
        for (int i = 0; i < 4; i++) {
            animation[i] = new Animation();
            animation[i].addFrame(image_cut[i][0], 300);
            animation[i].addFrame(image_cut[i][1], 300);
            animation[i].addFrame(image_cut[i][2], 300);
            animation[i].addFrame(image_cut[i][3], 300);
        }


        return animation;
    }
    /**
     * 动画对象生成函数
     * @param image 传入一张4*4图片对象
     * @return
     */
    public Animation[] getAnimations(Image image){
        Animation animation[] = new Animation[4];
        Image image_cut[][] = cutImage(image,4,4,0,0,
                32,32,32,48);
        for (int i = 0; i < 4; i++) {
            animation[i] = new Animation();
            animation[i].addFrame(image_cut[i][0], 300);
            animation[i].addFrame(image_cut[i][1], 300);
            animation[i].addFrame(image_cut[i][2], 300);
            animation[i].addFrame(image_cut[i][3], 300);
    }


        return animation;
    }
    /**
     * 动画对象生成函数
     * @param image 传入一张4*1图片对象
     * @return
     */
    public Animation getAnimation(Image image, int time){
        Animation animation = new Animation();
        Image image_cut[][] = cutImage(image,1,4,0,0,
                32,32,32,0);

        animation = new Animation();
        animation.addFrame(image_cut[0][0], 300);
        animation.addFrame(image_cut[0][1], 300);
        animation.addFrame(image_cut[0][2], 300);
        animation.addFrame(image_cut[0][3], 300);
        return animation;
    }

    //以下几个函数用于图片切割
    /**
     * 分割图像
     * @param image 传入的图片对象
     * @param x 开始裁剪位置的X坐标
     * @param y 开始裁剪位置的Y坐标
     * @param width 裁剪的图片宽度
     * @param height 裁剪的图片高度
     * @return 裁剪完并加载到内存后的二维图片数组
     */
    public Image cutImage(Image image, int x, int y, int width, int height) {
        Image retImage;
        ImageFilter filter = new CropImageFilter(x, y, width, height);
        retImage = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(),
                                filter));
        return retImage;
    }
    /**
         * 分割图像
         * @param image 传入的图片对象
         * @param rows 垂直方向上需要裁剪出的图片数量 - 行
         * @param cols 水平方向上需要裁剪出的图片数量 - 列
         * @param x 开始裁剪位置的X坐标
         * @param y 开始裁剪位置的Y坐标
         * @param width 每次裁剪的图片宽度
         * @param height 每次裁剪的图片高度
         * @param changeX 每次需要改变的X坐标数量
         * @param changeY 每次需要改变的Y坐标数量
         * @return 裁剪完并加载到内存后的二维图片数组
     */
    public Image[][] cutImage(Image image, int rows, int cols, int x,
        int y, int width, int height, int changeX, int changeY) {
        if(image == null){
            System.out.print("图片对象为空");
            throw new NullPointerException();
        }

        Image[][] images = new Image[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ImageFilter filter = new CropImageFilter(x + j * changeX,
                        y + i * changeY, width, height);
                images[i][j] = Toolkit.getDefaultToolkit().createImage(
                                new FilteredImageSource(image.getSource(),
                                        filter));
            }
        }

        return images;
    }


    /**
     * 向地图添加所有怪物Sprite的引用，该函数用于之后对怪物的统一遍历
     * @param map 一张地图对象
     * @param hostSprite Sprite原型，可通过复制构造的方式生成Sprite
     * @param tileX 地图中的x坐标【数组值
     * @param tileY 地图中的y坐标【数组值
     */
    public void addSprite(TileMap map, Botany hostSprite, int tileX, int tileY) {
        if (hostSprite != null) {
            // 复制构造sprite
            Botany sprite = (Botany)hostSprite.clone();
            sprite.setEntityName(hostSprite.getEntityName());
            // 初始化sprite位置:像素位置
            sprite.setX(TileMapRenderer.tilesToPixels(tileX));
            sprite.setY(TileMapRenderer.tilesToPixels(tileY));

            // 添加sprite到spriteList;
            map.addSprite(sprite);
        }
    }
    /**
     * 向地图添加所有怪物Sprite的引用，该函数用于之后对怪物的统一遍历
     * @param map 一张地图对象
     * @param hostSprite Sprite原型，可通过复制构造的方式生成Sprite
     * @param tileX 地图中的x坐标【数组值
     * @param tileY 地图中的y坐标【数组值
     */
    public void addPlayer(TileMap map, Sprite hostSprite, int tileX, int tileY) {
        Sprite player = (Sprite)hostSprite;
        player.setX(TileMapRenderer.tilesToPixels(tileX));
        player.setY(TileMapRenderer.tilesToPixels(tileY));
        map.setPlayer(player);
    }

}

