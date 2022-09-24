package chiang.algorithm.recursion.linked;

public class Sum {

    private static int sum(Integer[] array){
        return sum(array,0);
    }

    private static int sum(Integer[] array, int i) {
        if(i == array.length){
            return 0;
        }
        return array[i]+sum(array,i+1);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,1,1,1,1};
        System.out.println(sum(array));
    }
}
