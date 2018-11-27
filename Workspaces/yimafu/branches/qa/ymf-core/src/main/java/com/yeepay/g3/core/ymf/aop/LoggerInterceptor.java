package com.yeepay.g3.core.ymf.aop;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;

/**
 * 方法调用日志
 * 
 * 拦截facade调用
 *
 */
public class LoggerInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        long start = System.currentTimeMillis();

        try {
            Object result = invocation.proceed();
            logger(invocation, start, result, null);
            return result;
        } catch (Throwable e) {
            logger(invocation, start, null, e);
            throw e;
        }
    }

    private void logger(MethodInvocation invocation, long start, Object result, Throwable throwable) {
        if (invocation.getThis() == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        long eclipse = System.currentTimeMillis() - start;
        sb.append("time=");
        sb.append(eclipse);
        sb.append("ms, method=");
        sb.append(invocation.getThis().getClass().getName() + "." + invocation.getMethod().getName());
        sb.append(", ");
        sb.append(buildMessage(invocation.getArguments()));
        if (result != null) {
            sb.append(", result=");
            sb.append(reflectionToString(result));
        }
        if (throwable != null) {
            sb.append(", throws=");
            sb.append(throwable.getClass().getSimpleName());
            sb.append(", msg: " + throwable.getMessage());
        }

        logger.info(sb.toString());
    }

    private String buildMessage(Object[] arguments) {

        ArrayList<String> list = new ArrayList<String>();

        int i = 1;
        for (Object arg : arguments) {
            list.add("arg" + i + "=" + reflectionToString(arg));
            i++;
        }
        return StringUtils.join(list, ", ");
    }

    private String reflectionToString(Object obj) {
        // obj必须重写toString方法
        return obj.toString();
    }

}
