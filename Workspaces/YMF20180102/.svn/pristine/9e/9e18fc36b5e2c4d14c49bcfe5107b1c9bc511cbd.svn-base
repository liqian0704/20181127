package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.facade.DataHandlerFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/8/30
 * @Time: 上午11:45
 */
public class DataHandlerFacadeTest extends AnnotationContextAwareTest {
    @Autowired
    private DataHandlerFacade dataHandlerFacade;

    @Test
    public void testHandleQrCodeContent() {
        int i = dataHandlerFacade.handleQrCodeContent();
        System.out.println("count:" + i);
    }

    @Test
    public void testHandleDefaultShop() {
        int i = dataHandlerFacade.handleDefaultShop();
        System.out.println("count:" + i);
    }

    @Test
    public void testBindQrCodeToShop() {
        int i = dataHandlerFacade.bindQrCodeToShop();
        System.out.println("count:" + i);
    }

    //更新处理二维码ftp路径
    @Test
    public void testUpdateQrFtpUrl() {
        int i = dataHandlerFacade.updateQrFtpUrl();
        System.out.println("count:" + i);
    }

    //更新处理商户logo ftp路径
    @Test
    public void testUpdateCustomerLogoFtpUrl() {
        int i = dataHandlerFacade.updateCustomerLogoFtpUrl();
        System.out.println("count:" + i);
    }

    /////////////////测试环境 Begin/////////////////////////////
    @Test
    public void testHandleQrCodeContent2() {
        DataHandlerFacade service = RemoteServiceFactory.getService(DataHandlerFacade.class);
        int i = service.handleQrCodeContent();
        System.out.println("count:" + i);
    }

    @Test
    public void testHandleDefaultShop2() {
        DataHandlerFacade service = RemoteServiceFactory.getService(DataHandlerFacade.class);
        int i = service.handleDefaultShop();
        System.out.println("count:" + i);
    }

    @Test
    public void testBindQrCodeToShop2() {
        DataHandlerFacade service = RemoteServiceFactory.getService(DataHandlerFacade.class);
        int i = service.bindQrCodeToShop();
        System.out.println("count:" + i);
    }


    //更新处理二维码ftp路径
    @Test
    public void testUpdateQrFtpUrl2() {
        DataHandlerFacade service = RemoteServiceFactory.getService(DataHandlerFacade.class);
        int i = service.updateQrFtpUrl();
        System.out.println("count:" + i);
    }

    //更新处理商户logo ftp路径
    @Test
    public void testUpdateCustomerLogoFtpUrl2() {
        DataHandlerFacade service = RemoteServiceFactory.getService(DataHandlerFacade.class);
        int i = service.updateCustomerLogoFtpUrl();
        System.out.println("count:" + i);
    }

    /////////////////测试环境 End/////////////////////////////

    /////////////////内测环境 Begin/////////////////////////////
    //private static final String INNER_ADDRESS = "http://10.151.30.161:8081/ymf-hessian/hessian/DataHandlerFacade";
    private static final String INNER_ADDRESS = "http://106.120.186.94:8080/ymf-hessian/hessian/DataHandlerFacade";
    @Test
    public void testHandleQrCodeContent3() {
        DataHandlerFacade service = RemoteServiceFactory.getService(INNER_ADDRESS, RemotingProtocol.HESSIAN,
                DataHandlerFacade.class);
        int i = service.handleQrCodeContent();
        System.out.println("count:" + i);
    }

    @Test
    public void testHandleDefaultShop3() {
        DataHandlerFacade service = RemoteServiceFactory.getService(INNER_ADDRESS, RemotingProtocol.HESSIAN,
                DataHandlerFacade.class);
        int i = service.handleDefaultShop();
        System.out.println("count:" + i);
    }

    //循环处理 默认网点
    @Test
    public void testBatchHandleDefaultShop3() {
        System.out.println("开始 处理默认网点");
        DataHandlerFacade service = RemoteServiceFactory.getService(INNER_ADDRESS, RemotingProtocol.HESSIAN,
                DataHandlerFacade.class);

        for (int i = 0 ; i < 100 ;i++) {
            int count = service.handleDefaultShop();
            System.out.println("第" + i + "次 处理成功：" + count);
        }
        System.out.println("结束 处理默认网点");
    }

    @Test
    public void testBindQrCodeToShop3() {
        DataHandlerFacade service = RemoteServiceFactory.getService(INNER_ADDRESS, RemotingProtocol.HESSIAN,
                DataHandlerFacade.class);
        int i = service.bindQrCodeToShop();
        System.out.println("count:" + i);
    }


    //更新处理二维码ftp路径
    @Test
    public void testUpdateQrFtpUrl3() {
        DataHandlerFacade service = RemoteServiceFactory.getService(INNER_ADDRESS, RemotingProtocol.HESSIAN,
                DataHandlerFacade.class);
        int i = service.updateQrFtpUrl();
        System.out.println("count:" + i);
    }

    //更新处理商户logo ftp路径
    @Test
    public void testUpdateCustomerLogoFtpUrl3() {
        DataHandlerFacade service = RemoteServiceFactory.getService(INNER_ADDRESS, RemotingProtocol.HESSIAN,
                DataHandlerFacade.class);
        int i = service.updateCustomerLogoFtpUrl();
        System.out.println("count:" + i);
    }

    /////////////////内测环境 End/////////////////////////////
}
