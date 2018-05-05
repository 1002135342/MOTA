/***********************************************************************
 * Module:  MapStrange.java
 * Author:  Administrator
 * Purpose: Defines the Interface MapStrange
 ***********************************************************************/
package strangeManager;
import java.awt.*;

/** 用于地图文件到地图图片的转化。
 *
 * @pdOid c7961e5c-4b27-4637-a245-d161a7c13ddf */
public interface MapStrange {

    static final int GUAIWU = 1;
    static final int WANJIA = 2;
    static final int JIANZHU = 3;
    /** 函数用于根据一个字符，获取指定图片对象
     *
     * @param c
     * @return 图片对象
     * @pdOid dbbe009c-bff5-4abe-bec0-b639ebcc2021 */
    String getImage(char c);

}