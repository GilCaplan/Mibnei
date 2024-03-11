public class Darray<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public Darray() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }


    public void set(int index, T element) {
        ensureCapacity();
//        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException("Index out of bounds");
//        }
        if (index >= size)
            size++;
        array[index] = element;
    }
    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
