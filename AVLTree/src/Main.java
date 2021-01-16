import component.AvlNode;
import process.AvlOperation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AvlNode<Integer> avlNodeRoot = new AvlNode(6);

        AvlOperation avlOperation = new AvlOperation();
        avlNodeRoot = avlOperation.insert(4,avlNodeRoot);
        avlNodeRoot = avlOperation.insert(5,avlNodeRoot);
/*        avlNodeRoot = avlOperation.insert(2,avlNodeRoot);
        avlNodeRoot = avlOperation.insert(1,avlNodeRoot);
        avlNodeRoot = avlOperation.insert(0,avlNodeRoot);*/

        //System.out.println(avlOperation.search(4,avlNodeRoot)==null?"":avlOperation.search(4,avlNodeRoot).getElement());
        avlNodeRoot = avlOperation.remove(4,avlNodeRoot);
    }
}
