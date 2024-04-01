
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private twothreeTree<RunnerID> IDtree;
    private twothreeTree<RunnerID> minTree;
    private twothreeTree<RunnerID> avgTree;

    public Race() {
        init();
    }
    public void init() {
          IDtree = new IDtree<>();
          minTree = new IDtree<>();
          avgTree = new IDtree<>();
    }
    public void addRunner(RunnerID id)  {
        IDtree.Insert(new RunnerTree<>(id));
        minTree.Insert(new RunnerTree<>(id, 999999999));
        avgTree.Insert(new RunnerTree<>(id, 999999999));
    }

    public void removeRunner(RunnerID id)
    {
        IDtree.Delete(new leaf<>(id));
    }

    public void addRunToRunner(RunnerID id, float time) {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        runner.Insert(new leaf<>(new myFloat(time)));
    }

    public void removeRunFromRunner(RunnerID id, float time)
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        runner.Delete(new leaf<>(new myFloat(time)));
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
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        return runner.getMinTime();
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
