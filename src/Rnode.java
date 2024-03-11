public class Rnode extends RunnerID implements Key {
    private float time;
    private int key;

    public Rnode(float time, int key){
        this.time = time;
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

}
