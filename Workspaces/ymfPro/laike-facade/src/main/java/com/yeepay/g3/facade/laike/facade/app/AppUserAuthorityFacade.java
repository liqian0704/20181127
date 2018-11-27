package com.yeepay.g3.facade.laike.facade.app;


import com.yeepay.g3.facade.laike.dto.*;

/**
 * @Description: 用户鉴权接口
 * @Author: zhaoyu.cui
 * @Date: 16/10/9
 * @Time: 下午3:27
 */
public interface AppUserAuthorityFacade {

    /**
     * 发送注册短信验证码
     *
     * @param reuqest
     * @return
     */
    BaseResponse sendRegisterSms(SendRegisterSmsRequest reuqest);

    /**
     * 验证注册短信验证码并且注册
     *
     * @param reuqest
     * @return
     */
    BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest reuqest);

    /**
     * 用户登陆
     *
     * @param reuqest
     * @return
     */
    LoginResponse login(LoginRequest reuqest);

    /**
     * 刷新tk
     *
     * @return
     */
    RefreshTKResponse refreshTK(RefreshTKRequest request);

    /**
     * 注销
     *
     * @param reuqest
     * @return
     */
    BaseResponse logout(LogoutReuqest reuqest);
}
