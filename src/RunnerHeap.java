public class RunnerHeap implements Key {
    private int runnerID;
    private heap<Rnode> minHeap;
    private heap<Rnode> maxHeap;
    private int minLen, maxLen;

    public RunnerHeap(int runnerID){
        this.runnerID = runnerID;
        minHeap = new heap<>();
        maxHeap = new heap<>();
        minLen = minHeap.getHeap_size();
        maxLen = maxHeap.getHeap_size();
    }

    @Override
    public int getKey() {
        return this.runnerID;
    }

    @Override
    public void setKey(int k) {
        this.runnerID = k;
    }

    @Override
    public int getSecondaryKey() {
        return 0;
    }
}
