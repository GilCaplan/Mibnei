public class twothreeTree extends node{
    protected node root;
    public twothreeTree(){
        super(0);
        Init(0);
    }

    public void Init(int i){
        this.root = new node(999999999, i);

        // sentinel nodes
        node l =new node(-999999999);
        node m = new node(999999999);

        l.setp(root);
        m.setp(root);

        root.setLeft(l);
        root.setMiddle(m);
    }

    public node Search(node x, int k){
        if(this.root.getLeft().getLeft() == null && this.root.getRight().getLeft() == null)//sentinel
            return this.root;
        if(k < 0)
            return null;
        x = this.root;
        if (x.getLeft() == null && x.getKey() == k)// checking if x is a leaf
            return x;
        if(x.getLeft() == null) return null;
        if(k <= x.getLeft().getKey())
            return Search(x.getLeft(), k);
        if(k<= x.getMiddle().getKey())
            return Search(x.getMiddle(), k);
        return Search(x.getRight(), k);
    }

    public node Minimum(){
        node x = this.root;
        while(x.getLeft() != null)//x is not a leaf
            x = x.getLeft();
        x = x.getp().getMiddle();
        if(x.getKey() != 999999999)
            return x;
        throw new IllegalArgumentException("T is empty");
    }

    public node Successor(node x){
        node z = x.getp();
        node y;
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
        if(y.getKey() < 999999999)
            return y;
        return null;
    }

    public void Update_Key(node x){
        x.setKey(x.getLeft().getKey());
        if(x.getMiddle() != null)
            x.setKey(x.getMiddle().getKey());
        if(x.getRight() != null)
            x.setKey(x.getRight().getKey());
    }

    public void Set_Children(node x, node l, node m, node r){
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);

        if(m != null)
            m.setp(x);
        if(r != null)
            r.setp(x);
        Update_Key(x);
    }

    public node Insert_And_Split(node x, node z){
        node l = x.getLeft();
        node m = x.getMiddle();
        node r = x.getRight();
        if(r == null){
            if(z.getKey() < l.getKey())
                Set_Children(x, z, l, m);
            else if(z.getKey() < m.getKey())
                Set_Children(x, l,z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        node y = new node(m.getKey());//check
        if(z.getKey() < l.getKey()){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if(z.getKey() < m.getKey()){
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if(z.getKey() < r.getKey()){
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        }
        else Set_Children(y, r, z, null);
        return y;
    }

    public void Insert(node z){
        node y = this.root;
        while(y.getLeft() != null) {//y is not a leaf
            if (z.getKey() < y.getLeft().getKey()) y = y.getLeft();
            else if(z.getKey() < y.getMiddle().getKey()) y =y.getMiddle();
            else y = y.getRight();
        }
        node x = y.getp();
        if (z != null)
            z = Insert_And_Split(x, z);
        else Update_Key(x);
        if(z != null){
            node w = new node(z.getKey());//check
            Set_Children(w, x, z, null);
            this.root = w;
        }
    }

    public node Borrow_Or_Merge(node y){
        node z = y.getp();
        if(y == z.getLeft()){
            node x = z.getMiddle();
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
            node x = z.getLeft();
            if(x.getRight() != null){
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
            }
            else{
                Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
                //delete node y?
                Set_Children(z, x, z.getRight(), null);
                return z;
            }
        }
        node x = z.getMiddle();
        if(x.getRight() != null){
            Set_Children(y, x.getRight(), y.getLeft(), null);
            Set_Children(x, x.getLeft(), x.getMiddle(), null);
        }
        else{
            Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
            //delete node y?
            Set_Children(z, z.getLeft(), x, null);
        }
        return z;
    }

    public void Delete(node x){
        node y = x.getp();
        if( x == y.getLeft())
            Set_Children(y, y.getMiddle(), y.getRight(), null);
        else if(x == y.getMiddle()){
            Set_Children(y, y.getLeft(), y.getRight(), null);
        }
        else {
            Set_Children(y, y.getLeft(), y.getMiddle(), null);
            //delete node x?
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
