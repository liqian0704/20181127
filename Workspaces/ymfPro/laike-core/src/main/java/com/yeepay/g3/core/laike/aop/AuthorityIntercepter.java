package com.yeepay.g3.core.laike.aop;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.FunctionEntity;
import com.yeepay.g3.core.laike.entity.RoleFunctionEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.service.AccountOpenService;
import com.yeepay.g3.core.laike.service.FunctionService;
import com.yeepay.g3.core.laike.service.RoleFuncService;
import com.yeepay.g3.core.laike.service.UserService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum;
import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description:权限拦截
 * Author: jiawen.huang
 * Date: 16/11/24
 * Time: 11:21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
@Aspect
@Order(4)
public class AuthorityIntercepter {

    @Autowired
    private FunctionService functionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountOpenService accountOpenService;

    @Autowired
    private RoleFuncService roleFuncService;

    @Around(value = "execution(* com.yeepay.g3.facade.laike.facade.app..*.*(..))")
    public Object facadeAround(ProceedingJoinPoint joinPoint) throws Throwable {

        if ((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_PERMISSION_SWITCH)) {
            UserEntity userEntity = null;
            AccountOpenEntity accountOpenEntity = null;

            //1.获取当前请求的方法
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String methodName = methodSignature.getDeclaringTypeName() + methodSignature.getName();
            Object[] args = joinPoint.getArgs();
            String memberNo = ((BaseRequest) args[0]).getMemberNo();

            //2.查询对应方法和级别
            FunctionEntity functionEntity = functionService.findActiveByCode(methodName);//catch not exist

            if (functionEntity.checkLevel(FuncLevelEnum.LOGIN)) {
                userEntity = userService.findRegisterByMember(memberNo);//catch exp
            }
            if (functionEntity.checkLevel(FuncLevelEnum.PAYABLE) || functionEntity.checkLevel(FuncLevelEnum.WITHDRAW)) {
                accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
                if (functionEntity.checkLevel(FuncLevelEnum.WITHDRAW)) {
                    if (!accountOpenEntity.getOpenStatus().equals(OpenStatusEnum.SUCCESS)) {
                        throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
                    }
                }
            }
            //3.确定角色
            RoleFunctionEntity roleFunctionEntity = roleFuncService
                    .findByRoleAndFunId(userEntity.getRole().name(), functionEntity.getId());
            if (!roleFunctionEntity.getAvailable()) {
                throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);//
            }

            ConstantUtil.THREAD_LOCAL_USER.set(userEntity);
            ConstantUtil.THREAD_LOCAL_ACCOUNT.set(accountOpenEntity);
        }
        return joinPoint.proceed();
    }
}
