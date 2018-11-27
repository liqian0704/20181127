package com.yeepay.g3.core.ymf.generator;

import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
import com.yeepay.g3.utils.persistence.GenericDao;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
public class MybatisDaoGenerator {

    String enter = "\r\n";
    String tab = "	";
    String space = " ";
    String dot = ".";
    String sep = ";";
    String basePath = "com.yeepay.g3.core.ymf.dao.order";

    private String daoFile = "src/main/java/com/yeepay/g3/core/ymf/dao/order/OrderDTODao.java";

    @Test
    public void execute() {

        Class clz = OrderDTO.class;

        try {
            RandomAccessFile file = new RandomAccessFile(daoFile, "rw");
            //生成dao接口
            String daoInterface = generateDaoInterface(clz);
            file.write(daoInterface.getBytes());
            System.out.println("over");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成dao实现
//        String daoImpl = generateDaoImpl(clz);
//
//        StringBuffer daoImplPath = new StringBuffer();
//        daoImplPath.append(filePath).append("dao\\impl\\")
//                .append(clz.getSimpleName().replace("Entity", ""))
//                .append("DaoImpl.java");
//        System.out.println(daoImplPath.toString());
//        f = new File(daoImplPath.toString());
//        FileUtil.writeFileSmart(daoImpl, f);
//
//        //生成service接口
//        String serviceInterface = generateServiceInterface(clz);
//
//        StringBuffer serviceInterfacePath = new StringBuffer();
//        serviceInterfacePath.append(filePath).append("service\\")
//                .append(clz.getSimpleName().replace("Entity", ""))
//                .append("Service.java");
//        System.out.println(serviceInterfacePath.toString());
//        f = new File(serviceInterfacePath.toString());
//        FileUtil.writeFileSmart(serviceInterface, f);
//
//        //生成service实现
//        String serviceImpl = generateServiceImpl(clz);
//
//        StringBuffer serviceImplPath = new StringBuffer();
//        serviceImplPath.append(filePath).append("service\\impl\\")
//                .append(clz.getSimpleName().replace("Entity", ""))
//                .append("ServiceImpl.java");
//        System.out.println(serviceImplPath.toString());
//        f = new File(serviceImplPath.toString());
//        FileUtil.writeFileSmart(serviceImpl, f);

    }

    /**
     * 生成dao接口
     *
     * @param clz
     * @return
     */
    private String generateDaoInterface(Class clz) {

        StringBuffer str = new StringBuffer("");

        //引包
        str.append("package").append(space).append(basePath).append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //引用类
        str.append("import").append(space).append(clz.getName()).append(sep).append(enter);
        str.append("import").append(space).append(GenericDao.class.getName()).append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //类头
        // 注解
        str.append("import org.springframework.stereotype.Repository;");
        str.append(enter);
        str.append("public interface ").append(clz.getSimpleName().replace("Entity", "")).append("Dao")
                .append(" extends GenericDao<").append(clz.getSimpleName()).append(">{").append(enter);
        //中间隔一行
        str.append(enter);
        //类结束
        str.append("}");

        System.out.println(str);

        return str.toString();
    }

    /**
     * 生成dao实现
     *
     * @param clz
     * @return
     */
    private String generateDaoImpl(Class clz) {

        StringBuffer str = new StringBuffer("");

        //引包
        str.append("package").append(space).append(basePath).append(dot)
                .append("dao.impl").append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //引用类
        str.append("import").append(space).append(basePath).append(dot).append("dao").append(dot)
                .append(clz.getSimpleName().replace("Entity", "")).append("Dao").append(sep).append(enter);
        str.append("import").append(space).append(clz.getName()).append(sep).append(enter);
        str.append("import").append(space).append(GenericDaoDefault.class.getName()).append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //类头
        str.append("public class ").append(clz.getSimpleName().replace("Entity", ""))
                .append("DaoImpl").append(enter).append(tab).append(tab)
                .append(" extends GenericDaoDefault<").append(clz.getSimpleName()).append(">").append(space)
                .append("implements").append(space)
                .append(clz.getSimpleName().replace("Entity", "")).append("Dao")
                .append("{").append(enter);
        //中间隔一行
        str.append(enter);
        //类结束
        str.append("}");

        System.out.println(str);

        return str.toString();
    }

    /**
     * 生成service接口
     *
     * @param clz
     * @return
     */
    private String generateServiceInterface(Class clz) {
        StringBuffer str = new StringBuffer("");

        //引包
        str.append("package").append(space).append(basePath).append(dot)
                .append("service").append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //引用类
        str.append("import").append(space).append(clz.getName()).append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //类头
        str.append("public interface ").append(clz.getSimpleName().replace("Entity", "")).append("Service")
                .append(" {").append(enter);
        //中间隔一行
        str.append(enter);
        //添加增加方法
        str.append(tab).append("public void addEntity(").append(clz.getSimpleName()).append(" entity);").append(enter);
        //中间隔一行
        str.append(enter);
        //添加修改方法
        str.append(tab).append("public void updateEntity(").append(clz.getSimpleName()).append(" entity);").append(enter);
        //中间隔一行
        str.append(enter);
        //添加删除方法
        str.append(tab).append("public void deleteById(Long id);").append(enter);
        //中间隔一行
        str.append(enter);
        //添加查询方法
        str.append(tab).append("public ").append(clz.getSimpleName()).append(" getById(Long id);").append(enter);
        //中间隔一行
        str.append(enter);
        //类结束
        str.append("}");

        System.out.println(str);

        return str.toString();
    }

    /**
     * 生成service实现
     *
     * @param clz
     * @return
     */
    private String generateServiceImpl(Class clz) {
        StringBuffer str = new StringBuffer("");

        //引包
        str.append("package").append(space).append(basePath).append(dot)
                .append("service").append(dot).append("impl").append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //引用类
        str.append("import javax.annotation.Resource;").append(enter).append(enter);
        str.append("import").append(space).append(basePath).append(dot).append("dao").append(dot)
                .append(getName(clz, Type.D)).append(sep).append(enter);
        str.append("import").append(space).append(clz.getName()).append(sep).append(enter);
        str.append("import").append(space).append(basePath).append(dot).append("service").append(dot)
                .append(getName(clz, Type.S)).append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //类头
        str.append("public class ").append(getName(clz, Type.SI))
                .append(" implements ").append(getName(clz, Type.S)).append(" {").append(enter);
        //中间隔一行
        str.append(enter);
        //实现对应的接口
        String daoRef = firstLower(getName(clz, Type.D));
        str.append(tab).append("@Resource").append(enter);
        str.append(tab).append("private ").append(getName(clz, Type.D))
                .append(space).append(daoRef).append(sep).append(enter);
        //中间隔一行
        str.append(enter);
        //添加增加方法
        str.append(tab).append("public void addEntity(").append(clz.getSimpleName()).append(" entity){").append(enter);
        str.append(tab).append(tab).append(daoRef).append(".add(entity);").append(enter);
        str.append(tab).append("}").append(enter);
        //中间隔一行
        str.append(enter);
        //添加修改方法
        str.append(tab).append("public void updateEntity(").append(clz.getSimpleName()).append(" entity){").append(enter);
        str.append(tab).append(tab).append("if(entity.getId()==null || entity.getId()==0 ").append(enter);
        str.append(tab).append(tab).append(tab).append(tab).append("|| entity.getVersion()==null){").append(enter);
        str.append(tab).append(tab).append(tab)
                .append("throw new PosBizException(PosBizExceptionEnum.INPUT_PARAM_IS_EMPTY);").append(enter);
        str.append(tab).append(tab).append("}").append(enter);
        str.append(tab).append(tab).append(daoRef).append(".update(entity);").append(enter);
        str.append(tab).append("}").append(enter);
        //中间隔一行
        str.append(enter);
        //添加删除方法
        str.append(tab).append("public void deleteById(Long id){").append(enter);
        str.append(tab).append(tab)
                .append("if(id==null) throw new PosBizException(PosBizExceptionEnum.INPUT_PARAM_IS_EMPTY);").append(enter);
        str.append(tab).append(tab).append(daoRef).append(".delete(id);").append(enter);
        str.append(tab).append("}").append(enter);
        //中间隔一行
        str.append(enter);
        //添加查询方法
        str.append(tab).append("public ").append(clz.getSimpleName()).append(" getById(Long id){").append(enter);
        str.append(tab).append(tab)
                .append("if(id==null) throw new PosBizException(PosBizExceptionEnum.INPUT_PARAM_IS_EMPTY);").append(enter);
        str.append(tab).append(tab).append("return ").append(daoRef).append(".get(id);").append(enter);
        str.append(tab).append("}").append(enter);
        //中间隔一行
        str.append(enter);
        //类结束
        str.append("}");

        System.out.println(str);

        return str.toString();
    }

    private String getName(Class clz, Type t) {
        String result = null;
        String prefix = clz.getSimpleName().replace("Entity", "");
        switch (t) {
            case D:
                result = prefix + "Dao";
                break;
            case DI:
                result = prefix + "DaoImpl";
                break;
            case S:
                result = prefix + "Service";
                break;
            case SI:
                result = prefix + "ServiceImpl";
                break;
            default:
                break;
        }
        return result;
    }

    private String firstLower(String orgStr) {
        String first = (orgStr.charAt(0) + "").toLowerCase();
        return first + orgStr.substring(1);
    }


    public enum Type {
        D,//Dao
        DI,//DaoImpl
        S,//Service
        SI;//ServiceImpl
    }
}
