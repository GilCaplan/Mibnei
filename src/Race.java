
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
          avgTree = new IDtree();
    }
    public node<RunnerID> getRoot(){
        return IDtree.getRoot();
    }
    public void addRunner(RunnerID id)  {
        RunnerTree<RunnerID> runner = new RunnerTree<>(id);
        IDtree.Insert(runner);
        minTree.Insert(new minRunner(runner));
        avgTree.Insert(new avgRunner(runner));
    }

    public void removeRunner(RunnerID id) throws Exception//* need to fix, how to delete from min, avg Tree
    {
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        IDtree.Delete(new internalNode<>(id));
        minTree.Delete(new internalNode<>(id, runner.getMinTime()));
        avgTree.Delete(new internalNode<>(id, runner.getAvgRun()));
    }

    public void addRunToRunner(RunnerID id, float time) throws Exception {
        //we added the runner object as a shallow copy so we need to delete and then re-add it
        //this will hold to the log(n) since each action is log(n) in parallel
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        runner.Insert(new leaf<>(new myFloat(time)));
        fixMinAvgRuns(id, runner);
    }

    public void removeRunFromRunner(RunnerID id, float time) throws Exception {
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
        return runner.getMinTime().getF();
    }
    public float getAvgRun(RunnerID id){
        RunnerTree<RunnerID> runner = (RunnerTree<RunnerID>) IDtree.Search(null, new leaf<>(id));
        return runner.getAvgRun().getF();
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

    public void fixMinAvgRuns(RunnerID id, RunnerTree<RunnerID> runner) throws Exception {
        minTree.Delete(new internalNode<>(id, runner.getPrevMinTime()));//check that found properly
        runner.setPrevMinTime(runner.getMinTime());
        avgTree.Delete(new internalNode<>(id, runner.getPrevAvgTime()));
        runner.setPrevAvgTime(runner.getAvgRun());
        minTree.Insert(new minRunner(runner));
        avgTree.Insert(new avgRunner(runner));
    }
}
