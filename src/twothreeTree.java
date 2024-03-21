public class twothreeTree<T>{
    protected node<T> root;
    public twothreeTree(){
        Init();
    }

    public void Init(){
        this.root = new node<>((T) new Sentinal());

        // sentinel node<T>s
        node<T> l =new node<>((T)new Sentinal());
        node<T> m = new node<>((T) new Sentinal());

        l.setp(root);
        m.setp(root);

        root.setLeft(l);
        root.setMiddle(m);
    }

    public node<T> Search(node<T> x, T k) throws CastingException {
        if(this.root.getLeft().getLeft() == null && this.root.getRight().getLeft() == null)//sentinel
            return this.root;

        x = this.root;
        if (x.getLeft() == null && x.getKey().equals(k))// checking if x is a leaf
            return x;
        if(x.getLeft() == null) return null;
        if(((RunnerID)k).isSmaller((RunnerID)(x.getLeft()).getKey()))
            return Search(x.getLeft(), k);
        if(((RunnerID) k).isSmaller((RunnerID) (x.getMiddle().getKey())))
            return Search(x.getMiddle(), k);
        return Search(x.getRight(), k);
    }

    public node<T> Minimum(){
        node<T> x = this.root;
        while(x.getLeft() != null)//x is not a leaf
            x = x.getLeft();
        x = x.getp().getMiddle();
        if(!(x.getKey() instanceof Sentinal))
            return x;
        throw new IllegalArgumentException("T is empty");
    }

    public node<T> Successor(node<T> x){
        node<T> z = x.getp();
        node<T> y;
        while(x == x.getRight() || (z.getRight() == null && x == z.getMiddle())){
            x = z;
            z = z.getp();
        }
        if(x == z.getLeft())
            y = z.getMiddle();
        else
            y = z.getRight();
        while(y.getLeft() != null)//y is not a leaf
            y = y.getLeft();
        if(((RunnerID)y.getKey()).isSmaller(new Sentinal()))
            return y;
        return null;
    }

    public void Update_Key(node<T> x){
        x.setKey(x.getLeft().getKey());
        if(x.getMiddle() != null)
            x.setKey(x.getMiddle().getKey());
        if(x.getRight() != null)
            x.setKey(x.getRight().getKey());
    }

    public void Set_Children(node<T> x, node<T> l, node<T> m, node<T> r){
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);

        if(m != null)
            m.setp(x);
        if(r != null)
            r.setp(x);
        Update_Key(x);
    }

    public node<T> Insert_And_Split(node<T> x, node<T> z) throws CastingException {
        node<T> l = x.getLeft();
        node<T> m = x.getMiddle();
        node<T> r = x.getRight();
        if(r == null){
            if(((RunnerID)z.getKey()).isSmaller((RunnerID)l.getKey()))
                Set_Children(x, z, l, m);
            else if(((RunnerID)z.getKey()).isSmaller((RunnerID)m.getKey()))
                Set_Children(x, l,z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        node<T> y = new node<T>(m.getKey());//check
        if(((RunnerID)z.getKey()).isSmaller((RunnerID)l.getKey())){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if(((RunnerID)z.getKey()).isSmaller((RunnerID)m.getKey())){
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if(((RunnerID)z.getKey()).isSmaller((RunnerID)r.getKey())){
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        }
        else Set_Children(y, r, z, null);
        return y;
    }

    public void Insert(node<T> z) throws CastingException {
        node<T> y = this.root;
        while(y.getLeft() != null) {//y is not a leaf
            if (((RunnerID)z.getKey()).isSmaller((RunnerID) y.getLeft().getKey()))
                y = y.getLeft();
            else if(((RunnerID)z.getKey()).isSmaller((RunnerID)y.getMiddle().getKey()))
                    y = y.getMiddle();
            else y = y.getRight();
        }
        node<T> x = y.getp();
        if (z != null)
            z = Insert_And_Split(x, z);
        else Update_Key(x);
        if(z != null){
            node<T> w = new node<T>(z.getKey());//check
            Set_Children(w, x, z, null);
            this.root = w;
        }
    }

    public node<T> Borrow_Or_Merge(node<T> y){
        node<T> z = y.getp();
        if(y == z.getLeft()){
            node<T> x = z.getMiddle();
            if(x.getRight() != null){
                Set_Children(y, y.getLeft(), x.getLeft(), null);
                Set_Children(x, x.getMiddle(), x.getRight(), null);
            }
            else{
                Set_Children(x, y.getLeft(), x.getLeft(), x.getMiddle());
                //delete y?
                Set_Children(z, x, z.getRight(), null);
            }
            return z;
        }
        if(y == z.getMiddle()){
            node<T> x = z.getLeft();
            if(x.getRight() != null){
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
            }
            else{
                Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
                //delete node<T> y?
                Set_Children(z, x, z.getRight(), null);
                return z;
            }
        }
        node<T> x = z.getMiddle();
        if(x.getRight() != null){
            Set_Children(y, x.getRight(), y.getLeft(), null);
            Set_Children(x, x.getLeft(), x.getMiddle(), null);
        }
        else{
            Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
            //delete node<T> y?
            Set_Children(z, z.getLeft(), x, null);
        }
        return z;
    }

    public void Delete(node<T> x){
        node<T> y = x.getp();
        if( x == y.getLeft())
            Set_Children(y, y.getMiddle(), y.getRight(), null);
        else if(x == y.getMiddle()){
            Set_Children(y, y.getLeft(), y.getRight(), null);
        }
        else {
            Set_Children(y, y.getLeft(), y.getMiddle(), null);
            //delete node<T> x?
        }
        while(y != null){
            if(y.getMiddle() == null){
                if(y != this.root)
                    y = Borrow_Or_Merge(y);
                else {
                    this.root = y.getLeft();
                    y.getLeft().setp(null);
                    //delete y?
                    return;
                }
            }
            else{
                Update_Key(y);
                y = y.getp();
            }
        }
    }
}

