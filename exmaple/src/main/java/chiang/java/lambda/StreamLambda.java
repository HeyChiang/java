package chiang.java.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通过map、filter、peek对数据进行操作并打印
 * @author jianghao
 */
public class StreamLambda {

    static ArrayList<String> strList = new ArrayList<>();
    public static void main(String[] args) {
        initData();

        // 筛选名称长度大于7的
        List<String> filterList = strList.stream().filter((s -> s.length() > 7)).collect(Collectors.toList());
        System.out.println("过滤长度>7的字符串："+filterList);

        // 对字符串进行操作，拿到操作后的结果
        List<String> mapList = strList.stream().map(s -> s + "--").collect(Collectors.toList());
        System.out.println("拿到操作后的结果："+mapList);

        // 列表所有的数-100
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList = list.stream()
                .peek((x) -> System.out.println( x=x-100))
                .collect(Collectors.toList());
        // peek的操作不会影响集合的数据
        newList.forEach(System.out::println);

        //数据统计
        boysAndGirls(ComparatorLambda.personList);

    }

    public static void boysAndGirls(List<ComparatorLambda.Person> persons) {
        Map<Integer, Integer> result = persons.parallelStream().filter(p -> p.getAge()>=1 && p.getAge()<=99).
                collect(
                        Collectors.groupingBy(ComparatorLambda.Person::getSex, Collectors.summingInt(p->1))
                );
        System.out.print("boysAndGirls result is " + result.toString());
    }

    private static void initData() {
        ComparatorLambda.initData();
        strList.add("zhangsan");
        strList.add("lisi");
        strList.add("wangwu");
        strList.add("zhaoliu");
        strList.add("qianqi");
    }
}
