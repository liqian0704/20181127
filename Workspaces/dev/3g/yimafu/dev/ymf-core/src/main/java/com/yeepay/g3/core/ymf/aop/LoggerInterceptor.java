package com.yeepay.g3.core.ymf.aop;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

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

    /**
     * aop输出日志
     * @param invocation 调用方法
     * @param start 开始时间
     * @param result 返回值
     * @param throwable 异常
     */
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
        sb.append(buildMessage("arg", invocation.getArguments()));
        if (result != null) {
            sb.append(", result=");
            sb.append(buildMessage("return", new Object[]{ result }));
        }
        if (throwable != null) {
            sb.append(", throws=");
            sb.append(throwable.getClass().getSimpleName());
            sb.append(", msg: " + throwable.getMessage());
        }

        logger.info(sb.toString());
    }

    /**
     * 打印日志
     * 集合size超过10的不打印详情
     * @param type 类型
     * @param arguments 参数
     * @return log string
     */
    private String buildMessage(String type, Object[] arguments) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 1;
        for (Object arg : arguments) {
            if (Collection.class.isAssignableFrom(arg.getClass())) {
                Collection c = (Collection) arg;
                if (c.size() > 10) { // list大于10个不需要打印出来
                    list.add(type + i + "=Collection.size():" + c.size());
                } else {
                    list.add(type + i + "=" + reflectionToString(arg));
                }
            } else if (Object[].class.isAssignableFrom(arg.getClass())) {
                Object[] c = (Object[]) arg;
                if (c.length > 10) { // array大于10个不需要打印出来
                    list.add(type + i + "=Object[].length:" + c.length);
                } else {
                    list.add(type + i + "=" + reflectionToString(arg));
                }
            } else {
                list.add(type + i + "=" + reflectionToString(arg));
            }
            i++;
        }
        return StringUtils.join(list, ", ");
    }

    private String reflectionToString(Object obj) {
        // obj必须重写toString方法
        return obj.toString();
    }

}
