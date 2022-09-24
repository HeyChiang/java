package chiang.java.lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * 筛选功率列表的数据，得到自己想要的
 * @author JiangHao at 12/9/2021 7:47 PM
 */
public class FilterLambda {

    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("55555");
        strList.add("1");
        strList.add("22");
        strList.add("333");
        strList.add("4444");

        // 使用断言可以添加多个条件去筛选
        Predicate<String> limit = s -> s.length() >4;
        Predicate<String> limitOr = s -> s.equals("22");

        strList.stream().filter(limit.or(limitOr)).forEach(System.out::println);
    }
}
