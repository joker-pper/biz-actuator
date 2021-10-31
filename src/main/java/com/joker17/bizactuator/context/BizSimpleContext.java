package com.joker17.bizactuator.context;

/**
 * 简单业务上下文
 *
 * @param <T> data object class
 */
public class BizSimpleContext<T> implements BizContext {

    private static final long serialVersionUID = 171572168099278456L;

    /**
     * 数据对象
     */
    private T dataObject;

    public T getDataObject() {
        return dataObject;
    }

    public void setDataObject(T dataObject) {
        this.dataObject = dataObject;
    }
}
