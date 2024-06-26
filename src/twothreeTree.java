public class twothreeTree<T extends RunnerID> {
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
    }

    public node<T> Search(node<T> x, node<T> k)  {
        if (x == null)
            x = this.root;
        if(x instanceof leaf) {
            if(x.getKey().toString().equals(k.getKey().toString()))
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
        x.setKey(x.getLeft().getKey(), x.getLeft().getSecondaryKey());
        if(x.getMiddle() != null) {
            x.setKey(x.getMiddle().getKey(), x.getMiddle().getSecondaryKey());
        }
        if(x.getRight() != null)
            x.setKey(x.getRight().getKey(), x.getRight().getSecondaryKey());
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
            }
            UpdateSize(x);
        }
        if(z != null){
            internalNode<T> w = new internalNode<>(null);//check
            Set_Children(w, x, z, null);
            this.root = w;
            UpdateSize(root);
        }
    }

    private void UpdateSize(node<T> x) {
        if(x == null || x instanceof leaf)
            return;
        int r = x.getLeft() != null? x.getLeft().getSize():0;
        r += x.getMiddle() != null? x.getMiddle().getSize():0;
        r += x.getRight() != null? x.getRight().getSize():0;
        x.setSize(r);
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
                y = new internalNode<>(null);
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
                y = new internalNode<>(null);
                Set_Children(z, x, z.getRight(), null);
            }
            return z;
        }
        internalNode<T> x = (internalNode<T>) z.getMiddle();
        if(x.getRight() != null){
            Set_Children(y, x.getRight(), y.getLeft(), null);
            Set_Children(x, x.getLeft(), x.getMiddle(), null);
        }
        else{
            Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
            y = new internalNode<>(null);
            Set_Children(z, z.getLeft(), x, null);
        }
        return z;
    }

    public void Delete(internalNode<T> x) {
        node<T> z = this.Search(null, x);
        if(z == null)
            throw new IllegalArgumentException();
        internalNode<T> y = (internalNode<T>) z.getp();
        if(keyEqual(x, y.getLeft()))
            Set_Children(y, y.getMiddle(), y.getRight(), null);
        else if(keyEqual(x, y.getMiddle())){
            Set_Children(y, y.getLeft(), y.getRight(), null);
        }
        else {
            Set_Children(y, y.getLeft(), y.getMiddle(), null);
        }
        UpdateSize(y);
        while(y != null){
            if(y.getMiddle() == null){
                if(y != this.root)
                    y = (internalNode<T>) Borrow_Or_Merge(y);
                else {
                    this.root = y.getLeft();
                    y.getLeft().setp(null);
                    return;
                }
            }
            else{
                Update_Key(y);
                y = (internalNode<T>) y.getp();
            }
            UpdateSize(y);
        }
    }

    public node<T> Minimum(){
        node<T> x = this.root;
        while(!(x.getLeft() instanceof leaf))//x is not a leaf
            x = x.getLeft();

        x = x.getMiddle();
        if(!(x.getKey() instanceof Sentinal))
            return x;
        throw new IllegalArgumentException();
    }

    public int Rank(node<T> x){
        int rank = 1;
        internalNode<T> y = (internalNode<T>) x.getp();
        while(y != null){
            if(x == y.getMiddle())
                rank = rank + y.getLeft().getSize();
            else if(x == y.getRight())
                rank += y.getLeft().getSize() + y.getMiddle().getSize();
            x = y;
            y = (internalNode<T>) y.getp();
        }
        return rank;
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
        return null;
    }

    public boolean checkSmaller(node<T> x, node<T> y){
        //check if x is smaller than y
        if(y.getKey() instanceof Sentinal){
            return y.getKey().toString().equals("s+");
        }
        if(x.getKey() instanceof Sentinal){
            return !x.getKey().toString().equals("s+");
        }
        if(x.getSecondaryKey() != null && x.getSecondaryKey().getF() != (float)-1){
            if(x.getSecondaryKey().getF() == y.getSecondaryKey().getF())
                return x.getKey().isSmaller(y.getKey());
            return x.getSecondaryKey().getF() < y.getSecondaryKey().getF();
        }
        return x.getKey().isSmaller(y.getKey());
    }
    public boolean keyEqual(node<T> x, node<T> y){
        if (x.getKey() instanceof Sentinal || y.getKey() instanceof Sentinal)
            return false;
        if(x.getSecondaryKey() != null && y.getSecondaryKey() != null && x.getSecondaryKey().getF() != (float)-1){//only comparing the Secondary keys
            return x.getSecondaryKey().getF() == y.getSecondaryKey().getF() && x.getKey().toString().equals(y.getKey().toString());
        }
        return x.getKey().toString().equals(y.getKey().toString());
    }

    public void printTree(node<T> root){
        printTree(root, "", true);
    }
    public void printTree(node<T> root, String prefix, boolean isTail) {
        if (root instanceof internalNode<T>) {
            internalNode<T> innerNode = (internalNode<T>) root;
            System.out.println(prefix + (isTail ? "└──" : "├── "));
            System.out.println(prefix + innerNode);
            printTree(innerNode.getLeft(), prefix + (isTail ? "    " : "│   "), false);
            printTree(innerNode.getMiddle(), prefix + (isTail ? "    " : "│   "), false);
            printTree(innerNode.getRight(), prefix + (isTail ? "    " : "│   "), true);
        } else if (root instanceof leaf<T>) {
            System.out.println(prefix + (isTail ? "└── : " : "├── : ") + root);
        }
    }
}


