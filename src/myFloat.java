public class myFloat implements Key<myFloat> {
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
    public float getSecondaryKey() {
        return 0;
    }

    @Override
    public boolean isSmaller(myFloat other) {
        return false;
    }
}
