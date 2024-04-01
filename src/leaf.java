public class leaf<T extends RunnerID> extends node<T>{
    public leaf(T key) {
        super(key);
    }

    public leaf(T key, float skey) {
        super(key, skey);
    }
}
