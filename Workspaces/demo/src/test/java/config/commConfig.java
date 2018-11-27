package config;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public class commConfig {

    private Properties  propertie;

    private FileInputStream inputFile;
    Prop prop;

    public commConfig(String filePath){

       prop= PropKit.use(new File(filePath),"utf-8");

    }
    public String getValue(String key){

        if(prop.containsKey(key)){

            String value=prop.get(key);

            return value;

        }else {

            return "";
        }


    }


}
