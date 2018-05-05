package strangeManager;

/***********************************************************************
 * Module:  CollisionStrangeA.java
 * Author:  Administrator
 * Purpose: Defines the Class CollisionStrangeA
 ***********************************************************************/

import mapManager.TileMap;
import stateManager.sprites.Botany;
import stateManager.sprites.Entity;
import stateManager.sprites.Player;
import stateManager.sprites.Sprite;

/** @pdOid b2caed09-0c3c-4c01-8bac-76b5418152c2 */
public class CollisionStrangeA implements CollisionStrange {

    public boolean isChangeStair;

    public CollisionStrangeA(){
        this.isChangeStair = false;
    }
    @Override
    public void onCollison(TileMap currentmap, Player player, Botany botany) {


        //撞到怪物
        if(botany.getEntityName() == "sprite") {
            //调用战斗函数,返回战斗结果
            if(true) {
                botany.setState(Entity.STATE_DEAD);
            }
            else {
                player.setState(Sprite.STATE_DYING);
                //游戏结束
            }
        }
        //判定NPC
        if(botany.getEntityName() == "NPC1") {

        }
        if(botany.getEntityName() == "NPC2") {

        }

        //判定钥匙
        if(botany.getEntityName() == "yellowkey") { //黄
            botany.setState(Entity.STATE_DEAD);
            player.setYellowKey(1);
        }
        if(botany.getEntityName() == "bluekey") {//蓝
            botany.setState(Entity.STATE_DEAD);
            player.setBlueKey(1);
        }
        if(botany.getEntityName() == "redkey") {//红
            botany.setState(Entity.STATE_DEAD);
            player.setRedKey(1);
        }

        //判定血瓶
        if(botany.getEntityName() == "redblood") {//红
            ((Player)currentmap.getPlayer()).setBlood(20);
        }
        if(botany.getEntityName() == "blueblood") {//蓝
            ((Player)currentmap.getPlayer()).setBlood(30);
        }
        if(botany.getEntityName() == "greenblood") {//绿
            ((Player)currentmap.getPlayer()).setBlood(40);
        }

        //撞到装备
        if(botany.getEntityName() == "weapon") {
            ((Player)currentmap.getPlayer()).seteaAggressivity(100);
            botany.setState(Entity.STATE_DEAD);
        }

        if(botany.getEntityName() == "attack") {//盾
            ((Player)currentmap.getPlayer()).seteDfenses(200);
            botany.setState(Entity.STATE_DEAD);
        }

        if(botany.getEntityName() == "defence") {
            ((Player)currentmap.getPlayer()).seteDfenses(200);
            botany.setState(Entity.STATE_DEAD);
        }
        // 特殊物体
        if(botany.getEntityName() == "book") {

            botany.setState(Entity.STATE_DEAD);
        }


        //门
        if(botany.getEntityName() == "yellowdoor") {
            if(player.getYellowKey() > 0) {
                player.setYellowKey(-1);
                botany.setState(Entity.STATE_DEAD);
            }
            else {
                player.setVelocityX(0);
                player.setVelocityY(0);
            }


        }

        if(botany.getEntityName() == "bluedoor") {
            if(player.getBlueKey() > 0) {
                player.setBlueKey(-1);
                botany.setState(Entity.STATE_DEAD);
            }
            else {
                player.setVelocityX(0);
                player.setVelocityY(0);
            }
        }

        if(botany.getEntityName() == "reddoor") {
            if(player.getRedKey() > 0) {
                player.setRedKey(-1);
                botany.setState(Entity.STATE_DEAD);
            }
            else {
                player.setVelocityX(0);
                player.setVelocityY(0);
            }
        }

        //上下楼梯
        if(botany.getEntityName() == "upstair") {
            if(currentmap.getMapname() == "map000"){
                currentmap.setNextmapname("map001");
            }

            if(currentmap.getMapname() == "map001")
                currentmap.setNextmapname("map002");

            if(currentmap.getMapname() == "map001" && false)
                currentmap.setNextmapname("map003");

            if(currentmap.getMapname() == "map003")
                currentmap.setNextmapname("map004");

            isChangeStair = true;
        }

        if(botany.getEntityName() == "downstair") {
            if(currentmap.getMapname() == "map001")
                currentmap.setNextmapname("map000");

            isChangeStair = true;
        }
    }

}