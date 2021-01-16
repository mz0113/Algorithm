package component;

import java.math.BigDecimal;

public class AvlNode<T> implements Comparable<T>{
    T element;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
        //这个构造方法其实只是插入元素时候的构造方法
    }

    public AvlNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(AvlNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public AvlNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(AvlNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    AvlNode<T> leftNode;
    AvlNode<T> rightNode;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    int height;//树的高度，某节点的高度是从该节点到一片树叶的最长路径的长。也就是左右子树中最高的为该子树的高。
                //深度，是从某节点到某个节点的唯一路径的长

    public AvlNode(T element) {
        this.element = element;
        height = 0;//这个构造方法其实是根的创建，根默认为0的
    }

    public AvlNode(T element, AvlNode<T> leftNode, AvlNode<T> rightNode) {
        this.element = element;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        height = 0;//这个构造方法其实是根的创建，根默认为0的
    }

    @Override
    public int compareTo(T o) {
        return new BigDecimal(this.element.toString()).compareTo(new BigDecimal(o.toString()));
    }
    客户端1改了Node和Main文件
            客户端1又改了Node和Main文件
}
