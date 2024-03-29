
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private twothreeTree IDtree;

    public Race() throws CastingException {
        init();
    }
    public void init() throws CastingException {
//        IDheap = new heap<>();
          IDtree = new IDtree<RunnerID>();
//        avgheap = new heap<>();
//        minheap = new heap<>();
    }
    public void addRunner(RunnerID id) throws CastingException {
        node runner = new RunnerTree(id);
        IDtree.Insert(runner);
    }

    public void removeRunner(RunnerID id)
    {

    }

    public void addRunToRunner(RunnerID id, float time) throws CastingException {
        IDtree.Search(null, null);
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
