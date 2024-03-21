public interface Key<T> {
    T getKey();
    void setKey(T k);
    float getSecondaryKey();
    boolean isSmaller(T other);
}
