package stateManager.sprites;

/** ******************************************************************
 * Module:  Player.java
 * Author:  Administrator
 * Purpose: Defines the Class Player
 * *********************************************************************
 *
 *
 *
 * @pdOid c37a1331-486f-4891-b610-8de947e8e9ea */
public class Player extends Sprite {
    /** @pdOid 4ff39e35-02ab-48ab-8b0e-e0bb35cad2b2 */
    private int blood;
    /** @pdOid 919dbeb2-2cbc-40b0-80da-3227cb05e4d4 */
    private int defenses;
    /** @pdOid c18d6923-c98a-44d4-a6e9-72b8412c2873 */
    private int aggressivity;

    private int yellowKey;
    private int blueKey;
    private int redKey;

    /** @param nvalue
     * @pdOid 09b6a998-8ece-43bf-9a87-33afdc24e623 */
    public void setBlood(int nvalue) {
        blood+=nvalue;
    }

    /** @param nvalue
     * @pdOid 24f440ba-9cf1-4077-9184-07de87d0a614 */
    public void seteDfenses(int nvalue) {
        defenses+=nvalue;
    }

    /** @param nvalue
     * @pdOid fb74e3f2-0ded-4e0b-89f0-0a338cd45c6d */
    public void seteaAggressivity(int nvalue) {
        aggressivity+=nvalue;
    }

    /** @param left
     * @param right
     * @param deadLeft
     * @param deadRight
     * @pdOid 2a917728-a860-4911-94b6-3845e2292e93 */
    public Player(Animation left, Animation right, Animation deadLeft, Animation deadRight) {
        super(left, right, deadLeft, deadRight);
        this.yellowKey = 0;
        this.blueKey = 0;
        this.redKey = 0;

        this.blood = 1000;
        this.seteaAggressivity(10);
        this.seteDfenses(10);
    }

    /** @param animation
     * @pdOid f6a46992-e603-4cc6-b202-86618bc13d6a */
    public Player(Animation[] animation) {
        super(animation[0], animation[1], animation[2], animation[3]);
    }

    /** Sprite唤醒函数
     *
     *
     * @pdOid 9ed7c372-e7f3-4032-9327-9f4c33d12c54 */
    public void wakeUp() {
        // do nothing
    }

    /** 获取玩家的最大速度
     *
     * @return 玩家的最大速度 float
     *
     * @pdOid fc433a30-4c2d-4ddd-b546-771c02034a08 */
    public float getMaxSpeed() {
        return 0.3f;
    }

    /** 获取玩家游戏时间
     *
     * @return 玩家游戏时间 long
     *
     * @pdOid c27a2d20-15e8-4807-abbf-c9a837ead2e7 */
    public long getStateTime() {
        return super.getStateTime();
    }

    public int getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey += yellowKey;
    }

    public int getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey += blueKey;
    }

    public int getRedKey() {
        return redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey += redKey;
    }

}