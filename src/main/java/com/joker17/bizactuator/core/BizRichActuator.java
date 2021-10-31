package com.joker17.bizactuator.core;


import com.joker17.bizactuator.context.BizContext;
import com.joker17.bizactuator.resolver.BizResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * biz顺序执行器
 *
 * @param <P> biz parameter class
 * @param <C> used BizContext class
 * @param <R> return value class
 */
public class BizRichActuator<P, C extends BizContext, R> extends BizActuator<P, C, R> {

    /**
     * 待执行函数列表
     */
    protected List<BiConsumer<P, C>> biConsumerList;

    /**
     * 构造函数
     *
     * @param bizParameter
     * @param bizContext
     * @param bizResolver
     */
    protected BizRichActuator(P bizParameter, C bizContext, BizResolver<P, C, R> bizResolver) {
        super(bizParameter, bizContext, bizResolver);
        this.biConsumerList = new ArrayList<>(16);
    }

    /**
     * 获取实例
     *
     * @param bizParameter
     * @param bizContext
     * @param bizResolver
     * @param <P>
     * @param <C>
     * @param <R>
     * @return
     */
    public static <P, C extends BizContext, R> BizRichActuator<P, C, R> of(P bizParameter, C bizContext, BizResolver<P, C, R> bizResolver) {
        return new BizRichActuator(bizParameter, bizContext, bizResolver);
    }

    /**
     * 添加到待执行函数列表中
     *
     * @param biConsumer
     * @return
     */
    public BizRichActuator<P, C, R> appendConsumer(BiConsumer<P, C> biConsumer) {
        Objects.requireNonNull(biConsumer, "biConsumer must be not null!");
        biConsumerList.add(biConsumer);
        return this;
    }

    /**
     * 执行待执行函数
     */
    private void executeAppendConsumerList() {
        if (!biConsumerList.isEmpty()) {
            biConsumerList.forEach(biConsumer -> biConsumer.accept(bizParameter, bizContext));
        }
    }

    @Override
    public R execute() {
        //进行检查
        bizResolver.check(bizParameter, bizContext);
        //执行待执行函数
        executeAppendConsumerList();
        //执行业务方法
        R result = bizResolver.execute(bizParameter, bizContext);
        //执行之后的方法
        bizResolver.executeAfter(bizParameter, bizContext, result);
        return result;
    }
}
