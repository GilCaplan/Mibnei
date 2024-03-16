
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private heap<RunnerHeap> IDheap;
    private twothreeTree IDtree;
    private heap<RunnerHeap> avgheap;
    private heap<RunnerHeap> minheap;
    public Race(){
        init();
    }
    public void init()
    {
//        IDheap = new heap<>();
          IDtree = new twothreeTree();
//        avgheap = new heap<>();
//        minheap = new heap<>();
    }
    public void addRunner(RunnerID id)
    {
        int i = Integer.parseInt(String.valueOf(id));
        RunnerTree runner = new RunnerTree(i);
    }

    public void removeRunner(RunnerID id)
    {

    }

    public void addRunToRunner(RunnerID id, float time)
    {
        RunnerTree Runner = (RunnerTree) ;
        Runner
    }

    public void removeRunFromRunner(RunnerID id, float time)
    {
        throw new UnsupportedOperationException("not implemented");
    }

    public RunnerID getFastestRunnerAvg()
    {

        throw new UnsupportedOperationException("not implemented");
    }

    public RunnerID getFastestRunnerMin()
    {

        throw new UnsupportedOperationException("not implemented");
    }

    public float getMinRun(RunnerID id)
    {

        throw new UnsupportedOperationException("not implemented");
    }
    public float getAvgRun(RunnerID id){
        throw new UnsupportedOperationException("not implemented");
    }

    public int getRankAvg(RunnerID id)
    {
        throw new UnsupportedOperationException("not implemented");
    }

    public int getRankMin(RunnerID id)
    {
        throw new UnsupportedOperationException("not implemented");
    }
}
