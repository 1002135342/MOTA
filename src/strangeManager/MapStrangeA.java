package strangeManager;
/***********************************************************************
 * Module:  MapStrangeA.java
 * Author:  Administrator
 * Purpose: Defines the Class MapStrangeA
 ***********************************************************************/

import java.awt.*;

import javax.swing.*;

/** @pdOid bf3ec66f-4507-493c-b1a5-79efd613e204 */
public class MapStrangeA implements MapStrange {

    public int aa = 0;
    /** @param c
     * @pdOid 857deebc-3a19-49cf-b36c-19061f528f7b */
    public String getImage(char c) {
        if(c == '1'){
            aa = 1;
            return "wall";
        }
        if(c == '2'){
            aa = 1;
            return "floor";
        }
        if(c == '3'){
            aa = 2;
            return "sprite";
        }
        if(c == 'A'){
            aa = 2;
            return "NPC1";
        }
        if(c == 'a'){
            aa = 2;
            return "NPC2";
        }
        if(c == '5'){
            aa = 2;
            return "yellowkey";
        }
        if(c == 'B'){
            aa = 2;
            return "bluekey";
        }
        if(c == 'b'){
            aa = 2;
            return "redkey";
        }
        if(c == '6'){
            aa = 2;
            return "redblood";
        }
        if(c == 'C'){
            aa = 2;
            return "blueblood";
        }
        if(c == 'c'){
            aa = 2;
            return "greenblood";
        }
        if(c == '7'){
            aa = 2;
            return "weapon";
        }
        if(c == 'D'){
            aa = 2;
            return "attack";
        }
        if(c == 'd'){
            aa = 2;
            return "defence";
        }
        if(c == '8'){
            aa = 2;
            return "book";
        }
        if(c == '9'){
            aa = 2;
            return "upstair";
        }
        if(c == 'E'){
            aa = 2;
            return "downstair";
        }
        if(c == '0'){
            aa = 2;
            return "yellowdoor";
        }
        if(c == 'F'){
            aa = 2;
            return "bluedoor";
        }
        if(c == 'f'){
            aa = 2;
            return "reddoor";
        }
        if(c == 'P'){
            aa = 3;
            return "player";
        }

        return null;
    }

    public Image loadImage(String name) {
        return new ImageIcon(name).getImage();
    }
}