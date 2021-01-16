import java.util.Date;

/**
 * 希尔排序
 * 画图结合算法容易理解
 */
public class ShellSort {
    static int[] data = new int[]{
            10,9,7,8,6,5,4,3,2,1,0
    };
    static int[] dataBig = new int[10000000];

    static {
       for (int i = 0; i < dataBig.length; i++) {
           dataBig[i] = dataBig.length-i-1;
       }
    }

    public static void main(String[] args) {
        shellSort(data);
        shellSortDesc(data);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                shellSort(dataBig);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                shellSort(dataBig);
            }
        });

        t1.setPriority(10);//10的优先级低于1
        t2.setPriority(10);

        t1.start();
        t2.start();

    }

    /**
     * d1 = n / 2 ;
     * dn = di / 2;
     *
     * di等于几表示数据一组有多少个，/2表示分为几组
     */
    private static void shellSort(int[] data) {
        long tB = new Date().getTime();
        int temp;//辅助保存变量
        int index = 0;//整体后移后的插入位置
        for (int dk = data.length/2; dk >= 1 ; dk = dk/2) {
            //对之后的每一个数字进行直接插入排序
            for (int i = dk+1; i < data.length; i++) { //i=dk+1,因为i是一个步长。所以dk+1是第二组的第一个元素
                    //因为存在步进dk的长度，所以只是组内比较
                if(data[i]<data[i-dk]){
                    temp = data[i];
                    //直接插入排序的过程，整体后移然后插入
                    for (int j = i-dk; j >= 0 && temp<data[j]; j -=dk) { //从i-dk开始，以i为步长整体后移，直到遇到比它大的数，说明要排在他后面
                            data[j +dk] = data[j];
                            index = j;
                    }
                    //插入
                    data[index] = temp;
                }
            }
        }
        System.out.println(new Date().getTime()-tB);
    }




    /**
     * d1 = n / 2 ;
     * dn = di / 2;
     *
     * di等于几表示数据一组有多少个，/2表示分为几组
     */
    private static void shellSortDesc(int[] data) {
        long tB = new Date().getTime();
        int temp;//辅助保存变量
        int index = 0;//整体后移后的插入位置
        for (int dk = data.length/2; dk >= 1 ; dk = dk/2) {
            //对之后的每一个数字进行直接插入排序
            for (int i = dk+1; i < data.length; i++) { //i=dk+1,因为i是一个步长。所以dk+1是第二组的第一个元素
                //因为存在步进dk的长度，所以只是组内比较
                if(data[i]>data[i-dk]){
                    temp = data[i];
                    //直接插入排序的过程，整体后移然后插入
                    for (int j = i-dk; j >= 0 && temp>data[j]; j -=dk) { //从i-dk开始，以i为步长整体后移，直到遇到比它大的数，说明要排在他后面
                        data[j +dk] = data[j];
                        index = j;
                    }
                    //插入
                    data[index] = temp;
                }
            }
        }
        System.out.println(new Date().getTime()-tB);
    }
}