public class RunnerTree<T extends RunnerID> extends leaf<T> {
    private twothreeTree<myFloat> runs;
    private myFloat minTime;

    private final RunnerID id;
    private int len, sumTime;
    //left to do, implement extra attributes to Treekey that saves min, avg run of runner
    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
        runs = new Runner2_3Tree();
        this.minTime = new myFloat(Float.MIN_VALUE);
    }
    public void Insert(node<myFloat> z){
        this.runs.Insert(z);
        this.minTime = this.getRuns().Minimum().getKey();
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

    public myFloat getAvgRun(){return new myFloat((float) sumTime / len);}

    public RunnerID getId() {
        return id;
    }
}
