
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
        node<RunnerID> runner = new RunnerTree<>(id);//shallow copy on purpose
        IDtree.Insert(runner);
        minTree.Insert(runner);
        avgTree.Insert(runner);
    }

    public void removeRunner(RunnerID id)//* need to fix, how to delete from min, avg Tree
    {
        IDtree.Delete(new leaf<>(id));
        minTree.Delete(new leaf<>(id));//need to fix according to skey
        avgTree.Delete(new leaf<>(id));
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
}
