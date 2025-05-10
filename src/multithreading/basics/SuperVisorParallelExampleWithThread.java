package multithreading.basics;

import java.util.Comparator;

/**
 * parallel solution using threads
 */
public class SuperVisorParallelExampleWithThread {
    public static void main(String[] args) {
        ParallelWorker1 p1 = new ParallelWorker1();
        ParallelWorker2 p2 = new ParallelWorker2();

        long startTime = System.currentTimeMillis();

        p1.start(); // start the thread
        p2.start(); // start the thread

        // if we directly call the run() which we override, it will not be a parallel call
        // instead it will be again a sequential call, so calling start() is required to make the parallel call
        // p1.run();
        // p2.run();

        // total time consumed by making the threads and running parallel, wait for both threads to finish
        try {
            p1.join(); // main program thread wait for p1 to finish
            p2.join(); // main program thread wait for p2 to finish
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("total time in execution : " + (endTime - startTime));

    }
}

// extend thread class and override the run method and call the start() on the worker
class ParallelWorker1 extends Thread {
    public void executeWork() throws InterruptedException { // need to either throw exception or handle in try catch
        for(int i = 0 ;i<10; i++) {
            Thread.sleep(100);
            System.out.println("worker 1 performing task " + i);
        }
    }

    @Override
    public void run() {
        try {
            this.executeWork();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// extend thread class and override the run method
class ParallelWorker2 extends Thread {
    public void executeWork() {
        for(int i = 0 ;i<10; i++) {
            try { // need to either throw exception or handle in try catch
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("worker 2 performing task " + i);
        }
    }

    @Override
    public void run() {
        this.executeWork();
    }
}
