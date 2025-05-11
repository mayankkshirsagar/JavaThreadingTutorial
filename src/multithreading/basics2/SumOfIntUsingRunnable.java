package multithreading.basics2;

import java.util.stream.IntStream;

public class SumOfIntUsingRunnable {

    public static int[] intStream = IntStream.rangeClosed(0, 5000).toArray();
    public static int sumFromIntStream = IntStream.rangeClosed(0, 5000).sum();

    // this is not right as it can create race condition
    // public static AtomicInteger totalFromThreadSum = new AtomicInteger(0);
    // use atomic int can solve this
    // or make add method synchronized
    // or read directly from objects
    public static int totalFromThreadSum = 0;


    public static void main(String[] args) throws Exception {
        RunnableWorker1 runnableWorker1 = new RunnableWorker1(intStream);
        RunnableWorker2 runnableWorker2 = new RunnableWorker2(intStream);

        Thread t1 = new Thread(runnableWorker1);
        Thread t2 = new Thread(runnableWorker2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        System.out.println("actual total sum : " + sumFromIntStream);
        System.out.println("sum from threads : " + totalFromThreadSum);
        System.out.println("sum from threads : " + (runnableWorker1.sum + runnableWorker2.sum)); // read directly from objects

    }

    // this might work right now but is not thread safe
    public static void add(int sum) {
        totalFromThreadSum += sum;
        // totalFromThreadSum.addAndGet(sum);  using atomic int
    }

    // or make add method synchronized if not using atomic int
    // public synchronized static void add(int sum) {
    // totalFromThreadSum += sum;
    // }

}

class RunnableWorker1 implements Runnable {

    public int[] arr;
    public int sum = 0;

    public RunnableWorker1(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length/2; i++)
            this.sum += arr[i];
        SumOfIntUsingRunnable.add(sum);
    }
}

class RunnableWorker2 implements Runnable {

    public int[] arr;
    public int sum = 0;

    public RunnableWorker2(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = arr.length/2; i < arr.length; i++)
            this.sum += arr[i];
        SumOfIntUsingRunnable.add(sum);
    }
}
