public class RunnerTree<T extends RunnerID> extends leaf<T> {
    private twothreeTree<myFloat> runs;
    private float minTime;
    private RunnerID id;
    private int len, sumTime;
    //left to do, implement extra attributes to Treekey that saves min, avg run of runner
    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
        runs = new Runner2_3Tree();
        this.minTime = Float.MIN_VALUE;
    }
    public RunnerTree(RunnerID i, float skey){
        super((T) i, skey);
        this.id = i;
        runs = new Runner2_3Tree();
        len++;
        sumTime += skey;
    }
    public void Insert(node<myFloat> z){
        this.runs.Insert(z);
        this.minTime = this.getRuns().Minimum().getKey().getF();
        len++;
        sumTime += z.getKey().getF();
    }
    public void Delete(node<myFloat> z){
        this.runs.Delete(z);
        len--;
        sumTime -= z.getKey().getF();
    }

    public twothreeTree<myFloat> getRuns() {
        return runs;
    }

    public float getMinTime() {
        return minTime;
    }

    public float getAvgRun(){return (float) sumTime / len;}


    public RunnerID getId() {
        return id;
    }

    public void setMinTime(float minTime) {
        this.minTime = minTime;
    }

    public float Successor(){
        return this.runs.Successor(null).getKey().getF();
    }

}
