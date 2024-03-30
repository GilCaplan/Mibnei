public class RunnerTree<T> extends leaf<T> {
    private node<T> Treekey;
    private RunnerID id;
    //left to do, implement extra attributes to Treekey that saves min, avg run of runner
    public RunnerTree(RunnerID i){
        super((T) i);
        this.id = i;
    }

    public void Init(){
        node<T> x = new internalNode<>(null);

        // sentinel node<T>s
        node<T> l = new internalNode<>((T)new Sentinal("-inf"));
        node<T> m = new internalNode<>((T) new Sentinal("inf"));

        l.setp(x);
        m.setp(x);

        x.setKey((T) new Sentinal("inf"));
        x.setLeft(l);
        x.setMiddle(m);
        this.Treekey = x;

    }

    public RunnerID getTKey(){
        return this.id;
    }
    public int rank(node x){
        int rank = 1;
        node y = x.getp();
        while(y != null){
            if(x == y.getMiddle())
                rank+= y.getLeft().getSize();
            else if(x == y.getRight())
                rank += y.getRight().getSize();
            x = y;
            y = y.getp();
        }
        return rank;
    }

    public void Insert(node<T> z){
        node<T> y = this.Treekey;
        z.setSize(1);
        while((!(y.getKey() instanceof leaf) || !(y instanceof leaf)) && y.getLeft() != null) {//y is not a leaf
            if ((float) z.getKey() < (float)y.getLeft().getKey()) y = y.getLeft();
            else if((float)z.getKey() < (float)y.getMiddle().getKey()) y =y.getMiddle();
            else y = y.getRight();
        }
        node<T> x = y.getp();
        if (z != null)
            z = Insert_And_Split(x, z);
        else Update_Key(x);
        if(z != null){
            node<T> w = new internalNode(z.getKey());//check
            Set_Children(w, x, z, null);
            this.Treekey = w;
        }
    }

    public node<T> Insert_And_Split(node<T> x, node<T> z){
        node<T> l = x.getLeft();
        node<T> m = x.getMiddle();
        node<T> r = x.getRight();
        if (x.getMiddle() == null)//only one child => deg(z)=2
            x.setSize(x.getSize() + 1);

        if(r == null){
            if((float)z.getKey() < (float)l.getKey())
                Set_Children(x, z, l, m);
            else if((float)z.getKey() < (float)m.getKey())
                Set_Children(x, l,z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        node<T> y = new internalNode(m.getKey());//check
        if(x.getLeft() == null)
            y.setSize(1);
        else if (x.getMiddle() == null)
            y.setSize(1 + x.getLeft().getSize());
        else if (x.getRight() == null)
            y.setSize(1 + x.getMiddle().getSize() + x.getLeft().getSize());
        else
            y.setSize(1 + x.getMiddle().getSize() + x.getLeft().getSize() + x.getRight().getSize());

        if((float)z.getKey() < (float)l.getKey()){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if((float)z.getKey() < (float)m.getKey()){
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if((float)z.getKey() < (float)r.getKey()){
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        }
        else {
            Set_Children(x, l, m, null);
            Set_Children(y, r, z, null);
        }
        return y;
        //if a new Treekey needs to be added then update its size value (sum of childrens size attribute)
        //need to implement?
    }

    public void Delete(node<T> x) {
        node<T> y = x.getp();
        if( x == y.getLeft())
            Set_Children(y, y.getMiddle(), y.getRight(), null);
        else if(x == y.getMiddle())
            Set_Children(y, y.getLeft(), y.getRight(), null);
        else
            Set_Children(y, y.getLeft(), y.getMiddle(), null);

        y.setSize(y.getSize()-1);
        node<T> l = y;
        while(l != null){
            l = l.getp();
            l.setSize(l.getSize()-1);
        }
        //delete node x?
        while(y != null){
            if(y.getMiddle() == null){
                if(y != this.Treekey)
                    y = Borrow_Or_Merge(y);
                else {
                    this.Treekey = y.getLeft();
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

    public node<T> Borrow_Or_Merge(node<T> y){
        node<T> z = y.getp();
        if(y == z.getLeft()){
            node<T> x = z.getMiddle();
            if(x.getRight() != null){
                Set_Children(y, y.getLeft(), x.getLeft(), null);
                Set_Children(x, x.getMiddle(), x.getRight(), null);

                x.setSize(x.getMiddle().getSize() + x.getRight().getSize());//update sizes
                y.setSize(y.getLeft().getSize() + x.getRight().getSize());
            }
            else{
                Set_Children(x, y.getLeft(), x.getLeft(), x.getMiddle());
                //delete y?
                Set_Children(z, x, z.getRight(), null);

                x.setSize(y.getLeft().getSize() + x.getLeft().getSize() + x.getMiddle().getSize());
                z.setSize(x.getSize() + z.getRight().getSize());
            }
            return z;
        }
        if(y == z.getMiddle()){
            node<T> x = z.getLeft();
            if(x.getRight() != null){
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);

                y.setSize(y.getRight().getSize() + y.getLeft().getSize());
                x.setSize(x.getLeft().getSize() + x.getMiddle().getSize());
            }
            else{
                Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
                //delete node y?
                Set_Children(z, x, z.getRight(), null);

                x.setSize(x.getLeft().getSize() + x.getMiddle().getSize() + y.getLeft().getSize());
                z.setSize(x.getSize() + z.getRight().getSize());
                return z;
            }
        }
        node<T> x = z.getMiddle();
        if(x.getRight() != null){
            Set_Children(y, x.getRight(), y.getLeft(), null);
            Set_Children(x, x.getLeft(), x.getMiddle(), null);

            y.setSize(x.getRight().getSize() + y.getLeft().getSize());
            x.setSize(x.getLeft().getSize() + x.getMiddle().getSize());
        }
        else{
            Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
            //delete node y?
            Set_Children(z, z.getLeft(), x, null);

            x.setSize(x.getLeft().getSize() + x.getMiddle().getSize() + y.getLeft().getSize());
            z.setSize(z.getLeft().getSize() + x.getSize());
        }
        return z;
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
}
