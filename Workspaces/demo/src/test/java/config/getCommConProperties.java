package config;

/**
 * Created by yp-tc-2646 on 17/1/22.
 */
public class getCommConProperties {

  // static commConfig cC=new commConfig("/src/test/java/conf/env.properties");
   static  commConfig cC=new commConfig("/Users/yp-tc-2646/Workspaces/demo/src/test/java/conf/env.properties");


    public static String getPicDir() {

        String picdir = cC.getValue("picurl");

        return picdir;

    }
    public static String getDBdir(){

        String dbDir= cC.getValue("dbdir");

        return dbDir;
    }
    public static String getTestUrl(){

        String testUrl=cC.getValue("testurl");

        return testUrl;
    }

    public static String getBrowserType(){

        String browerType=cC.getValue("browertype");

        return browerType;

    }

    public static String getUname(){

        String uname=cC.getValue("name");

        return  uname;
    }

    public static String getPassword(){

        String pwd= cC.getValue("password");

        return pwd;
    }

    public static String getDbType(){

        String dbType=cC.getValue("dbtype");

        return dbType;
    }

    public static String getDbUname(){

        String dbUname= cC.getValue("dbusername");

        return dbUname;
    }

    public static String getDbPwd(){

        String dbPWD= cC.getValue("dbpassword");

        return dbPWD;
    }


}
