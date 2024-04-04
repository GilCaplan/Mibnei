public class avgRunner extends leaf<RunnerID>{
    public avgRunner(RunnerTree<RunnerID> runner) {
        super(runner.getKey(), runner.getAvgRun());
    }
    @Override
    public String toString(){
        return this.getKey()+", "+this.getSecondaryKey();
    }
}
