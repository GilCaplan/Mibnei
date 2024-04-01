public class RunnerID_Wrapper<T> extends RunnerID implements Key<T>{
    @Override
    public T getKey() {
        return null;
    }

    @Override
    public void setKey(T k) {

    }

    @Override
    public float getSecondaryKey() {
        return 0;
    }

    @Override
    public boolean isSmaller(T other) {
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
