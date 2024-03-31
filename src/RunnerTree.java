public class RunnerTree<T> extends leaf<T> {
    private twothreeTree<Float> runs;
    private RunnerID id;
    //left to do, implement extra attributes to Treekey that saves min, avg run of runner
    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
        runs = new Runner2_3Tree();
    }
    public RunnerTree(RunnerID i, float skey){
        super((T) i, skey);
        this.id = i;
        runs = new Runner2_3Tree();
    }
    public void Insert(node<Float> z){
        this.runs.Insert(z);
    }
    public void Delete(node<Float> z){
        this.runs.Delete(z);
    }
    public float Successor(){
        return this.runs.Successor(null).getKey();
    }

}
