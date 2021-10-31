package com.joker17.bizactuator.context;

/**
 * 复杂业务上下文
 *
 * @param <Q> query object class
 * @param <U> update object class
 * @param <O> other object class
 */
public class BizComplexContext<Q, U, O> implements BizContext {

    private static final long serialVersionUID = 8647696031437965754L;

    /**
     * 查询对象
     */
    private Q queryObject;

    /**
     * 更新对象
     */
    private U updateObject;

    /**
     * 其他对象
     */
    private O otherObject;

    public Q getQueryObject() {
        return queryObject;
    }

    public void setQueryObject(Q queryObject) {
        this.queryObject = queryObject;
    }

    public U getUpdateObject() {
        return updateObject;
    }

    public void setUpdateObject(U updateObject) {
        this.updateObject = updateObject;
    }

    public O getOtherObject() {
        return otherObject;
    }

    public void setOtherObject(O otherObject) {
        this.otherObject = otherObject;
    }
}
