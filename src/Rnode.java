public class Rnode{
    private float time;
    private Rnode prev;
    private Rnode next;

    public Rnode(Rnode prev, float time){
        this.time = time;
        this.prev = prev;
        next = null;
    }

    @Override
    public String toString() {
        return String.valueOf(time);
    }
}
