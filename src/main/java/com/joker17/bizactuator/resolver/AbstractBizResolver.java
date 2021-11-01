package com.joker17.bizactuator.resolver;

import com.joker17.bizactuator.context.BizContext;

/**
 * 抽象业务解析器
 *
 * @param <P> biz parameter class
 * @param <C> used BizContext class
 * @param <R> return value class
 */
public abstract class AbstractBizResolver<P, C extends BizContext, R> implements BizResolver<P, C, R> {

    @Override
    public void executeAfter(P bizParameter, C bizContext, R result) {
    }

}
