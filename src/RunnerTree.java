public class RunnerTree<T> extends leaf<T> {
    private twothreeTree<Float> runs;
    private float minTime;
    private RunnerID id;
    //left to do, implement extra attributes to Treekey that saves min, avg run of runner
    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
        runs = new Runner2_3Tree();
        this.minTime = 0;
    }
    public RunnerTree(RunnerID i, float skey){
        super((T) i, skey);
        this.id = i;
        runs = new Runner2_3Tree();
    }
    public void Insert(node<Float> z){
        this.runs.Insert(z);
        this.minTime = this.getRuns().Minimum().getKey();
    }
    public void Delete(node<Float> z){
        this.runs.Delete(z);
    }

    public twothreeTree<Float> getRuns() {
        return runs;
    }

    public float getMinTime() {
        return minTime;
    }

    public RunnerID getId() {
        return id;
    }

    public void setMinTime(float minTime) {
        this.minTime = minTime;
    }

    public float Successor(){
        return this.runs.Successor(null).getKey();
    }

}
