
/**
 * 逆序建堆和排序
 * https://www.bilibili.com/video/BV1EQ4y1K7Rp?from=search&seid=17687214524139806780 视频动画
 * https://www.bilibili.com/video/BV1Eb41147dK?from=search&seid=17687214524139806780 大顶堆手写代码，UP主采用的是上面地址的倒序排法
 * 从最后一个非叶子节点倒着排所有叶子节点
 */
public class HeapSort {
    /**
     * 将i节点进行堆化并递归堆化涉及到的子节点
     * @param tree
     * @param totalN 总元素
     * @param currntI 当前节点i下标
     */
    private static void heapify(int tree[],int totalN,int currntI){
        //这里不需要递归出口，是因为其实currntI不会>=totalN,因为maxindex如果大于等于totalN时候下面比较压根不会赋值的
        //则maxIndex就一直是currntI，正常入参是不会报错的 手动输currntI才可能报错
        if (currntI>=totalN) {
            return;
        }

        int c1 = 2*currntI+1;
        int c2 = 2*currntI+2;
        int maxIndex = currntI;
        //节点及其子结点中选出最大值作为根
        if(c1<totalN && tree[c1]>tree[maxIndex]){
            maxIndex = c1;
        }
        if(c2<totalN && tree[c2]>tree[maxIndex]){
            maxIndex = c2;
        }
        //当子节点是最大值时候，需要递归再堆化这个子节点
        if(maxIndex!=currntI){
            swap(tree,maxIndex,currntI);
            heapify(tree,totalN,maxIndex);
        }
    }
    /**
     * 交换元素
     * @param tree
     * @param maxIndex
     * @param currntI
     */
    private static void swap(int[] tree, int maxIndex, int currntI) {
        int temp = tree[maxIndex];
        tree[maxIndex] = tree[currntI];
        tree[currntI] = temp;
    }
    /**
     * 大堆化的主入口
     * @param tree
     * @param totalN 总元素
     */
    private static void buildHeap(int tree[],int totalN){
        int lastNodeIdx = (totalN-2)/2;
        for (int nodeIdx = lastNodeIdx; nodeIdx >= 0; nodeIdx--) {
            heapify(tree,totalN,nodeIdx);
        }
    }
    private static void print(int tree[]){
        for (int i : tree) {
            System.out.println(i+" ");
        }
    }

    /**
     * 堆排序
     * 将转化好的堆排序输出，每次堆顶都是最大值
     * @param tree
     * @param totalN 总元素
     */
    private static void sortPrint(int[] tree, int totalN) {
        for (int i = totalN-1; i > 0; i--) {
            //树根和最后的叶子交换后，输出叶子后砍掉该叶子，并重建堆。以让下一次堆顶仍是最大值
            swap(tree,0,i);
            System.out.println(tree[i]);
            buildHeap(tree,i);
        }
    }
    public static void main(String[] args) {
        int tree[] = {5,8,1,4,2,3,9,10,7,14,16};
        buildHeap(tree,tree.length);
        sortPrint(tree,tree.length);
    }
}
