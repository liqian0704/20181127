package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.UserAuthorityFacade;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/10/9
 * @Time: 下午3:37
 */
@Service
public class UserAuthorityFacadeImpl extends AbstractFacade implements UserAuthorityFacade {

    @Override
    public BaseResponse sendRegisterSms(SendRegisterSmsRequest reuqest) {
        return userAuthBiz.sendRegisterSms(reuqest);
    }

    @Override
    public BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest reuqest) {
        return userAuthBiz.verifyRegisterSms(reuqest);
    }

    @Override
    public LoginResponse login(LoginRequest reuqest) {
        return userAuthBiz.login(reuqest);
    }

    @Override
    public RefreshTKResponse refreshTK(RefreshTKRequest request) {
        return userAuthBiz.refreshTK(request);
    }


    @Override
    public BaseResponse logout(LogoutReuqest reuqest) {
        return userAuthBiz.logout(reuqest);
    }
}
