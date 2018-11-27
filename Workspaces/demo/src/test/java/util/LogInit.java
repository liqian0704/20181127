package util;

/**
 * Created by yp-tc-2646 on 17/1/23.
 */

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class LogInit {

    protected final Logger log=Logger.getLogger(getClass());

    public LogInit(){

        try {
           // PropertyConfigurator.configure(CommMethod.getRealath()+"conf/log4j.properties");
            PropertyConfigurator.configure("/Users/yp-tc-2646/Workspaces/demo/src/test/java/conf/log4j.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
