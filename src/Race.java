
public class Race {
    // create some sort of Tree object data structure that can support the
    // runtime of requested functions.
    private heap<RunnerHeap> IDheap;
    private heap<RunnerHeap> avgheap;
    private heap<RunnerHeap> minheap;
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
            RunnerHeap node = new RunnerHeap(Integer.parseInt(String.valueOf(id)));
            IDheap.setfirst(node);
            return;
        }
        int i = Integer.parseInt(String.valueOf(id));
        IDheap.Heap_Insert(new RunnerHeap(i));
    }

    public void removeRunner(RunnerID id)
    {
        IDheap.Heap_Delete(Integer.parseInt(String.valueOf(id)));
    }

    public void addRunToRunner(RunnerID id, float time)
    {
        if (IDheap == null || IDheap.getHeap_size() < 1) {
            throw new IllegalArgumentException("runner doesn't exist");
        }

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
