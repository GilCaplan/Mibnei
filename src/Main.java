class RunnerIDInt extends RunnerID{
    private int id;
    public RunnerIDInt(int id){
        super();
        this.id = id;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.id < ((RunnerIDInt)other).id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }


}


public class Main {
    public static void main(String[] args) {
        // The ids which we will check will not necessarily be RunnerIDInt
        // This is just for the example

        Race race = new Race();

        RunnerIDInt id1 = new RunnerIDInt(1);
        RunnerIDInt id2 = new RunnerIDInt(3);
        RunnerIDInt id3 = new RunnerIDInt(4);
        RunnerIDInt id4 = new RunnerIDInt(7);
        race.addRunner(id1);
        race.addRunner(id2);
        race.addRunner(id3);
        race.addRunner(id4);

        printTree(race.getRoot(),"",true);
//        race.removeRunner(id2);
//        RunnerIDInt id;
//        float[] run_times = new float[]{(float)0.5, (float)1.5, (float)4.5, 5, 6, (float)8.8, 9, 2};
//        for (int j : runners) {
//            id = new RunnerIDInt(j);
//            for(float time: run_times)
//                race.addRunToRunner(id, time);
//            System.out.println("The min running time of" + id.toString() + "is " + race.getMinRun(id));
//
//        }
//        race.addRunToRunner(id1, (float)118.0);
//        System.out.println("The min running time of" + id2.toString() + "is " + race.getMinRun(id2));
//        System.out.println("The avg running time of" + id1.toString() + "is " + race.getAvgRun(id1));
//        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
    }

    public static void printTree(node<RunnerID> root, String prefix, boolean isTail) {
        if (root instanceof internalNode<RunnerID>) {
//            System.out.println(prefix + (isTail ? "└── InnerNode with key: " : "├── InnerNode with key: ") + root.key+", size: "+root.getSize());
            System.out.println(prefix + (isTail ? "└──" : "├── "));
            internalNode<RunnerID> innerNode = (internalNode<RunnerID>) root;
            printTree(innerNode.getLeft(), prefix + (isTail ? "    " : "│   "), false);
            printTree(innerNode.getMiddle(), prefix + (isTail ? "    " : "│   "), false);
            printTree(innerNode.getRight(), prefix + (isTail ? "    " : "│   "), true);
        } else if (root instanceof leaf<RunnerID>) {
//            System.out.println(prefix + (isTail ? "└── Leaf with key: " : "├── Leaf with key: ") + root.key+", size: "+root.getSize());
            System.out.println(prefix + (isTail ? "└── : " : "├── : ") + root.getKey());
        }
    }
}
