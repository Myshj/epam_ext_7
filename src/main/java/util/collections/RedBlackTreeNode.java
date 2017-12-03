package util.collections;

class RedBlackTreeNode<E> {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RedBlackTreeNode<?> that = (RedBlackTreeNode<?>) o;

        if (color != that.color) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        return right != null ? right.equals(that.right) : that.right == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    private RedBlackTreeNodeColor color;
    private E value;
    private RedBlackTreeNode<E> parent;
    private RedBlackTreeNode<E> left;
    private RedBlackTreeNode<E> right;

    RedBlackTreeNode(
            RedBlackTreeNodeColor color,
            E value,
            RedBlackTreeNode<E> parent,
            RedBlackTreeNode<E> left,
            RedBlackTreeNode<E> right
    ) {

        this.color = color;
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    RedBlackTreeNodeColor color() {
        return color;
    }

    void setColor(RedBlackTreeNodeColor color) {
        this.color = color;
    }

    RedBlackTreeNode<E> parent() {
        return parent;
    }

    void setParent(RedBlackTreeNode<E> parent) {
        this.parent = parent;
    }

    RedBlackTreeNode<E> left() {
        return left;
    }

    void setLeft(RedBlackTreeNode<E> left) {
        this.left = left;
    }

    RedBlackTreeNode<E> right() {
        return right;
    }

    void setRight(RedBlackTreeNode<E> right) {
        this.right = right;
    }

    E value() {
        return value;
    }

    void setValue(E value) {
        this.value = value;
    }

    RedBlackTreeNode<E> grandparent(){
        return parent.parent;
    }

    void setParentColor(RedBlackTreeNodeColor color){
        parent.setColor(color);
    }

    boolean hasParent(){
        return parent != null;
    }

    boolean isRed(){
        return color == RedBlackTreeNodeColor.RED;
    }

    boolean isBlack(){
        return color == RedBlackTreeNodeColor.BLACK;
    }
}
