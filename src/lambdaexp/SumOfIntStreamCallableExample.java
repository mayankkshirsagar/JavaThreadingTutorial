package lambdaexp;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SumOfIntStreamCallableExample {

    public static int[] intstream = IntStream.rangeClosed(0, 5000).toArray();
    public static int sum = IntStream.rangeClosed(0, 5000).sum();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /**
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0;i< intstream.length/2; i++) {
                    Thread.sleep(1);
                    sum += intstream[i];
                }
                return sum;
            }
        };
         */

        Callable<Integer> callable1 = () -> {
            int sum = 0;
            for (int i = 0;i< intstream.length/2; i++) {
                Thread.sleep(1);
                sum += intstream[i];
            }
            return sum;
        };

        Callable<Integer> callable2 = () -> {
            int sum = 0;
            for (int i = intstream.length/2;i< intstream.length; i++) {
                Thread.sleep(1);
                sum += intstream[i];
            }
            return sum;
        };

        // service which manages running threaded tasks, setting threads to 1 makes it sequential
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        List<Callable<Integer>> callables = Arrays.asList(callable1, callable2); // Arrays.asList(callable, callable2);
        List<Future<Integer>> futureSumList = executorService.invokeAll(callables);

//         or
//         Future<Integer> future1 = executorService.submit(callable1);
//         Future<Integer> future2 = executor.submit(callable2);
//         int result1 = future1.get();
//         int result2 = future2.get();
//         total sum = res1 + res2;

//         or
//         Future<Integer> future1 = executorService.submit(() -> {
//                    int sum = 0;
//                    for (int i = 0;i< intstream.length/2; i++) {
//                        sum += intstream[i];
//                    }
//                    return sum;
//                });


//         or
//         public static int computeSum1() {
//                int sum = 0;
//                    for (int i = 0;i< intstream.length/2; i++) {
//                        sum += intstream[i];
//                    }
//                    return sum;
//            }
//         Future<Integer> future1 = executorService.submit(() -> computeSum1());


        int computedSum = 0;
        for (Future<Integer> future : futureSumList) {
            computedSum += future.get();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("intStream sum : " + sum);
        System.out.println("computed sum : " + computedSum);
        System.out.println("total time : " + (endTime - startTime));

        // shutdown executor service,
        // If you don’t call shutdown() (or shutdownNow()),
        // the ExecutorService keeps running in the background,
        // even after your main method finishes.
        // ExecutorService creates non-daemon threads
        // Non-daemon threads keep the Java process alive.
        // The JVM does not exit until all non-daemon threads finish.
        // try by commenting the shutdown, program remains still active even after execution completion
        executorService.shutdown();
    }
}
