package multithreading.basics2;

import java.util.stream.IntStream;

public class SumOfNumbersWithRunnableAndLambda {

    public static int[] intStream = IntStream.rangeClosed(0, 5000).toArray();
    public static int sumFromIntStream = IntStream.rangeClosed(0, 5000).sum();
    public static int sumFromThread = 0;

    public static void main(String[] args) throws Exception {

//        Runnable runnable1 = () -> {
//            int sum = 0;
//            for (int i = 0; i < intStream.length/2; i++)
//                add(intStream[i]);
//        };
//
//        Runnable runnable2 = () -> {
//            int sum = 0;
//            for (int i = intStream.length/2; i < intStream.length; i++)
//                add(intStream[i]);
//        };

//        Thread t1 = new Thread(runnable1);
//        Thread t2 = new Thread(runnable2);

        // we are adding in the sum 1 by 1 , to show the difference if we do not add synchronized to the add method
        // try removing the synchronized from the method add and see if the sum matches,
        // since both threads call add method in parallel 2500 times each, without maintaining the thread safety
        // there is chance that one thread overwrites its value over another thread or reads stale value
        // causing sum inconsistency
        // solution to make the method synchronized or use atomic int for sumFromThread
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < intStream.length/2; i++)
                add(intStream[i]);
        });
        Thread t2 = new Thread(() -> {
            for (int i = intStream.length/2; i < intStream.length; i++)
                add(intStream[i]);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("sum from int stream : " + sumFromIntStream);
        System.out.println("sum from threads : " + sumFromThread);
    }

    public synchronized static void add(int sum) {
        sumFromThread += sum;
    }
}
