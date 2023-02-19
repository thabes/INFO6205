package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);
        System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        int[] arrSizePool = new int[4];
        int[] threadSizePool = {2,4,8,16,32};
        int initPower = 20;
        for(int i = 0; i < arrSizePool.length; i++){
            arrSizePool[i] = (int)Math.pow(2,initPower);
           initPower++;
            System.out.println(arrSizePool[i]);
        }
        System.out.println(arrSizePool[3]);

        //int[] arrSize = {arrSizePool[2]};
        int[] arrSize = arrSizePool;

        int[] threadSize = {threadSizePool[3]};
        //int[] threadSize = threadSizePool;

        Random random = new Random();
        int[] array;

        ArrayList<Long> timeList = new ArrayList<>();

        ForkJoinPool pool;
        for (int i = 0; i < arrSize.length; i++) {
            //ParSort.cutoff = 10000 * (j + 1);
            // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);

            int size = arrSize[i];
            array = new int[size];

            int[] cutoff = {size/(1024+1), size/(512+1), size/(256+1), size/(128+1),
                    size/(64+1), size/(32+1), size/(16+1), size/(8+1), size/(4+1),
                    size/(2+1), size+1};

            for(int j = 0; j < cutoff.length; j++){
                ParSort.cutoff = cutoff[j];
                for(int threads: threadSize){
                    pool = new ForkJoinPool(threads);
                    long time;
                    long startTime = System.currentTimeMillis();
                    for(int k = 0; k < 10; k++){
                        for(int m = 0; m < size; m++){
                            array[m] = random.nextInt(10000000);
                        }
                        ParSort.sort(array, 0, array.length, pool);
                    }
                    long endTime = System.currentTimeMillis();
                    time = (endTime - startTime);
                    timeList.add(time);
                    System.out.println("size of Array: " + size +  " cutoff：" + (ParSort.cutoff) + " Total threads: " + threads + "\t\tAvg time: " + time/10 + "ms");
                }

            }


//            for (int t = 0; t < 10; t++) {
//                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
//                ParSort.sort(array, 0, array.length);
//            }
//            long endTime = System.currentTimeMillis();
//            time = (endTime - startTime);
//            timeList.add(time);
//
//
//            System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms");

        }
//        try {
//            FileOutputStream fis = new FileOutputStream("./src/result.csv");
//            OutputStreamWriter isr = new OutputStreamWriter(fis);
//            BufferedWriter bw = new BufferedWriter(isr);
//            int j = 0;
//            for (long i : timeList) {
//                String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
//                j++;
//                bw.write(content);
//                bw.flush();
//            }
//            bw.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
