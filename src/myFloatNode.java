public class myFloatNode extends node<myFloat>{
    public myFloatNode(myFloat key) {
        super(key);
    }

    public myFloatNode(myFloat key, myFloat skey) {
        super(key, skey.getF());
    }
    myFloat s = new myFloat(3);
}
