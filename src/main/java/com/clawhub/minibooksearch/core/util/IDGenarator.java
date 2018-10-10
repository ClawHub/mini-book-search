/**************************************************************************************** 
           南京小视科技有限公司           
 ****************************************************************************************/
package com.clawhub.minibooksearch.core.util;

import java.util.UUID;

/**
 * <Description> <br>
 * 
 * @author zhangyuzhu<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017年10月19日 <br>
 */

public class IDGenarator {
    /**
     * 私有构造器
     */
    private IDGenarator() {
    }

    /**
     * Description: 生成32位uuid<br>
     * 
     * @author zhangyuzhu<br>
     * @taskId <br>
     * @return <br>
     */
    public static String getID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
