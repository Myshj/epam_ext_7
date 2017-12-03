package util.collections;

import java.util.Comparator;

public class RedBlackTree<E extends Comparable<E>> {
    private RedBlackTreeNode<E> empty = new RedBlackTreeNode<>(
            RedBlackTreeNodeColor.BLACK,
            null,
            null,
            null,
            null
    );

    private int size = 0;

    private RedBlackTreeNode<E> root = empty;

    private Comparator<E> comparator;

    public RedBlackTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public RedBlackTree() {
    }

    private RedBlackTreeNode<E> nodeWith(E value) {
        RedBlackTreeNode<E> current = root;
        while (!current.equals(empty)) {
            int comparisonResult = value.compareTo(current.value());
            if (comparisonResult == 0) {
                return current;
            } else {
                current = comparisonResult < 0 ?
                        current.left() :
                        current.right();
            }
        }
        return null;
    }

    private void delete(RedBlackTreeNode<E> toDelete) {

        if (toDelete == null || toDelete == empty) {
            return;
        }

        RedBlackTreeNode<E> temp_1;

        if (toDelete.left() == empty || toDelete.right() == empty) {
            temp_1 = toDelete;
        } else {
            temp_1 = toDelete.right();
            while ((temp_1.left() != empty)) {
                temp_1 = temp_1.left();
            }
        }

        RedBlackTreeNode<E> temp_2;
        if (temp_1.left() != empty) {
            temp_2 = temp_1.left();
        } else {
            temp_2 = temp_1.right();
        }

        temp_2.setParent(temp_1.parent());

        if (temp_1.hasParent()) {
            if (temp_1 == temp_1.parent().left()) {
                temp_1.parent().setLeft(temp_2);
            } else {
                temp_1.parent().setRight(temp_2);
            }
        } else {
            root = temp_2;
        }

        if (temp_1 != toDelete) {
            toDelete.setValue(temp_1.value());
        }

        if (temp_1.isBlack()) {
            rebalanceAfterDelete(temp_2);
        }
    }

    private void rebalanceAfterDelete(RedBlackTreeNode<E> current) {
        while (current != root && current.isBlack()) {
            if (current == current.parent().left()) {
                RedBlackTreeNode<E> sibling = current.parent().right();
                if (sibling.isRed()) {
                    sibling.setColor(RedBlackTreeNodeColor.BLACK);
                    current.setParentColor(RedBlackTreeNodeColor.RED);
                    leftRotate(current.parent());
                    sibling = current.parent().right();
                }

                if (sibling.left().isBlack() && sibling.right().isBlack()) {
                    sibling.setColor(RedBlackTreeNodeColor.RED);
                    current = current.parent();
                } else {
                    if (sibling.right().isBlack()) {
                        sibling.left().setColor(RedBlackTreeNodeColor.BLACK);
                        sibling.setColor(RedBlackTreeNodeColor.RED);
                        rightRotate(sibling);
                        sibling = current.parent().right();
                    }
                    sibling.setColor(current.parent().color());
                    current.setParentColor(RedBlackTreeNodeColor.BLACK);
                    sibling.right().setColor(RedBlackTreeNodeColor.BLACK);
                    leftRotate(current.parent());
                    current = root;
                }
            } else {
                RedBlackTreeNode<E> sibling = current.parent().left();
                if (sibling.isRed()) {
                    sibling.setColor(RedBlackTreeNodeColor.BLACK);
                    current.setParentColor(RedBlackTreeNodeColor.RED);
                    rightRotate(current.parent());
                    sibling = current.parent().left();
                }
                if (sibling.right().isBlack() &&
                        sibling.left().isBlack()) {
                    sibling.setColor(RedBlackTreeNodeColor.RED);
                    current = current.parent();
                } else {
                    if (sibling.left().isBlack()) {
                        sibling.right().setColor(RedBlackTreeNodeColor.BLACK);
                        sibling.setColor(RedBlackTreeNodeColor.RED);
                        leftRotate(sibling);
                        sibling = current.parent().left();
                    }
                    sibling.setColor(current.parent().color());
                    current.setParentColor(RedBlackTreeNodeColor.BLACK);
                    sibling.left().setColor(RedBlackTreeNodeColor.BLACK);
                    rightRotate(current.parent());
                    current = root;
                }
            }
        }
        current.setColor(RedBlackTreeNodeColor.BLACK);
    }

    private void insert(E value) {
        RedBlackTreeNode<E> current = root,
                parent = null;
        while (current != empty) {
            int comparisonResult = value.compareTo(current.value());
            parent = current;
            current = value.compareTo(current.value()) < 0 ?
                    current.left() :
                    current.right();
        }
        RedBlackTreeNode<E> newNode = new RedBlackTreeNode<>(
                RedBlackTreeNodeColor.RED,
                value,
                parent,
                empty,
                empty
        );
        if (parent != null) {
            int comparisonResult = value.compareTo(parent.value());
            if (comparisonResult < 0) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
        } else {
            root = newNode;
        }
        balanceAfterInserted(newNode);
    }

    private void balanceAfterInserted(RedBlackTreeNode<E> node) {
        while (
                node != root &&
                        node.parent().isRed()
                ) {
            if (node.parent() == node.grandparent().left()) {
                RedBlackTreeNode<E> uncle = node.grandparent().right();
                if (uncle.isRed()) {
                    node.setParentColor(RedBlackTreeNodeColor.BLACK);
                    uncle.setColor(RedBlackTreeNodeColor.BLACK);
                    node.grandparent().setColor(RedBlackTreeNodeColor.RED);
                    node = node.grandparent();
                } else {
                    if (node == node.parent().right()) {
                        node = node.parent();
                        leftRotate(node);
                    }
                    node.setParentColor(RedBlackTreeNodeColor.BLACK);
                    node.grandparent().setColor(RedBlackTreeNodeColor.RED);
                    rightRotate(node.grandparent());
                }
            } else {
                RedBlackTreeNode<E> uncle = node.grandparent().left();
                if (uncle.isRed()) {
                    node.setParentColor(RedBlackTreeNodeColor.BLACK);
                    uncle.setColor(RedBlackTreeNodeColor.BLACK);
                    node.grandparent().setColor(RedBlackTreeNodeColor.RED);
                    node = node.grandparent();
                } else {
                    if (node == node.parent().left()) {
                        node = node.parent();
                        rightRotate(node);
                    }
                    node.setParentColor(RedBlackTreeNodeColor.BLACK);
                    node.grandparent().setColor(RedBlackTreeNodeColor.RED);
                    leftRotate(node.grandparent());
                }
            }
        }
        root.setColor(RedBlackTreeNodeColor.BLACK);
    }

    public boolean contains(E value) {
        return nodeWith(value) != null;
    }

    public void add(E value) {
        if (!contains(value)) {
            insert(value);
            size++;
        }

    }

    public void remove(E value) {
        RedBlackTreeNode<E> toDelete = nodeWith(value);
        if (toDelete != null) {
            delete(toDelete);
            size--;
        }
    }

    private void leftRotate(RedBlackTreeNode<E> current) {
        RedBlackTreeNode<E> newRoot = current.right();
        current.setRight(newRoot.left());
        if (newRoot.left() != empty) {
            newRoot.left().setParent(current);
        }
        if (newRoot != empty) {
            newRoot.setParent(current.parent());
        }
        if (current.hasParent()) {
            if (current == current.parent().left()) {
                current.parent().setLeft(newRoot);
            } else {
                current.parent().setRight(newRoot);
            }
        } else {
            root = newRoot;
        }
        newRoot.setLeft(current);
        if (current != empty) {
            current.setParent(newRoot);
        }
    }

    private void rightRotate(RedBlackTreeNode<E> current) {
        RedBlackTreeNode<E> newRoot = current.left();
        current.setLeft(newRoot.right());
        if (newRoot.right() != empty) {
            newRoot.right().setParent(current);
        }

        if (newRoot != empty) {
            newRoot.setParent(current.parent());
        }
        if (current.hasParent()) {
            if (current == current.parent().right()) {
                current.parent().setRight(newRoot);
            } else {
                current.parent().setLeft(newRoot);
            }
        } else {
            root = newRoot;
        }

        newRoot.setRight(current);
        if (current != empty) {
            current.setParent(newRoot);
        }
    }
}
