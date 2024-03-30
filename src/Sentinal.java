public class Sentinal extends RunnerID{
    private boolean flag;
    public Sentinal(String flag){
        this.flag = flag.equals("inf");
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        return this.flag;
    }

    public float getFloat(){
        return !this.flag? 999999999:-999999999;
    }

    @Override
    public String toString() {
        String type = !this.flag? "+":"-";
        return "s"+type;
    }
}
