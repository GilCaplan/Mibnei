public class myFloat extends RunnerID {
    private float key;
    public myFloat(float key){
        this.key = key;
    }

    public float getF() {
        return key;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        return this.key < ((myFloat)other).key;
    }

    @Override
    public String toString() {
        return String.valueOf(this.key);
    }
}
