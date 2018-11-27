package com.yeepay.g3.core.ymf.aop;

import com.yeepay.g3.core.ymf.entity.common.OperateLog;
import com.yeepay.g3.core.ymf.service.common.OperateLogService;
import com.yeepay.g3.core.ymf.support.EntityBuilder;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法调用日志
 * 
 * 拦截BOSS系统的service接口
 * 方法签名save*,update*,delete*,batch*
 * 参数必须包含{@link OperateEntity}
 */
public class ServiceLoggerInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ServiceLoggerInterceptor.class);

    private OperateLogService operateLogService;

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

    @SuppressWarnings("unchecked")
    private void logger(MethodInvocation invocation, long start, Object result, Throwable throwable) {
        if (invocation.getThis() == null) {
            return;
        }
        long eclipse = System.currentTimeMillis() - start;
        // 方法
        Method method = invocation.getMethod();
        Class clazz = invocation.getThis().getClass();
        // 参数
        try {
            Object[] objects = invocation.getArguments();
            for (Object obj : objects) {
                if (obj.getClass() == OperateEntity.class) { // 参数是OperateEntity才拦截
                    OperateEntity en = (OperateEntity) obj; // only for one parameter
                    String operator = en.getOperator();
                    String bizType = clazz.getSimpleName() + ":" + method.getName();
                    if (StringUtils.isEmpty(operator)) {
                        logger.info("操作人是空, 无法记录日志 调用栈方法=" + bizType);
                        continue;
                    }
                    OperateLog log = EntityBuilder.buildLog(en, bizType, eclipse);
                    operateLogService.save(log);
                } else if (obj.getClass() == List.class && // 是集合,并且集合元素是OperateEntity
                        obj.getClass().getComponentType() == OperateEntity.class) {
                    List<OperateEntity> entityList = (List<OperateEntity>) obj;
                    OperateEntity en = entityList.get(0); // only for one parameter
                    String operator = en.getOperator();
                    String bizType = clazz.getSimpleName() + ":" + method.getName();
                    if (StringUtils.isEmpty(operator)) {
                        logger.info("操作人是空, 无法记录日志 调用栈方法=" + bizType);
                        continue;
                    }
                    OperateLog log = EntityBuilder.buildLog(en, bizType, eclipse);
                    log.setDescription("批量操作,操作记录数:" + entityList.size());
                    operateLogService.save(log);
                }
            }
        } catch (Throwable t) {
            logger.error("持久化日志失败", t); // 不要影响主流程
        }
    }

    public void setOperateLogService(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }
}
