
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private heap<Rnode> IDheap;
    private heap<Rnode> avgheap;
    private heap<Rnode> minheap;
    public Race(){
        init();
    }
    public void init()
    {
        IDheap = new heap<>();
        avgheap = new heap<>();
        minheap = new heap<>();
    }
    public void addRunner(RunnerID id)
    {
        if (IDheap == null || IDheap.getHeap_size() < 1) {
            Rnode node = new Rnode(null, 100000000, Integer.parseInt(String.valueOf(id)));
            IDheap.setfirst(node);
            return;
        }
        int i = Integer.parseInt(String.valueOf(id));
        IDheap.Heapify(i);

    }

    public void removeRunner(RunnerID id)
    {
        throw new UnsupportedOperationException("not implemented");
    }

    public void addRunToRunner(RunnerID id, float time)
    {
        throw new UnsupportedOperationException("not implemented");
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
