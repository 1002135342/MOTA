package stateManager;

import mapManager.TileMap;
import mapManager.TileMapRenderer;
import stateManager.sprites.*;
import strangeManager.MapStrange;
import resourceManager.ResourceManager;
import strangeManager.MapStrangeA;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class currentResourceManager extends ResourceManager{

    private ArrayList tiles;
    private MapStrange mapStrange;

    //地图中需要人物
    private Sprite playerSprite;

    public currentResourceManager(){
        mapStrange = new MapStrangeA();
    }

    public void loadResources() {

        loadImage_tilemap();            //加载地图图片资源
                                        //加载声音资源
        loadAnim_animList();            //加载动画资源
        loadPlayer_player();            //加载人物资源
        loadSprite_spriteList();        //加载怪物资源
        loadMap_maplist();              //加载地图数据资源
    }

    /**
     * 地图加载函数，加载指定地图名的地图
     * @param filename 地图名
     * @return  地图对象TileMap
     * @throws IOException
     */
    public TileMap loadMap(String filename) throws IOException {

        Image tmpImage = null;
        //向地图中添加地图数据
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("//")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }

        height = lines.size();
        TileMap newMap = new TileMap(width, height);
        for (int y=0; y<height; y++) {
            String line = (String)lines.get(y);
            for (int x=0; x<line.length(); x++) {
                char ch = line.charAt(x);
                String type = mapStrange.getImage(ch);

                //向地图中添加玩家数据
                if(type == "player"){
                    newMap.setTile(x, y, ch, (Image)imagelist.get("floor"));
                    addPlayer(newMap, playerSprite, x, y);
                }
                //向地图中添加建筑数据
                if(((MapStrangeA)mapStrange).aa == 1){
                    newMap.setTile(x, y, ch, (Image)imagelist.get(type));
                }
                //向地图中添加可消除数据
                if(((MapStrangeA)mapStrange).aa == 2){
                    newMap.setTile(x, y, ch, (Image)imagelist.get("floor"));
                    addSprite(newMap, (Botany)spriteList.get(type), x, y);
                }
            }
        }

        //将地图添加到地图列表
        maplist.put(filename, newMap);
        return newMap;
    }

    /**
     * 重置当前地图
     * @return 当前地图TileMap
     */
    public TileMap reloadMap(String filename) {
        TileMap tileMap = null;
        try {
            tileMap =  loadMap(filename);
        }
        catch (IOException ex) {
            tileMap =  null;
            ex.printStackTrace();
        }
        //存储当前地图并返回
        if(tileMap != null)
            maplist.replace(filename, tileMap);
        return tileMap;
    }

    /**
     * 获取指定地图
     */
    public TileMap getMap(String mapkey){
        return (TileMap) maplist.get(mapkey);
    }

    private void loadImage_tilemap(){
        imagelist.put("wall",loadImage("images/pictures/wall.png"));
        imagelist.put("floor",loadImage("images/pictures/floor.png"));

        imagelist.put("NPC1",loadImage("images/pictures/001-npc01-1.png"));
        imagelist.put("NPC2",loadImage("images/pictures/001-npc01-1.png"));
        imagelist.put("yellowkey",loadImage("images/pictures/101-01.png"));
        imagelist.put("bluekey",loadImage("images/pictures/101-02.png"));
        imagelist.put("redkey",loadImage("images/pictures/101-03.png"));
        imagelist.put("redblood",loadImage("images/pictures/103-05.png"));
        imagelist.put("blueblood",loadImage("images/pictures/103-06.png"));
        imagelist.put("greenblood",loadImage("images/pictures/103-07.png"));
        imagelist.put("weapon",loadImage("images/pictures/104-01.png"));
        imagelist.put("attack",loadImage("images/pictures/103-01.png"));
        imagelist.put("defence",loadImage("images/pictures/103-02.png"));
        imagelist.put("book",loadImage("images/pictures/101-13.png"));
        imagelist.put("upstair",loadImage("images/pictures/upstair.png"));
        imagelist.put("downstair",loadImage("images/pictures/downstair.png"));
        imagelist.put("door",loadImage("images/pictures/door.png"));
        imagelist.put("sprite",loadImage("images/pictures/guaiwu.png"));
    }
    private void loadAnim_animList(){
        animList.put("NPC1",getAnimations((Image)imagelist.get("NPC1"))[0]);
        animList.put("reddoor",getAnimations((Image)imagelist.get("door"))[1]);
        animList.put("bluedoor",getAnimations((Image)imagelist.get("door"))[2]);
        animList.put("yellowdoor",getAnimations((Image)imagelist.get("door"))[3]);
        animList.put("sprite",getAnimations((Image)imagelist.get("sprite"))[0]);
    }
    private void loadSprite_spriteList() {
        Botany botany = new Botany((Animation) animList.get("sprite"));
        botany.setEntityName("sprite");
        spriteList.put("sprite", botany);

        botany = new Botany((Animation) animList.get("NPC1"));
        botany.setEntityName("NPC1");
        spriteList.put("NPC1", new Botany((Animation) animList.get("NPC1")));

        botany = new door((Animation) animList.get("yellowdoor"));
        botany.setEntityName("yellowdoor");
        spriteList.put("yellowdoor", botany);

        botany = new door((Animation) animList.get("bluedoor"));
        botany.setEntityName("bluedoor");
        spriteList.put("bluedoor", botany);

        botany = new door((Animation) animList.get("reddoor"));
        botany.setEntityName("reddoor");
        spriteList.put("reddoor", botany);

        //创建钥匙对象
        botany = new Botany((Image) imagelist.get("yellowkey"));
        botany.setEntityName("yellowkey");
        spriteList.put("yellowkey", botany);

        botany = new Botany((Image) imagelist.get("bluekey"));
        botany.setEntityName("bluekey");
        spriteList.put("bluekey", botany);

        botany = new Botany((Image) imagelist.get("redkey"));
        botany.setEntityName("redkey");
        spriteList.put("redkey", botany);
        //创建钥匙对象
        botany = new Botany((Image) imagelist.get("redblood"));
        botany.setEntityName("redblood");
        spriteList.put("redblood", botany);

        botany = new Botany((Image) imagelist.get("blueblood"));
        botany.setEntityName("blueblood");
        spriteList.put("blueblood", botany);

        botany = new Botany((Image) imagelist.get("greenblood"));
        botany.setEntityName("greenblood");
        spriteList.put("greenblood", botany);

        //创建men对象
        botany = new Botany((Image) imagelist.get("upstair"));
        botany.setEntityName("upstair");
        spriteList.put("upstair", botany);

        botany = new Botany((Image) imagelist.get("downstair"));
        botany.setEntityName("downstair");
        spriteList.put("downstair", botany);
    }
    private void loadMap_maplist(){
        TileMap tileMap = null;
        try {
            tileMap = loadMap("images/maps/Map000.txt");
            tileMap.setMapname("map000");
            maplist.put("map000",tileMap);

            tileMap = loadMap("images/maps/Map001.txt");
            tileMap.setMapname("map001");
            maplist.put("map001",tileMap);

            //maplist.put("map002",loadMap("images/maps/Map002.txt"));
            //maplist.put("map003",loadMap("images/maps/Map003.txt"));
            //maplist.put("map004",loadMap("images/maps/Map004.txt"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void loadPlayer_player(){
        Image image = loadImage("images/Characters/刺客.png");
        playerSprite = new Player(getAnimations(image));
        playerSprite.setEntityName("player");
    }
}
