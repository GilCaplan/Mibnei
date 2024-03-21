
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private twothreeTree IDtree;

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
        node runner = new RunnerTree(id);
        IDtree.Insert(runner);
    }

    public void removeRunner(RunnerID id)
    {

    }

    public void addRunToRunner(RunnerID id, float time)
    {
        IDtree.Search(null, Integer.parseInt(String.valueOf(id)));
//        Runner
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
