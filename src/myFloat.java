public class myFloat extends RunnerID implements Key<myFloat> {
    private float key;
    public myFloat(float key){
        this.key = key;
    }

    public float getF() {
        return key;
    }

    @Override
    public myFloat getKey() {
        return new myFloat(key);
    }

    @Override
    public void setKey(myFloat k) {

    }

    @Override
    public myFloat getSecondaryKey() {
        return new myFloat(0);
    }

    @Override
    public boolean isSmaller(myFloat other) {
        return false;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
