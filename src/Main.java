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
    public static void main(String[] args) throws Exception {
        // The ids which we will check will not necessarily be RunnerIDInt
        // This is just for the example

        Race race = new Race();

//        RunnerIDInt id1 = new RunnerIDInt(3);
//        RunnerIDInt id2 = new RunnerIDInt(5);
//        race.addRunner(id1);
//        race.addRunner(id2);
        int[] runners = new int[]{1,3,5,7,14,19,22,25,29};
        for (int j : runners) {
            race.addRunner(new RunnerIDInt(j));
        }
//        race.removeRunner(new RunnerIDInt(5));
//        race.removeRunner(new RunnerIDInt(19));
        printTree(race.getRoot(),"",true);

        RunnerIDInt id;
        float[] run_times = new float[]{(float)1, (float)2, (float)3, 1, 3, (float)2, 2, 2};
        for (int j : runners) {
            id = new RunnerIDInt(j);
            for(float time: run_times)
                race.addRunToRunner(id, time);
            System.out.println();
            System.out.println("The min running time of " + id + " is " + race.getMinRun(id));
            System.out.println("The avg running time of " + id + " is " + race.getAvgRun(id));
            System.out.println();

        }
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
