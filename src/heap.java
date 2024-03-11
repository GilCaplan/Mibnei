public class heap<T extends Key> {
    private Darray<T> A;
    private int heap_size;
    public heap(){
        A = new Darray<>();
        heap_size = 0;
    }
    public void Heapify(int i){
        int l = 2 * i;
        int smallest = (l <= this.heap_size && this.A.get(l).getKey() < this.A.get(i).getKey()) ? l : i;
        int r = 2*i +1;
        if (r <= this.heap_size && A.get(r).getKey() < this.A.get(smallest).getKey() )
            smallest = r;
        if (smallest != i){
            //swap A[i] and A[smallest]
            T temp = A.get(smallest);
            A.set(smallest, A.get(i));
            A.set(i, temp);
        }
        //A[i] seeps down the tree

    }

    public void Build_Heap(){
        this.heap_size = this.A.size();
        for(int i=this.A.size(); i>0; i--)
            Heapify(i);
    }

    public T Heap_Extract_Min(){
        if(this.heap_size < 1)
            throw new IllegalStateException("The heap is empty");
        T min = this.A.get(1);
        this.A.set(1, this.A.get(heap_size - 1));
        Heapify(1);
        return min;
    }

    public void Heap_Decrease_Key(int i, int k){
        if(k > this.A.get(i).getKey())
            throw new IllegalStateException("new key is larger than current key");
        this.A.get(i).setKey(k);//fix

        while(i > 1 && A.get(i).getKey() < this.A.get(i/2).getKey()){
            T temp = this.A.get(i);
            this.A.set(i, this.A.get(i/2));
            this.A.set(i/2, temp);
            i /= 2;
        }
    }

    public void Heap_Insert(T x){
        int s = this.heap_size+1;
        this.A.set(s, x);
        this.A.get(s).setKey(999999999);
        this.heap_size = s;
        Heap_Decrease_Key(s, x.getKey());
    }
    public void Heap_Delete(int i){
            Heap_Decrease_Key(i, -999999999);
            Heap_Extract_Min();
    }

    public void Heap_Increase_Key(int i, int k){
        if(this.A.get(i).getKey() > k)
            throw new IllegalStateException("new key is smaller than current key");
        this.A.get(i).setKey(k);
        Heapify(i);
    }

    public Darray<T> getA() {
        return A;
    }

    public int getHeap_size() {
        return heap_size;
    }

    public void setfirst(T i) {
        A.add(i);
    }

    public void setHeap_size(int heap_size) {
        this.heap_size = heap_size;
    }
}
