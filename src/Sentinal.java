public class Sentinal extends RunnerID{
    private final boolean flag;


    public Sentinal(String flag){
        this.flag = flag.equals("inf");
    }

    @Override
    public boolean isSmaller(RunnerID other) {//sentinal < other.key
        return this.flag;
    }

    @Override
    public String toString() {
        return "s" + (this.flag? "+":"-");
    }
}
