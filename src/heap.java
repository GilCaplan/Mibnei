//public class heap<T extends Key> {
//    private Darray<T> A;
//    private int heap_size;
//    public heap(){
//        A = new Darray<>();
//        A.add(null);
//        heap_size = 0;
//    }
//    //perhaps edit other functions as well?
//    //edited so it can work with a secondary key
//    public void Heapify(int i){
//        int l = 2 * i;
//        int smallest;
//        if (l < this.heap_size && ((A.get(l).getKey() == A.get(i).getKey()) && A.get(l).getSecondaryKey() <  A.get(i).getSecondaryKey() )|| (Integer.valueOf(A.get(l).getKey().toString()) < Integer.valueOf(A.get(i).getKey().toString())))
//            smallest = l;
//        else
//            smallest = i;
//        int r = 2*i +1;
//        if (r < this.heap_size && ((A.get(r).getKey() == this.A.get(smallest).getKey() && A.get(r).getSecondaryKey() < this.A.get(smallest).getSecondaryKey() )||(A.get(r).getKey() < this.A.get(smallest).getKey())))
//            smallest = r;
//        if (smallest != i){
//            //swap A[i] and A[smallest]
//            T temp = A.get(smallest);
//            A.set(smallest, A.get(i));
//            A.set(i, temp);
//        }
//        //A[i] seeps down the tree
//
//    }
//
//    public void Build_Heap(){
//        this.heap_size = this.A.size();
//        for(int i=this.A.size(); i>0; i--)
//            Heapify(i);
//    }
//
//
//    public T Heap_Extract_Min(){
//        if(this.heap_size < 1)
//            throw new IllegalStateException("The heap is empty");
//        T min = this.A.get(0);
//        this.A.set(0, this.A.get(heap_size - 1));
//        Heapify(0);
//        return min;
//    }
//
//    public void Heap_Decrease_Key(int i, int k){
//        if(k > this.A.get(i).getKey())
//            throw new IllegalStateException("new key is larger than current key");
//        this.A.get(i).setKey(k);
//
//        while(i > 1 && A.get(i).getKey() < this.A.get(i/2).getKey()){
//            T temp = this.A.get(i);
//            this.A.set(i, this.A.get(i/2));
//            this.A.set(i/2, temp);
//            i /= 2;
//        }
//    }
//
//    public void Heap_Insert(T x){
//        int s = this.heap_size+1;
//        this.A.set(s, x);
//        this.A.get(s).setKey(999999999);
//        this.heap_size = s-1;
//        Heap_Decrease_Key(s, x.getKey());
//    }
//    public void Heap_Delete(int i){
//            Heap_Decrease_Key(i, -999999999);
//            Heap_Extract_Min();
//    }
//
//    public void Heap_Increase_Key(int i, int k){
//        if(this.A.get(i).getKey() > k)
//            throw new IllegalStateException("new key is smaller than current key");
//        this.A.get(i).setKey(k);
//        Heapify(i);
//    }
//
//    public int getHeap_size() {
//        return heap_size;
//    }
//
//    public void setfirst(T i) {
//        this.A.add(i);
//        this.heap_size = 1;
//    }
//
//    public void setHeap_size(int heap_size) {
//        this.heap_size = heap_size;
//    }
//}
