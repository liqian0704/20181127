package utility;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yp-tc-2646 on 17/9/29.
 */
public class MybatisUtils {


    public  static SqlSession openSession() throws IOException {

        String resource= "Accounting_core_mapper/mybatis-config.xml";
        InputStream in= Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(in);
        return sessionFactory.openSession();
    }
}
