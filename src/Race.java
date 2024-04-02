
public class Race {
    private twothreeTree<RunnerID> IDtree;
    private twothreeTree<RunnerID> minTree;
    private twothreeTree<RunnerID> avgTree;

    public Race() {
        init();
    }
    public void init() {
          IDtree = new IDtree();
          minTree = new IDtree();
          //make separate tree class that's a wrapper class, implements getKey
          // secondary key will be runner.getMin
          avgTree = new IDtree();//same thing
    }
    public void addRunner(RunnerID id)  {
        RunnerTree<RunnerID> runner = new RunnerTree<>(id);
        IDtree.Insert(runner);
//        minTree.Insert(new minRunner(runner));
//        avgTree.Insert(new avgRunner(runner));
    }

    public void removeRunner(RunnerID id)//* need to fix, how to delete from min, avg Tree
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        IDtree.Delete(new leaf<>(id));
        minTree.Delete(new leaf<>(id, runner.getMinTime()));
        avgTree.Delete(new leaf<>(id, runner.getAvgRun()));
    }

    public void addRunToRunner(RunnerID id, float time) {
        //we added the runner object as a shallow copy so we need to delete and then re-add it
        //this will hold to the log(n) since each action is log(n) in parallel
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        runner.Insert(new leaf<>(new myFloat(time)));

        fixMinAvgRuns(id, runner);
    }

    public void removeRunFromRunner(RunnerID id, float time)
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        runner.Delete(new leaf<>(new myFloat(time)));

        fixMinAvgRuns(id, runner);
    }

    public RunnerID getFastestRunnerAvg()
    {
        return avgTree.Minimum().getKey();
    }

    public RunnerID getFastestRunnerMin()
    {
        return minTree.Minimum().getKey();
    }

    public float getMinRun(RunnerID id)
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        return runner.getMinTime();
    }
    public float getAvgRun(RunnerID id){
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        return runner.getAvgRun();
    }

    public int getRankAvg(RunnerID id)
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        return avgTree.Rank(runner);
    }

    public int getRankMin(RunnerID id)
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        return minTree.Rank(runner);
    }

    public void fixMinAvgRuns(RunnerID id, RunnerTree<RunnerID> runner){
        minTree.Delete(new leaf<>(id, runner.getMinTime()));//check that found properly
        avgTree.Delete(new leaf<>(id, runner.getAvgRun()));
        minTree.Insert(new minRunner(runner));
        avgTree.Insert(new avgRunner(runner));
    }
}
