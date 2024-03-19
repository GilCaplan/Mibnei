public class RunnerTree extends twothreeTree {
    //left to do, implement extra attributes to root that saves min, avg run of runner
    public RunnerTree(int i){
        super.Init(i);
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

    @Override
    public void Insert(node z){
        node y = this.root;
        z.setSize(1);
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

    @Override
    public node Insert_And_Split(node x, node z){
        node l = x.getLeft();
        node m = x.getMiddle();
        node r = x.getRight();
        if (x.getMiddle() == null)//only one child => deg(z)=2
            x.setSize(x.getSize() + 1);

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
        if(x.getLeft() == null)
            y.setSize(1);
        else if (x.getMiddle() == null)
            y.setSize(1 + x.getLeft().getSize());
        else if (x.getRight() == null)
            y.setSize(1 + x.getMiddle().getSize() + x.getLeft().getSize());
        else
            y.setSize(1 + x.getMiddle().getSize() + x.getLeft().getSize() + x.getRight().getSize());

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
        //if a new root needs to be added then update its size value (sum of childrens size attribute)
        //need to implement?
    }

    @Override
    public void Delete(node x) {
        node y = x.getp();
        if( x == y.getLeft())
            Set_Children(y, y.getMiddle(), y.getRight(), null);
        else if(x == y.getMiddle())
            Set_Children(y, y.getLeft(), y.getRight(), null);
        else
            Set_Children(y, y.getLeft(), y.getMiddle(), null);

        y.setSize(y.getSize()-1);
        node l = y;
        while(l != null){
            l = l.getp();
            l.setSize(l.getSize()-1);
        }
        //delete node x?
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

    @Override
    public node Borrow_Or_Merge(node y){
        node z = y.getp();
        if(y == z.getLeft()){
            node x = z.getMiddle();
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
            node x = z.getLeft();
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
        node x = z.getMiddle();
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
}
