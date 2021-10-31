package com.joker17.bizactuator.resolver;

import com.joker17.bizactuator.context.BizContext;

/**
 * 业务解析器 (用于专门处理某个业务场景)
 *
 * @param <P> biz parameter class
 * @param <C> used BizContext class
 * @param <R> return value class
 */
public abstract class BizResolver<P, C extends BizContext, R> {

    /**
     * 检查参数
     *
     * @param bizParameter
     * @param bizContext
     */
    public abstract void check(P bizParameter, C bizContext);

    /**
     * 执行并返回结果
     *
     * @param bizParameter
     * @param bizContext
     * @return
     */
    public abstract R execute(P bizParameter, C bizContext);

    /**
     * 进行处理execute执行后的逻辑(可按需重写)
     *
     * @param bizParameter
     * @param bizContext
     * @param result
     */
    public void executeAfter(P bizParameter, C bizContext, R result) {
    }

}
