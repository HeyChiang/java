package chiang.java.spi;

import java.util.ServiceLoader;

/**
 * 1.在本项目 META-INFO.services 里定义Animal接口，通过SPI加载实现接口的类，并打印。
 *
 * 2.将本项目打成jar，然后创建A项目引入。在A项目的INFO.services目录下创建文件，并写上A项目自己实现类的路径，
 * 调用 SpiDemoApplication.main也会将A项目的实现类打印。
 *
 * @author JiangHao
 */
public class SpiDemoApplication {

    public static void main(String[] args){
    	//会根据文件找到对应的实现类
        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);
        //执行实现类方法
        for (Animal animal : load) {
            animal.speak();
        }
    }
}