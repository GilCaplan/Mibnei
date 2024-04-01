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

//        RunnerIDInt id1 = new RunnerIDInt(3);
//        RunnerIDInt id2 = new RunnerIDInt(5);
//        race.addRunner(id1);
//        race.addRunner(id2);
        int[] runners = new int[]{1,4,5,7,14,19,22,25,29};
        for (int j : runners) {
            race.addRunner(new RunnerIDInt(j));
        }
//        race.removeRunner(id2);
        RunnerIDInt id;
        float[] run_times = new float[]{(float)0.5, (float)1.5, (float)4.5, 5, 6, (float)8.8, 9, 2};
        for (int j : runners) {
            id = new RunnerIDInt(j);
            for(float time: run_times)
                race.addRunToRunner(id, time);
            System.out.println("The min running time of" + id.toString() + "is " + race.getMinRun(id));

        }
//        race.addRunToRunner(id1, (float)118.0);
//        System.out.println("The min running time of" + id2.toString() + "is " + race.getMinRun(id2));
//        System.out.println("The avg running time of" + id1.toString() + "is " + race.getAvgRun(id1));
//        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
    }
}
