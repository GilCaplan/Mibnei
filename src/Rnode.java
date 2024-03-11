public class Rnode extends RunnerID implements Key {
    private float time;
    private int key;
    private Rnode prev;
    private Rnode left;
    private Rnode right;

    public Rnode(Rnode prev, float time, int key){
        this.time = time;
        this.prev = prev;
        this.left = null;
        this.right = null;
        this.key = key;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        return this.key < ((Rnode)other).getKey();
    }

    @Override
    public String toString() {
        return String.valueOf(time);
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public void setKey(int k) {
        this.key = k;
    }

    public void setLeft(Rnode l) {
        this.left = l;
    }
    public void setRight(Rnode r) {
        this.right = r;
    }
    public Rnode getLeft(Rnode l) {
        return this.left;
    }
    public Rnode getRight(Rnode r) {
        return this.right;
    }
}
