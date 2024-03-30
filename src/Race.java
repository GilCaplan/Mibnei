
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private twothreeTree<RunnerID> IDtree;

    public Race() {
        init();
    }
    public void init() {
          IDtree = new IDtree<>();
    }
    public void addRunner(RunnerID id)  {
        IDtree.Insert(new RunnerTree<>(id));
    }

    public void removeRunner(RunnerID id)
    {

    }

    public void addRunToRunner(RunnerID id, float time) {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        runner.Insert(new internalNode<>(time));
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
