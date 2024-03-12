public class Rnode extends RunnerID implements Key {
    private float time;
    private int key;
    private int skey;

    public Rnode(float time, int key){
        this.time = time;
        this.key = key;
        this.skey = 0;
    }
    public Rnode(float time, int key, int skey){
        this.time = time;
        this.key = key;
        this.skey = skey;
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
    public int getSecondaryKey() {
        return this.skey;
    }

    @Override
    public void setKey(int k) {
        this.key = k;
    }

}
