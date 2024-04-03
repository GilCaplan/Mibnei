public class RunnerTree<T extends RunnerID> extends leaf<T> {
    private twothreeTree<myFloat> runs;
    private myFloat minTime;
    private myFloat avgTime;
    private myFloat prevMinTime;
    private myFloat prevAvgTime;

    public void setPrevMinTime(myFloat prevTime) {
        this.prevMinTime = prevTime;
    }
    public void setPrevAvgTime(myFloat prevTime) {
        this.prevAvgTime = prevTime;
    }

    private final RunnerID id;
    private int len;
    private float sumTime;

    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
        runs = new Runner2_3Tree();
        this.minTime = new myFloat(Float.MAX_VALUE);
        this.prevMinTime = new myFloat(Float.MAX_VALUE);
        this.avgTime = new myFloat(0);
        this.avgTime = new myFloat(0);
        this.prevAvgTime = new myFloat(0);
    }
    public void Insert(node<myFloat> z){
        if (id == null || z.getKey().getF() < 0) {
            throw new IllegalArgumentException();
        }
        this.runs.Insert(z);
        if (len > 0)
            this.minTime = this.runs.Minimum().getKey();
        else
            this.minTime = z.getKey();
        len++;
        sumTime += z.getKey().getF();
        avgTime = new myFloat(sumTime / len);
    }
    public void Delete(node<myFloat> z) {
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
    public myFloat getPrevMinTime(){return prevMinTime;}
    public myFloat getPrevAvgTime(){return prevAvgTime;}

    public myFloat getAvgRun(){
        if(len < 1)
            return new myFloat(Float.MAX_VALUE);
        return avgTime;
    }

    public myFloat updateAvgRun(){return this.avgTime = new myFloat(sumTime / len);}

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
