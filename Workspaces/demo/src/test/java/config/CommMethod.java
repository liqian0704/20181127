package config;


import java.net.URISyntaxException;

/**
 * Created by yp-tc-2646 on 17/1/23.
 */
public class CommMethod {


    public static String getRealath() throws Exception {

        String path = "";
        try {
            path = DriverManage.class.getClassLoader().getResource("").toURI()
                    .getPath();

            try {
                path = CommMethod.convertFilePath(path);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return path;
    }
    public static String convertFilePath(String filePath){

        if(filePath.contains("bin")){

            filePath=filePath.replace("bin/","");
        }

        return filePath;
    }


}
