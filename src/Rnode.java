public class Rnode extends node implements Key {
    private float time;
    private int key;
    private int skey;

    public Rnode(float time, int key){
        super(key);
        this.time = time;
        this.skey = 0;
    }
    public Rnode(float time, int key, int skey){
        super(key);
        this.time = time;
        this.skey = skey;
    }

}
