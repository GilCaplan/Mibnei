public class avgRunner extends node<RunnerID> implements Key<RunnerID>{
    public avgRunner(RunnerTree<RunnerID> runner) {
        super(runner.getKey(), runner.getAvgRun());
    }
}
