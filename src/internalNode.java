public class internalNode<T extends RunnerID> extends node<T>{
    public internalNode(T key) {
        super(key);
    }

    public internalNode(T key, float skey) {
        super(key, skey);
    }

}
