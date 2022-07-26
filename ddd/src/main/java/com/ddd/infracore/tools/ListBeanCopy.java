package com.ddd.infracore.tools;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 集合类的拷贝工具
 *
 * @author JiangHao
 */
public class ListBeanCopy {

    /**
     * 将sourceList集合拷贝到另一个泛型集合类中
     *
     * @param sourceList 被拷贝的集合
     * @param targetBean 通过此
     * @param <T>        被复制的对象
     * @param <R>        新的对象类型
     * @return 复制完成后的集合对象
     */
    public static <T, R> List<R> copy(List<T> sourceList, Supplier<R> targetBean) {
        return copy(sourceList,targetBean,null);
    }

    /**
     * 将sourceList集合拷贝到另一个泛型集合类中
     *
     * @param sourceList 被拷贝的集合
     * @param targetBean 通过此
     * @param callBack 对象复制完成时的回调
     * @param <T>        被复制的对象
     * @param <R>        新的对象类型
     * @return 复制完成后的集合对象
     */
    public static <T, R> List<R> copy(List<T> sourceList, Supplier<R> targetBean, CopyCallBack<T, R> callBack) {

        if (sourceList != null && targetBean != null) {

            List<R> list = new ArrayList<>(sourceList.size());
            sourceList.forEach((bean) -> {
                R r = targetBean.get();
                BeanUtils.copyProperties(bean, r);
                if (callBack != null) {
                    callBack.callBack(bean, r);
                }
                list.add(r);
            });

            return list;
        }

        return null;
    }
}
