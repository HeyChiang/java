package com.ddd.infracore.tools;

/**
 * 复制类的过程中回调
 *
 * @author JiangHao
 */
@FunctionalInterface
public interface CopyCallBack<T,R> {

    /**
     * 复制完成后回调
     *
     * @param t 被复制的类
     * @param r 返回对象
     */
    void callBack(T t,R r);
}
