public abstract class node<T> implements Key<T>{
    private node<T> p;
    private node<T> left;
    private node<T> middle;
    private node<T> right;
    private T key;
    private float skey;
    private T id;
    private int size=0;

    public void setP(node<T> p) {
        this.p = p;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSkey(float skey) {
        this.skey = skey;
    }

    public node<T> getP() {
        return p;
    }

    public int getSize() {
        return size;
    }

    public float getSkey() {
        return this.skey;
    }


    public void setp(node<T> p) {
        this.p = p;
    }

    public node(T key) {
        this.left = null;
        this.middle = null;
        this.p = null;
        this.right = null;
        this.key = key;
        this.skey = (float) -1;
    }


    public node(T key, float skey) {
        this.left = null;
        this.middle = null;
        this.p = null;
        this.right = null;
        this.key = key;
        this.skey = skey;
    }


    public void setLeft(node<T> left) {
        this.left = left;
    }

    public void setMiddle(node<T> middle) {
        this.middle = middle;
    }

    public void setRight(node<T> right) {
        this.right = right;
    }

    public node<T> getp() {
        return p;
    }

    public node<T> getLeft() {
        return left;
    }
    public node<T> getMiddle() {
        return middle;
    }

    public node<T> getRight() {
        return right;
    }


    @Override
    public T getKey() {
        return this.key;
    }
    @Override
    public float getSecondaryKey() {
        return this.skey;
    }

    @Override
    public boolean isSmaller(T other) {
        return false;
    }

    @Override
    public void setKey(T k) {
        this.key = k;
    }
}

