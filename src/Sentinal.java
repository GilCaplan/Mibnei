public class Sentinal extends RunnerID{
    private boolean flag;
    public Sentinal(String flag){
        this.flag = !flag.equals("inf");
    }

    public boolean isSmaller(RunnerID other) {
        return this.flag;
    }

    @Override
    public String toString() {
        String type = !this.flag? "+":"-";
        return "s"+type;
    }
}
