public class leaf<T extends RunnerID> extends node<T>{
    public leaf(T key) {
        super(key);
    }

    public leaf(T key, myFloat skey) {
        super(key, skey);
    }
}
