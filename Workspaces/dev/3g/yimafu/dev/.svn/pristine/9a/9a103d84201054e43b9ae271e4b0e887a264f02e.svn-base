package com.yeepay.g3.core.ymf.aop;

import com.yeepay.g3.facade.ymf.validator.annotations.MemCache;
import com.yeepay.g3.facade.ymf.validator.annotations.YMFCacheKey;
import com.yeepay.g3.utils.cache.remote.RemoteCacheUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by dongxulu on 17/5/1.
 */
@Aspect
@Component
public class MemCacheInterceptor {
    private static final String joinExecution = "execution(* com.yeepay.g3.core.ymf.service.impl.*.*(..))&&@annotation(cache)";

    private static final Logger log = LoggerFactory.getLogger(MemCacheInterceptor.class);

    @Around(value = "execution(* com.yeepay.g3.core.ymf.service.impl..*.*(..))&&@annotation(cache)")
    public Object cached(ProceedingJoinPoint pjp, MemCache cache) throws Throwable {
        String key=getCacheKey(pjp, cache);
        log.info("####this MemCacheInterceptor start###### YMFCacheKey "+key);
        Object value=RemoteCacheUtils.get(key);//从缓存获取数据
        log.info("###RemoteCacheUtils getValue: " + JSONUtils.toJsonString(value));
        if(value !=null){
            return value;
        }
        value = pjp.proceed();      //如果有数据,则直接返回
        //跳过缓存,到后端查询数据
        RemoteCacheUtils.put(key,value); //缓存不存在直接,则增加缓存
        return value;
    }

    /**
     * 获取缓存的key值
     * @param pjp
     * @param cache
     * @return
     */
    private String getCacheKey(ProceedingJoinPoint pjp,MemCache cache) {
        StringBuilder buf=new StringBuilder();
        buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
        if(cache.key().length()>0) {
            buf.append(".").append(cache.key());
        }
        Object[] args=pjp.getArgs();
        if(cache.cacheMode()== MemCache.CacheMode.DEFAULT) {
            Annotation[][] pas=((MethodSignature)pjp.getSignature()).getMethod().getParameterAnnotations();
            for(int i=0;i<pas.length;i++) {
                for(Annotation an:pas[i]) {
                    if(an instanceof YMFCacheKey) {
                        buf.append(".").append(args[i].toString());
                        break;
                    }
                }
            }
        }else if(cache.cacheMode()==MemCache.CacheMode.ALL) {
            for(Object arg:args) {
                buf.append(".").append(arg.toString());
            }
        }

        return buf.toString();
    }
}
