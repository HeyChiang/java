package com.chiang.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambda {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("zhangsan",15,"上海"));
        personList.add(new Person("lisi",2,"上海"));
        personList.add(new Person("wangwu",18,"上海"));
        personList.add(new Person("zhaoliu",30,"上海"));

        // 常规
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // 构造方法引用
        personList.sort(Comparator.comparingInt(Person::getAge));

        System.out.println(personList);

        IPerson iPerson = Person::new;
        iPerson.initPerson("1",2,"3");
    }

    interface IPerson{
        void initPerson(String name, int age, String address);
    }

    static class Person{
        public Person(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        String name;
        int age;
        String address;

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
