public abstract class node<T extends RunnerID>{
    private node<T> p;
    private node<T> left;
    private node<T> middle;
    private node<T> right;
    private T key;
    private myFloat skey;
    private int size=0;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize(){return this.size;}

    public void setp(node<T> p) {
        this.p = p;
    }

    public node(T key) {
        this.left = null;
        this.middle = null;
        this.p = null;
        this.right = null;
        this.key = key;
        this.skey = new myFloat((float) -1);
    }


    public node(T key, myFloat skey) {
        this.left = null;
        this.middle = null;
        this.p = null;
        this.right = null;
        this.key = key;
        this.skey = skey;
    }
    @Override
    public String toString(){
        if(this.getSecondaryKey() == null || this.getSecondaryKey().getF() == -1)
            return this.getKey().toString();
        return "("+this.getKey().toString()+", "+this.getSecondaryKey().toString()+", "+this.getSize()+")";
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


    public T getKey() {
        return this.key;
    }
    public myFloat getSecondaryKey() {
        return this.skey;
    }

    public boolean isSmaller(T other) {//don't call
        return this.getKey().isSmaller(other);
    }

    public void setKey(T k) {
        this.key = k;
    }
    public void setKey(T k, myFloat t) {
        this.key = k;
        this.skey = t;
    }
}

