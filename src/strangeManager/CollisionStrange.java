package strangeManager;

/***********************************************************************
 * Module:  CollisionStrange.java
 * Author:  Administrator
 * Purpose: Defines the Interface CollisionStrange
 ***********************************************************************/

import mapManager.TileMap;
import stateManager.sprites.Botany;
import stateManager.sprites.Player;

/** @pdOid 7250d15c-f4cc-4fc0-ba14-49c28a500726 */
public interface CollisionStrange {
    /** @param currentmap
     * @pdOid 1c247063-a159-4c60-b85e-72d514837fff */
    void onCollison(TileMap currentmap, Player player, Botany botany);
    //void onCollison(TileMap currentmap, Player player);
}