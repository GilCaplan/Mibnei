public class node extends RunnerID implements Key{
    private node p;
    private node left;
    private node middle;
    private node right;
    private int key;
    private int id;
    private int size=0;

    public void setP(node p) {
        this.p = p;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSkey(int skey) {
        this.skey = skey;
    }

    public node getP() {
        return p;
    }

    public int getSize() {
        return size;
    }

    public int getSkey() {
        return skey;
    }

    private int skey;

    public void setp(node p) {
        this.p = p;
    }

    public node(int key) {
        this.left = null;
        this.middle = null;
        this.p = null;
        this.right = null;
        this.key = key;
        this.skey = 0;
    }


    public node(int key, int skey) {
        this.left = null;
        this.middle = null;
        this.p = null;
        this.right = null;
        this.key = key;
        this.skey = skey;
    }


    public void setLeft(node left) {
        this.left = left;
    }

    public void setMiddle(node middle) {
        this.middle = middle;
    }

    public void setRight(node right) {
        this.right = right;
    }

    public node getp() {
        return p;
    }

    public node getLeft() {
        return left;
    }
    public node getMiddle() {
        return middle;
    }

    public node getRight() {
        return right;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        return this.key < ((Rnode)other).getKey();
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    @Override
    public int getKey() {
        return this.key;
    }
    @Override
    public int getSecondaryKey() {
        return this.skey;
    }

    @Override
    public void setKey(int k) {
        this.key = k;
    }
}
