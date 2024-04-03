public class RunnerTree<T extends RunnerID> extends leaf<T> {
    private twothreeTree<myFloat> runs;
    private myFloat minTime;
    private myFloat prevTime;

    public void setPrevTime(myFloat prevTime) {
        this.prevTime = prevTime;
    }

    private final RunnerID id;
    private int len;
    private float sumTime;
    //left to do, implement extra attributes to Treekey that saves min, avg run of runner
    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
        runs = new Runner2_3Tree();
        this.minTime = new myFloat(Float.MAX_VALUE);
        this.prevTime = new myFloat(Float.MAX_VALUE);
    }
    public void Insert(node<myFloat> z){
        this.runs.Insert(z);
        if (len > 0)
            this.minTime = this.runs.Minimum().getKey();
        else
            this.minTime = z.getKey();
        len++;
        sumTime += z.getKey().getF();
    }
    public void Delete(node<myFloat> z){
        this.runs.Delete((internalNode<myFloat>) z);
        len--;
        sumTime -= z.getKey().getF();
    }

    public twothreeTree<myFloat> getRuns() {
        return runs;
    }

    public myFloat getMinTime() {
        return minTime;
    }
    public myFloat getPrevTime(){return prevTime;}

    public myFloat getAvgRun(){return new myFloat((float) sumTime / len);}

    public RunnerID getId() {
        return id;
    }

    public void printTree(node<T> root, String prefix, boolean isTail) {
        if (root instanceof internalNode<T>) {
//            System.out.println(prefix + (isTail ? "└── InnerNode with key: " : "├── InnerNode with key: ") + root.key+", size: "+root.getSize());
            System.out.println(prefix + (isTail ? "└──" : "├── "));
            internalNode<RunnerID> innerNode = (internalNode<RunnerID>) root;
            printTree((node<T>) innerNode.getLeft(), prefix + (isTail ? "    " : "│   "), false);
            printTree((node<T>) innerNode.getMiddle(), prefix + (isTail ? "    " : "│   "), false);
            printTree((node<T>) innerNode.getRight(), prefix + (isTail ? "    " : "│   "), true);
        } else if (root instanceof leaf<T>) {
//            System.out.println(prefix + (isTail ? "└── Leaf with key: " : "├── Leaf with key: ") + root.key+", size: "+root.getSize());
            System.out.println(prefix + (isTail ? "└── : " : "├── : ") + root.getKey());
        }
    }
}
