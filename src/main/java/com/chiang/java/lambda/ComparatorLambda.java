package com.chiang.java.lambda;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 对集合中的某个属性进行大小比较，默认是从小到大的
 */
public class ComparatorLambda {

    public static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        initData();

        // 常规
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(personList);

        // 构造方法引用，从小到达排序
        personList.sort(Comparator.comparingInt(Person::getAge));

        System.out.println(personList);

    }

    public static void initData() {
        personList.add(new Person("zhangsan",15,1,"上海"));
        personList.add(new Person("lisi",2,1,"上海"));
        personList.add(new Person("wangwu",18,0,"上海"));
        personList.add(new Person("zhaoliu",30,0,"上海"));
    }

    static class Person{
        public Person(String name, int age,int sex, String address) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.address = address;
        }

        String name;
        int age;
        int sex;
        String address;

        public int getSex(){
            return sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}
