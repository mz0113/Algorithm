package process;

import component.AvlNode;

/**
 * https://blog.csdn.net/pacosonswjtu/article/details/50522677
 * @param <T>
 */
public class AvlOperation<T> implements Operation<T>{
    private static final int Allowed_Balance = 1;

    @Override
    public AvlNode insert(T x, AvlNode<T> avlNode) {
        //递归结束条件，如果传递来的avlNode（左右子树的根）是空的，则直接插入，否则继续比较
        if(avlNode==null){
            return new AvlNode<T>(x);
        }

        int compareResult = avlNode.compareTo(x);

        if(compareResult>0){
            //新插入的节点比子树根小，插入在左子树
            avlNode.setLeftNode(insert(x,avlNode.getLeftNode()));
        }else if(compareResult<0) {
            //插入在右子树
            avlNode.setRightNode(insert(x, avlNode.getRightNode()));
        }else{
            //duplcate value;do nothing
        }
        return balance(avlNode);//设置height
    }

    @Override
    public AvlNode remove(T x, AvlNode<T> avlNode) {
        if(avlNode==null){
            return avlNode;
        }
        int compareResult = avlNode.compareTo(x);

        if(compareResult>0){
            //在左边
            avlNode.setLeftNode(remove(x,avlNode.getLeftNode()));
        }else if(compareResult<0){
            avlNode.setRightNode(remove(x,avlNode.getRightNode()));
        }else if(avlNode.getRightNode()!=null && avlNode.getLeftNode()!=null){
            //找到了该元素，然后如果是双孩子
            //用右子树的最小的数据替换要删除的节点的值，并且递归的删除该最小的数据的节点
            avlNode.setElement(getMinElemnt(avlNode).getElement());
            avlNode.setRightNode(remove(avlNode.getElement(),avlNode.getRightNode()));//替换值后再把该节点右子树下的该值的节点删除掉
        }else{
            //单孩子
            //当前avlNode是要被删除的节点,则只需要改变引用的地址即可。
            avlNode = avlNode.getRightNode()==null?avlNode.getLeftNode():avlNode.getRightNode();
        }
        return balance(avlNode);
    }

    @Override
    public AvlNode search(T x, AvlNode<T> avlNode) {
        if(avlNode==null){
            return avlNode;
        }

        int compareResult = avlNode.compareTo(x);

        if(compareResult>0){
            //x小，在avlNode的左边
            return search(x,avlNode.getLeftNode());
        }else if(compareResult>0){
            return search(x,avlNode.getRightNode());
        }else{
            return avlNode;
        }
    }

    private AvlNode<T> balance(AvlNode<T> avlNode){
        if(avlNode==null){
            return avlNode;
        }

        if(getHeight(avlNode.getLeftNode())-getHeight(avlNode.getRightNode())>Allowed_Balance){
            if(getHeight(avlNode.getLeftNode().getLeftNode())>=getHeight(avlNode.getLeftNode().getRightNode())){
                //左左，如果删除操作造成不平衡且节点左右高度相等，又因为子树根左高于右，则默认为左左旋转 书上P94
                avlNode = rotateLL(avlNode);
            }else{
                //插入节点在左子树的右子树
                avlNode = rotateLR(avlNode);
            }
        }
        else if(getHeight(avlNode.getRightNode())-getHeight(avlNode.getLeftNode())>Allowed_Balance){
            if(getHeight(avlNode.getRightNode().getRightNode())>=getHeight(avlNode.getLeftNode().getLeftNode())){
                avlNode = rotateRR(avlNode);
            }else{
                avlNode = rotateRL(avlNode);
            }
        }
        avlNode.setHeight(Math.max(getHeight(avlNode.getLeftNode()),getHeight(avlNode.getRightNode()))+1);
        return avlNode;
    }

    private AvlNode<T> rotateLR(AvlNode<T> avlNode) {
        //左右型转变为LL型,对孙子节点为轴单旋转为LL型
        avlNode.setLeftNode(rotateRR(avlNode.getLeftNode()));
        return rotateLL(avlNode);
    }
    private AvlNode<T> rotateLL(AvlNode<T> avlNode) {
        AvlNode lNode = avlNode.getLeftNode();
        avlNode.setLeftNode(lNode.getRightNode());
        lNode.setRightNode(avlNode);
        avlNode.setHeight(Math.max(getHeight(avlNode.getLeftNode()),getHeight(avlNode.getRightNode()))+1);
        lNode.setHeight(Math.max( getHeight(lNode.getLeftNode()),getHeight(lNode.getRightNode()) )+1);
        return lNode;//新的子树根，avlNode已经变成lNode的右子树了
    }
    private AvlNode<T> rotateRL(AvlNode<T> avlNode) {
        //左右型转变为RR型,对孙子节点为轴单旋转为RR型
        avlNode.setRightNode(rotateLL(avlNode.getRightNode()));
        return rotateRR(avlNode);
    }
    private AvlNode<T> rotateRR(AvlNode<T> avlNode) {
        AvlNode rNode = avlNode.getRightNode();
        avlNode.setRightNode(rNode.getLeftNode());
        rNode.setLeftNode(avlNode);
        //单旋转结束后，需要重新设置AVL相对高度
        avlNode.setHeight(Math.max(getHeight(avlNode.getLeftNode()),getHeight(avlNode.getRightNode()))+1);
        rNode.setHeight(Math.max( getHeight(rNode.getLeftNode()),getHeight(rNode.getRightNode()) )+1);
        return rNode;//新的子树根，avlNode已经变成rNode的左子树了
    }

    private int getHeight(AvlNode<T> avlNode){
        return avlNode==null?-1:avlNode.getHeight();//如果节点为Null，返回-1
    }

    private AvlNode<T> getMinElemnt(AvlNode<T> avlNode){
        //一直取左子树即可
        if(avlNode==null){
            return null;
        }else if(avlNode.getLeftNode()==null){//如果一个节点的左子树是空的，那么显然它就是最小的了
            return avlNode;
        }
        return getMinElemnt(avlNode.getLeftNode());
    }
}
