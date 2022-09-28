package chiang.java.spi;

public class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("汪汪汪！");
    }
}