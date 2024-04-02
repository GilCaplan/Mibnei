public interface Key<T> {
    T getKey();
    void setKey(T k);
    myFloat getSecondaryKey();
    boolean isSmaller(T other);
}