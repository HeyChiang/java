package chiang.java.sundry;

import lombok.Getter;

/**
 * @author JiangHao at 1/8/2022 3:55 PM
 */
@Getter
public enum ProductStatusEnum {

    /**
     * 每个枚举三个参数
     */
    SALE("0","00","000"),
    UNSALEABLE("1","11","111");

    private final String status;
    private final String two;
    private final String three;

    /**
     * 枚举类型的参数设定，然后依靠构造函数的顺序传值
     * 把two参数放到了第三的位置，打印结果就变成000而不是00
     */
    ProductStatusEnum(String status, String three, String two) {
        this.status = status;
        this.two = two;
        this.three = three;
    }

    public static void main(String[] args) {
        System.out.println(ProductStatusEnum.SALE.toString());
        System.out.println(ProductStatusEnum.SALE.getTwo());
        System.out.println(ProductStatusEnum.SALE.getStatus());
        System.out.println(ProductStatusEnum.SALE.getThree());
    }
}
