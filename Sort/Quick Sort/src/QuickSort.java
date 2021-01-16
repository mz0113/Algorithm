/**
 * 快速排序是性能最好的排序算法，是冒泡排序的改进
 */
public class QuickSort {
    static int[] data = new int[]{
            10,9,7,8,6,5,4,3,2,1,0
    };
    public static void main(String[] args) {
        quickSort(data,0,data.length-1);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]+",");
        }
    }

    /**
     * 递归的过程 因为要处理左右两端
     * @param data
     */
    private static void quickSort(int[] data,int leftIndex,int rightIndex) {
        if(leftIndex>rightIndex){
            return;
        }
        int base;//基准数
        int bL = leftIndex;//保存的下表原始值，不做修改。递归调用会需要。因为要把left-right之间的数字拆分成两组递归
        int bR = rightIndex;

        base = data[leftIndex];
        System.out.println("\n进来时左指针"+leftIndex+" "+"进来时右指针"+rightIndex);
        while(leftIndex!=rightIndex){
            //先从右边移动（这一点顺序很重要）
            while (data[rightIndex]>=base && rightIndex>leftIndex){
                //循环，找到一个比基准值小的数字放在左边
                rightIndex--;
            }
            while (data[leftIndex]<=base && rightIndex>leftIndex){
                //循环，找到一个比基准值大的数字放在右边
                leftIndex++;
            }
            {
                //交换二者所值的元素
                System.out.println("左指针"+leftIndex+" "+"右指针"+rightIndex);
                int temp = data[rightIndex];
                data[rightIndex] = data[leftIndex];
                data[leftIndex] = temp;
            }
        }
        {
            //指针所指元素和基准数交换
            System.out.println("开始交换");
            data[bL] = data[rightIndex];
            data[rightIndex] = base;
            System.out.println("基准数:"+base);
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i]+",");
            }
            quickSort(data,bL,leftIndex-1);//这里的leftIndex = rightIndex ，是一个拆分数组的中间下表索引
            quickSort(data,leftIndex+1,bR);
        }
    }
}
