package cases;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by yp-tc-2646 on 17/1/29.
 */
public class test {
    Properties properties=new Properties();
    @Test
    public void t(){
        String filepath1="";
        String filepath2="";
        String filepath4="";

        String f="/Users/yp-tc-2646/Workspaces/demo/src/test/java/conf/env.properties";
        try {
//           filepath1= test.class.getClassLoader().getResource("conf/env.properties").toURI().getPath();
            filepath1= test.class.getClassLoader().getResource("").toURI().getPath();
            filepath2=this.getClass().getResource("").getPath();
           // FileInputStream fileInputStream=new FileInputStream("");
            //InputStream fileInputStream1=Properties.class.getResourceAsStream("env.properties");
            InputStream in = ClassLoader.getSystemResourceAsStream("/Users/yp-tc-2646/Workspaces/demo/src/test/java/conf/env.properties");
            properties.load(in);
           // PropKit.use(filePath,"utf-8");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------");
       System.out.println("paht1:="+filepath1);
        System.out.println("path2:="+filepath2);
        System.out.println("Path3:="+System.getProperty("user.dir"));
        System.out.println();

       // System.out.println(System.getProperty());

    }
}
