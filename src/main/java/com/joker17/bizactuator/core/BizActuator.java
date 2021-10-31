package com.joker17.bizactuator.core;

import com.joker17.bizactuator.context.BizContext;
import com.joker17.bizactuator.resolver.BizResolver;

import java.util.Objects;

/**
 * biz执行器
 *
 * @param <P> biz parameter class
 * @param <C> used BizContext class
 * @param <R> return value class
 */
public class BizActuator<P, C extends BizContext, R> {

    /**
     * biz parameter
     */
    protected P bizParameter;

    /**
     * biz context
     */
    protected C bizContext;

    /**
     * biz resolver
     */
    protected BizResolver<P, C, R> bizResolver;

    /**
     * 构造方法
     *
     * @param bizParameter
     * @param bizContext
     * @param bizResolver
     */
    protected BizActuator(P bizParameter, C bizContext, BizResolver<P, C, R> bizResolver) {
        Objects.requireNonNull(bizParameter, "bizParameter must be not null!");
        Objects.requireNonNull(bizContext, "bizContext must be not null!");
        Objects.requireNonNull(bizResolver, "bizResolver must be not null!");
        this.bizParameter = bizParameter;
        this.bizContext = bizContext;
        this.bizResolver = bizResolver;
    }

    /**
     * 获取实例
     *
     * @param parameter
     * @param bizContext
     * @param bizResolver
     * @param <P>
     * @param <C>
     * @param <R>
     * @return
     */
    public static <P, C extends BizContext, R> BizActuator<P, C, R> of(P parameter, C bizContext, BizResolver<P, C, R> bizResolver) {
        return new BizActuator(parameter, bizContext, bizResolver);
    }


    /**
     * 执行并获取结果
     *
     * @return
     */
    public R execute() {
        //进行检查
        bizResolver.check(bizParameter, bizContext);
        //执行业务方法
        R result = bizResolver.execute(bizParameter, bizContext);
        //执行之后的方法
        bizResolver.executeAfter(bizParameter, bizContext, result);
        return result;
    }


}
