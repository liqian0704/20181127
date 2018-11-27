package com.yeepay.g3.facade.ymf.validator.annotations;

import java.lang.annotation.*;

/**
 * memcache注解
 * Created by dongxulu on 17/4/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MemCache {
    public enum CacheMode{
        DEFAULT,    //只有加了@CacheKey的参数,才加入key后缀中
        ALL;        //所有参数都加入key后缀
    }
     String key() default "";     //缓存key
     CacheMode cacheMode() default CacheMode.DEFAULT;       //key的后缀模式
     int expire() default 0;      //缓存多少秒,默认无限期

}
