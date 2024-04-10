import java.util.*;
public class TestRace
{
    public static void main(String[] args) {
        System.out.println("starting to test:");
        test1();
        test2();
        test3();
        test4();
        test5();
        testAddRunner();
        testRemoveRunner();
        testAddRunToRunner();
        testRemoveRunFromRunner();
        testGetMinRun();
        testGetAvgRun();
        testGetFastestRunnerAvg();
        testGetFastestRunnerMin();
        testGetRankAvg();
        testGetRankMin();
        System.out.println("finished testing");

    }
    public static void testGetRankMin() {
        Race race = new Race();
        race.init();

        RunnerID id1 = new RunnerIDInt(1);
        race.addRunner(id1);
        RunnerID id2 = new RunnerIDInt(2);
        race.addRunner(id2);
        RunnerID id3 = new RunnerIDInt(3);
        race.addRunner(id3);
        RunnerID id4 = new RunnerIDInt(4);
        race.addRunner(id4);
        test(1, race.getRankMin(id1), "");

        race.addRunToRunner(id2, 15);
        test(2, race.getRankAvg(id1), "");
        test(1, race.getRankAvg(id2), "");
        race.addRunToRunner(id1, 16);
        test(2, race.getRankAvg(id1), "");
        test(1, race.getRankAvg(id2), "");
        race.addRunToRunner(id1, 1);
        race.addRunToRunner(id3, 20);
        race.addRunToRunner(id4, 7);
        test(2, race.getRankAvg(id1), "");
        test(3, race.getRankAvg(id2), "");
        test(4, race.getRankAvg(id3), "");
        test(1, race.getRankAvg(id4), "");


    }
    public static void testGetRankAvg() {
        Race race = new Race();
        race.init();

        RunnerID id1 = new RunnerIDInt(1);
        race.addRunner(id1);
        RunnerID id2 = new RunnerIDInt(2);
        race.addRunner(id2);
        RunnerID id3 = new RunnerIDInt(3);
        race.addRunner(id3);
        RunnerID id4 = new RunnerIDInt(4);
        race.addRunner(id4);

        System.out.println();
        race.addRunToRunner(id1, 10);
        race.addRunToRunner(id1, 5);
        race.addRunToRunner(id1, 15);
        test(1, race.getRankAvg(id1), "");
        test(2, race.getRankAvg(id2), "");
        test(3, race.getRankAvg(id3), "");
        test(4, race.getRankAvg(id4), "");

        race.addRunToRunner(id2, 1);
        race.addRunToRunner(id2, 2);
        race.addRunToRunner(id2, 3);

        test(2, race.getRankAvg(id1), "");
        test(1, race.getRankAvg(id2), "");
        test(3, race.getRankAvg(id3), "");
        test(4, race.getRankAvg(id4), "");

        race.addRunToRunner(id3, 9);
        race.addRunToRunner(id4, 3);

        test(4, race.getRankAvg(id1), "");
        test(1, race.getRankAvg(id2), "");
        test(3, race.getRankAvg(id3), "");
        test(2, race.getRankAvg(id4), "");

    }
    public static void testGetFastestRunnerMin() {

        System.out.println("testing GetFastestRunnerMin");
        Race race = new Race();
        race.init();
        RunnerID id1 = new RunnerIDInt(1);
        RunnerID id2 = new RunnerIDInt(2);
        race.addRunner(id2);
        race.addRunner(id1);
        test(id1, race.getFastestRunnerMin(), "checking fastest runner after adding id1, id2");

        RunnerID id0 = new RunnerIDInt(0);
        race.addRunner(id0);
        race.addRunToRunner(id1, 17);
        race.addRunToRunner(id1, 15);
        race.addRunToRunner(id1, 16);//id1 avg: 15
        test(id1, race.getFastestRunnerMin(),"");
        race.addRunToRunner(id2, 15);
        race.addRunToRunner(id2, 10);
        test(id2, race.getFastestRunnerMin(),"");
        race.addRunToRunner(id0, 15);
        test(id2, race.getFastestRunnerMin(),"");
        race.removeRunFromRunner(id2, 10);//id2 avg 15
        test(id0, race.getFastestRunnerMin(),"");
        test(new RunnerIDInt(0), race.getFastestRunnerMin(),"");
        race.removeRunner(id0);

        System.out.println("Finished test");
        System.out.println();
    }

    public static void testGetFastestRunnerAvg() {
        Race race = new Race();
        race.init();
        try {
            System.out.println("value which should be null is: " + race.getFastestRunnerAvg());
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught, theres no fasted runner");
        }
        RunnerID id2 = new RunnerIDInt(2);
        race.addRunner(id2);
        RunnerID id1 = new RunnerIDInt(1);
        race.addRunner(id1);

        race.addRunToRunner(id1, 17);
        race.addRunToRunner(id1, 15);
        race.addRunToRunner(id1, 16);
        test(id1 ,race.getFastestRunnerAvg(), "");
        race.addRunToRunner(id2, 16);
        test(id1 ,race.getFastestRunnerAvg(), "");
        race.addRunToRunner(id2, 10);
        test(id2 ,race.getFastestRunnerAvg(), "");
    }
    public static void testGetAvgRun() {
        System.out.println("Testing get Avg Run");
        Race race = new Race();
        race.init();

        RunnerID id = new RunnerIDInt(1);
        race.addRunner(id);
        test(Float.MAX_VALUE, race.getAvgRun(id), "Checking Average run time");
        race.addRunToRunner(id, 17);
        race.addRunToRunner(id, 15);
        race.addRunToRunner(id, 16);
        test((float)16, race.getAvgRun(id), "Checking Average run time");

        race.addRunToRunner(id, 2);
        race.addRunToRunner(id, 25);
        test((float)15, race.getAvgRun(id), "Checking Average run time");
        System.out.println();
    }

    public static void testGetMinRun() {
        Race race = new Race();
        race.init();
        RunnerID id = new RunnerIDInt(1);
        race.addRunner(id);
        for(float i = 1000; i > 0; i -= 2)
            race.addRunToRunner(id, i);
        test((float)2, race.getMinRun(id), "Checking minimal run which should be 2");
    }

    public static void testRemoveRunFromRunner() {
        Race race = new Race();
        race.init();
        System.out.println("Starting Test - Removing Runs from Runner");
        RunnerID id = new RunnerIDInt(1);
        race.addRunner(id);
        race.addRunToRunner(id, 16);
        race.addRunToRunner(id, 18);
        race.removeRunFromRunner(id, 16);
        race.removeRunFromRunner(id, 18);

        race.addRunToRunner(id, 15);
        try {
            race.addRunToRunner(id, 15);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught, can't remove run 15 again");
        }
        race.removeRunFromRunner(id, 15);
        try {
            race.removeRunFromRunner(id, 15);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught, can't remove run 15 again");
        }

        RunnerID id2 = new RunnerIDInt(2);
        race.addRunToRunner(id, 15);
        try {
            race.addRunToRunner(id, 15);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught, can't add the same run twice");
        }
        race.addRunToRunner(id, 10);
        race.addRunner(id2);
        race.addRunToRunner(id2, 11);
        RunnerID id222 = new RunnerIDInt(222);
        race.addRunner(id222);
        for(float i=1; i < 100; i+=0.5)
            race.addRunToRunner(id222, i);

        for(float i=1; i < 100; i+=0.5)
            race.removeRunFromRunner(id222, i);

        System.out.println("Finished Test");
        System.out.println();
    }

    public static void testAddRunToRunner() {
        System.out.println("Test the addition of runs to runner");
        Race race = new Race();
        race.init();

        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10,11};

        int cnt = 0;
        for (int i : new int[] {6, 3, 9, 88, 212, 92, 7, 33, 22, 131, 54}) {
            RunnerID id = new RunnerIDInt(i);
            race.addRunner(id);
            race.addRunToRunner(id, i);
            HashSet<Float> runner_list = new HashSet<>();
            for(int j=0; j < array[cnt] - 1; j++){
                float run = getRandomFloatInRange(i, i+30, runner_list);
                runner_list.add(run);
                race.addRunToRunner(id, run);
            }
            cnt++;
        }
        for (int i : new int[] {6, 3, 9, 88, 212, 92, 7, 33, 22, 131, 54}) {
            test(i, race.getMinRun(new RunnerIDInt(i)), "checking Min run for runner "+ i);
        }
        test(3, race.getRankMin(new RunnerIDInt(7)), "checking rank of runner 9");
        test(1, race.getRankMin(new RunnerIDInt(3)), "checking rank of runner 1");
        test(4, race.getRankMin(new RunnerIDInt(9)), "checking rank of runner 9");
        test(new RunnerIDInt(3), race.getFastestRunnerMin(), "checking fastest runner");

    }

    public static void testAddRunner() {
        System.out.println("Starting Test, trying to add runners");
        Race race = new Race();
        race.init();
        // add runner
        for (int i : new int[] {2, 5, 6, 3, 9, 1, 88, 212, 92, 7, 33}) {
            RunnerID id = new RunnerIDInt(i);
            race.addRunner(id);
        }

        test(new RunnerIDInt(1), race.getFastestRunnerAvg(), "Checking fastest runner by Average time");
        test(new RunnerIDInt(1), race.getFastestRunnerMin(), "Checking fastest runner by Minimum time");
        RunnerID id = new RunnerIDInt(0);
        race.addRunner(id);
        System.out.println("Finished adding runners");
    }

    public static void testRemoveRunner() {
        System.out.println("Starting test, Testing while trying to remove runners");
        Race race = new Race();
        race.init();
        RunnerID id1 = new RunnerIDInt(1);
        race.addRunner(id1);
        race.removeRunner(id1);
        race.addRunner(id1);
        RunnerID id2 = new RunnerIDInt(2);
        race.addRunner(id2);
        RunnerID id3 = new RunnerIDInt(3);
        race.addRunner(id3);
        race.removeRunner(id1);
        race.removeRunner(id3);

        RunnerID id5 = new RunnerIDInt(5);
        race.addRunner(id5);
        race.removeRunner(id5);
        race.addRunner(id5);
        System.out.println("Finished test");
    }

    public static void test5() {
        System.out.println("Starting test 5");
        Race race = new Race();
        int[] runners = new int[]{1,4,5,3,7,14,19,22,25,29};
        for (int j : runners)
            race.addRunner(new RunnerIDInt(j));
        test(Float.MAX_VALUE, race.getMinRun(new RunnerIDInt(5)), "Checking Min time");
        test(Float.MAX_VALUE, race.getMinRun(new RunnerIDInt(22)), "Checking Avg time");
        test(new RunnerIDInt(1), race.getFastestRunnerAvg(), "Checking fastest runner by Average time");
        test(new RunnerIDInt(1), race.getFastestRunnerMin(), "Checking fastest runner by Minimum time");
        test(1, race.getRankAvg(new RunnerIDInt(1)), "Checking rank of 1 in average tree");
        test(7, race.getRankAvg(new RunnerIDInt(19)), "Checking rank of 19 in average tree");
        test(3, race.getRankMin(new RunnerIDInt(4)), "Checking rank of 4 in min tree");
        System.out.println("Finished test 5");
        System.out.println();
    }

    public static void test4() {
        System.out.println("Starting test 4");
        Race race = new Race();
        race.init();

        RunnerID id1 = new RunnerIDInt(1);
        RunnerID id2 = new RunnerIDInt(2);
        RunnerID id3 = new RunnerIDInt(3);
        race.addRunner(id1);
        race.addRunner(id2);
        race.addRunner(id3);

        race.addRunToRunner(id1, 1);
        race.addRunToRunner(id1, 9);


        race.addRunToRunner(id2, 4);
        try {
            race.addRunToRunner(id2, 4);
            race.addRunToRunner(id2, 4);
            race.addRunToRunner(id2, 4);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught error, can't re-add the same runners more than once");
        }


        race.removeRunFromRunner(id1, 1);
        try {
            race.addRunToRunner(id3, -9);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught, can't add negative time's to runners");
        }

        race.removeRunFromRunner(id2, 4);
        try {
            race.removeRunFromRunner(id2, 4);
            race.removeRunFromRunner(id2, 4);
            race.removeRunFromRunner(id2, 4);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught' can't remove non existent runs");
        }
        System.out.println("finished test 4");
        System.out.println();
    }

    public static void test3() {
        // checks that you can not add the same runner twice
        // needs to raise an error
        System.out.println("Starting test 3");
        Race race = new Race();
        race.init();
        RunnerID id1 = new RunnerIDInt(3);
        race.addRunner(id1);
        RunnerID id2 = new RunnerIDInt(3);
        try {
            race.addRunner(id2);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught error, can't add the same runner twice");
        }
        System.out.println("finished test 3");
        System.out.println();
    }

    public static void test2() {
        // checks that you can not add the same runner twice
        // needs to raise an error
        System.out.println("Starting test 2");
        Race race = new Race();
        race.init();
        RunnerID id = new RunnerIDInt(3);

        race.addRunner(id);
        try {
            race.addRunner(id);
        }
        catch (IllegalArgumentException ae){
            System.out.println("caught error, can't add the same runner twice");
        }
        System.out.println("Finished test 2");
        System.out.println();
    }

    public static void test1() {
        System.out.println("Starting test 1");
        Race race = new Race();
        race.init();
        RunnerID id = new RunnerIDInt(3);
        race.addRunner(id);
        test(Float.MAX_VALUE, race.getMinRun(id), "Race Min Run is incorrect");
        test(Float.MAX_VALUE, race.getAvgRun(id), "Race Avg Run is incorrect");
        race.addRunToRunner(id, 118);
        test((float)118, race.getMinRun(id), "Checking Min time");
        test((float)118, race.getAvgRun(id), "Checking Avg time");
        test(id, race.getFastestRunnerAvg(), "Checking fastest runner by Average time");
        test(id, race.getFastestRunnerMin(), "Checking fastest runner by Minimum time");
        test(1, race.getRankAvg(id), "Checking rank of "+ id + "in average tree");
        test(1, race.getRankMin(id), "Checking rank of "+ id + "in min tree");


        race.removeRunFromRunner(id, 118);

        race.removeRunner(id);

        try {
            System.out.println("The average time is: " + race.getAvgRun(id) + "\n");
        } catch (IllegalArgumentException iae) {
            System.out.println("caught average time error, since there are no runs");
        }
        try {
            System.out.println("The minimum time is: " + race.getMinRun(id) + "\n");
        } catch (IllegalArgumentException iae) {
            System.out.println("caught minimum time error, since there are no runs");
        }
        System.out.println("finished test 1");
        System.out.println();
    }

    public static void test(float str1, float str2, String message){
        boolean flag =  str1 == str2;
        if(!flag)
            System.out.println(message + ". Your answer: " + str2 + ", Actual Answer:" + str1);
    }
    public static void test(int x, int y, String message){
        boolean flag = x == y;
        if(!flag)
            System.out.println(message + ". Your answer: " + x+ ", Actual Answer:" + y);
    }
    public static void test(RunnerID r1, RunnerID r2, String message){
        boolean flag =  r1.toString().equals(r2.toString());
        if(!flag)
            System.out.println(message + ". Your answer: " + r2+ ", Actual Answer:" + r1);
    }
    public static float getRandomFloatInRange(float x, float y, HashSet<Float> runs) {
        float rand = (float) (Math.random() * (y - x) + x);
        while(runs.contains(rand))
            rand = (float) (Math.random() * (y - x) + x);
        return rand;
    }
}
