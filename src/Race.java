
public class Race {
    private twothreeTree<RunnerID> IDtree;
    private twothreeTree<RunnerID> minTree;
    private twothreeTree<RunnerID> avgTree;

    private RunnerID fastedRunnerMin, fastedRunnerAvg;
    public Race() {
        init();
    }
    public void init() {
          IDtree = new IDtree();
          minTree = new IDtree();
          avgTree = new IDtree();
    }
    public node<RunnerID> getRoot(){
        return IDtree.getRoot();
    }
    public void addRunner(RunnerID id)  {
        RunnerTree<RunnerID> runner = new RunnerTree<>(id);
        if(IDtree.Search(null, runner) != null)
            throw new IllegalArgumentException();
        IDtree.Insert(runner);
        minTree.Insert(new minRunner(runner));
        avgTree.Insert(new avgRunner(runner));
        fastedRunnerAvg = avgTree.Minimum().getKey();
        fastedRunnerMin = minTree.Minimum().getKey();
    }

    public void removeRunner(RunnerID id)
    {
        RunnerTree<RunnerID> runner = getRunner(id);
        IDtree.Delete(new internalNode<>(id));
        minTree.Delete(new internalNode<>(id, runner.getMinTime()));
        avgTree.Delete(new internalNode<>(id, runner.getAvgRun()));
        try {
            fastedRunnerAvg = avgTree.Minimum().getKey();
            fastedRunnerMin = minTree.Minimum().getKey();
        }
        catch(IllegalArgumentException ae){}
    }

    public void addRunToRunner(RunnerID id, float time) {
        RunnerTree<RunnerID> runner = getRunner(id);
        runner.Insert(new leaf<>(new myFloat(time)));
        fixMinAvgRuns(id, runner);
    }

    public void removeRunFromRunner(RunnerID id, float time) {
        RunnerTree<RunnerID> runner = getRunner(id);
        runner.Delete(new internalNode<>(new myFloat(time)));
        fixMinAvgRuns(id, runner);
    }


    public float getMinRun(RunnerID id)
    {
        return getRunner(id).getMinTime().getF();
    }
    public float getAvgRun(RunnerID id){
        return getRunner(id).getAvgRun().getF();
    }

    public int getRankAvg(RunnerID id)
    {
        RunnerTree<RunnerID> r = getRunner(id);
        avgRunner runner = (avgRunner) avgTree.Search(null, new avgRunner(r));
        return avgTree.Rank(runner);
    }

    public int getRankMin(RunnerID id)
    {
        RunnerTree<RunnerID> r = getRunner(id);
        avgRunner runner = (avgRunner) minTree.Search(null, new minRunner(r));
        return minTree.Rank(runner);
    }

    public RunnerID getFastestRunnerAvg()
    {
        return fastedRunnerAvg;
    }

    public RunnerID getFastestRunnerMin()
    {
        return fastedRunnerMin;
    }

    public void fixMinAvgRuns(RunnerID id, RunnerTree<RunnerID> runner) {
        minTree.Delete(new internalNode<>(id, runner.getPrevMinTime()));//check that found properly
        runner.setPrevMinTime(runner.getMinTime());
        avgTree.Delete(new internalNode<>(id, runner.getPrevAvgTime()));
        runner.setPrevAvgTime(runner.getAvgRun());
        minTree.Insert(new minRunner(runner));
        avgTree.Insert(new avgRunner(runner));
        fastedRunnerAvg = avgTree.Minimum().getKey();
        fastedRunnerMin = minTree.Minimum().getKey();
    }

    public RunnerTree<RunnerID> getRunner(RunnerID id){
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        if(runner == null)
            throw new IllegalArgumentException();
        return runner;
    }
}
