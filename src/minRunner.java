public class minRunner extends leaf<RunnerID> implements Key<RunnerID>{
    public minRunner(RunnerTree<RunnerID> runner) {
        super(runner.getKey(), runner.getMinTime());
    }
    @Override
    public String toString(){
        return this.getKey()+", "+this.getSecondaryKey();
    }
}
