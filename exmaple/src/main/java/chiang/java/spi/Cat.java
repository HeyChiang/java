package chiang.java.spi;

public class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("喵喵喵！");
    }
}