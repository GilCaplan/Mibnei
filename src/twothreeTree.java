public abstract class twothreeTree<T extends RunnerID> {
    private node<T> root;
    public twothreeTree(){
        Init();
    }
    public node<T> getRoot(){
        return this.root;
    }

    public void Init(){
        internalNode<T> x = new internalNode<>((T) new Sentinal("inf"));
        node<T> l = new leaf<>((T) new Sentinal("-inf"));
        node<T> m = new leaf<>((T) new Sentinal("inf"));

        l.setp(x);
        m.setp(x);

        x.setLeft(l);
        x.setMiddle(m);
        this.root = x;
        if(this instanceof IDtree)
            printTree();
    }

    public node<T> Search(node<T> x, node<T> k)  {
        if (x == null)
            x = this.root;
        if(x instanceof leaf) {
            if(x.getKey().equals(k.getKey()))
                return x;
            return null;
        }
        if(checkSmaller(k, x.getLeft()) || keyEqual(x.getLeft(), k))
            return Search(x.getLeft(), k);
        if(checkSmaller(k, x.getMiddle()) || keyEqual(x.getMiddle(), k))
            return Search(x.getMiddle(), k);
        return Search(x.getRight(), k);
    }


    public void Update_Key(internalNode<T> x){
        x.setKey(x.getLeft().getKey());
        if(x.getMiddle() != null)
            x.setKey(x.getMiddle().getKey());
        if(x.getRight() != null)
            x.setKey(x.getRight().getKey());
    }

    public void Set_Children(internalNode<T> x, node<T> l, node<T> m, node<T> r){
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        l.setp(x);

        if(m != null)
            m.setp(x);
        if(r != null)
            r.setp(x);
        Update_Key(x);
        if(m != null)
            UpdateSize(x);
    }

    public node<T> Insert_And_Split(internalNode<T> x, node<T> z) {
        node<T> l = x.getLeft();
        node<T> m = x.getMiddle();
        node<T> r = x.getRight();
        if(r == null){
            if(checkSmaller(z, l))
                Set_Children(x, z, l, m);
            else if(checkSmaller(z, m))
                Set_Children(x, l, z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        internalNode<T> y = new internalNode<>(null);//
        if(checkSmaller(z, l)){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if(checkSmaller(z, m)){
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if(checkSmaller(z, r)){
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        }
        else {
            Set_Children(x, l, m, null);
            Set_Children(y, r, z, null);
        }
        return y;
    }

    public void UpdateSize(internalNode<T> x){//fix
        int s = x.getLeft().getSize() + x.getMiddle().getSize();
        if(x.getRight() != null)
            s += x.getRight().getSize();
        x.setSize(s);
    }

    public void Insert(node<T> z) {
        z.setSize(1);
        node<T> y = this.root;
        while(!(y instanceof leaf)) {//y is not a leaf
            if (checkSmaller(z, y.getLeft()))
                y = y.getLeft();
            else if(checkSmaller(z, y.getMiddle()))
                    y = y.getMiddle();
            else y = y.getRight();
        }
        internalNode<T> x = (internalNode<T>) y.getp();
        z = Insert_And_Split(x, z);
        while(x != this.root){
            x = (internalNode<T>) x.getp();
            if(z != null)
                z = Insert_And_Split(x, z);
            else {
                Update_Key(x);
                UpdateSize(x);
            }
        }
        if(z != null){
            internalNode<T> w = new internalNode<>(null);//check
            Set_Children(w, x, z, null);
            this.root = w;
        }
//        printTree();
    }

    public node<T> Borrow_Or_Merge(internalNode<T> y){
        internalNode<T> z = (internalNode<T>) y.getp();
        if(y == z.getLeft()){
            internalNode<T> x = (internalNode<T>) z.getMiddle();
            if(x.getRight() != null){
                Set_Children(y, y.getLeft(), x.getLeft(), null);
                Set_Children(x, x.getMiddle(), x.getRight(), null);
            }
            else{
                Set_Children(x, y.getLeft(), x.getLeft(), x.getMiddle());
                Set_Children(z, x, z.getRight(), null);
            }
            return z;
        }
        if(y == z.getMiddle()){
            internalNode<T> x = (internalNode<T>) z.getLeft();
            if(x.getRight() != null){
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
            }
            else{
                Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
                Set_Children(z, x, z.getRight(), null);
                return z;
            }
        }
        internalNode<T> x = (internalNode<T>) z.getMiddle();
        if(x.getRight() != null){
            Set_Children(y, x.getRight(), y.getLeft(), null);
            Set_Children(x, x.getLeft(), x.getMiddle(), null);
        }
        else{
            Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
            Set_Children(z, z.getLeft(), x, null);
        }
        return z;
    }

    public void Delete(internalNode<T> x){
        internalNode<T> y = (internalNode<T>) this.Search(null, x).getp();
        if(keyEqual(x, y.getLeft()))
            Set_Children(y, y.getMiddle(), y.getRight(), null);
        else if(keyEqual(x, y.getMiddle())){
            Set_Children(y, y.getLeft(), y.getRight(), null);
        }
        else {
            Set_Children(y, y.getLeft(), y.getMiddle(), null);
            //delete node<T> x?
        }
        while(y != null){
            if(y.getMiddle() == null){
                if(y != this.root)
                    y = (internalNode<T>) Borrow_Or_Merge(y);
                else {
                    this.root = y.getLeft();
                    y.getLeft().setp(null);
                    //delete y?
                    return;
                }
            }
            else{
                Update_Key(y);
                y = (internalNode<T>) y.getp();
            }
        }
    }

    public int Rank(node<T> x){
        int rank = 1;
        node<T> y = x.getp();
        while(y != null){
            if(x == y.getMiddle())
                rank = rank + y.getLeft().getSize();
            else if(x == y.getRight())
                rank += y.getLeft().getSize() + y.getMiddle().getSize();
            x = y;
            y = y.getp();
        }
        return rank;
    }

    public node<T> Minimum(){//fix later
        node<T> x = this.root;
        while(!(x.getLeft() instanceof leaf) && x.getLeft() != null)//x is not a leaf
            x = x.getLeft();
        x = x.getp().getMiddle();
        if(!(x.getKey() instanceof Sentinal))
            return x;
        throw new IllegalArgumentException("T is empty");
    }

    public node<T> Successor(node<T> x){//fix later
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
        while(!(y.getLeft() instanceof leaf))//y is not a leaf
            y = y.getLeft();
        //if(y.isSmaller(new Sentinal()))
        //    return y;
        return null;
    }


    public void printTree() {
        System.out.println("new print of tree, tree object type is" +this.getClass().getName());
        System.out.println();
        printNode(root, 0);
        System.out.println();
        System.out.println();
    }

    private void printNode(node<T> n, int depth) {
        if (n == null) {
//            for (int i = 0; i < depth; i++) {
//                System.out.print("  ");
//            }
//            System.out.println("|-- null");
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }

        System.out.println("|-- " + n.getKey());

        if (n instanceof internalNode<T> internal) {
            printNode(internal.getLeft(), depth + 1);
            printNode(internal.getMiddle(), depth + 1);
            printNode(internal.getRight(), depth + 1);
        }
    }
    public boolean checkSmaller(node<T> x, node<T> y){
        //check if x is smaller than y
        if(y.getKey() instanceof Sentinal){
            return y.getKey().toString().equals("s+");
        }
        if(x.getSecondaryKey().getF() != (float)-1){
            if(x.getKey().toString().equals(y.getKey().toString())){
                return x.getKey().isSmaller(y.getKey());
            }
            return x.getSecondaryKey().getF() < y.getSecondaryKey().getF();
        }
        return x.getKey().isSmaller(y.getKey());
    }
    public boolean keyEqual(node<T> x, node<T> y){
        if (x.getKey() instanceof Sentinal || y.getKey() instanceof Sentinal)
            return false;
        return x.getKey().toString().equals(y.getKey().toString()) && x.getSecondaryKey() == y.getSecondaryKey();
    }
}

