package process;

import component.AvlNode;

public interface Operation<T> {
    AvlNode insert(T x,AvlNode<T> avlNode);
    AvlNode remove(T x,AvlNode<T> avlNode);
    AvlNode search(T x,AvlNode<T> avlNode);
}
