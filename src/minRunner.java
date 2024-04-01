public class minRunner extends node<RunnerID> implements Key<RunnerID>{
    public minRunner(RunnerTree<RunnerID> runner) {
        super(runner.getKey(), runner.getMinTime());
    }
}
