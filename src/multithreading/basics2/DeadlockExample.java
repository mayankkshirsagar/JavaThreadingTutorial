package multithreading.basics2;

public class DeadlockExample {

    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {

        // Thread 1 locks Lock1, then sleeps, and tries to lock Lock2.
        // Thread 2 locks Lock2, then sleeps, and tries to lock Lock1.
        // Thread 1 waits for Lock2, held by Thread 2.
        // Thread 2 waits for Lock1, held by Thread 1.

        // How to fix it:
        // Always lock shared resources in the same order.
        // Use tools like tryLock() with a timeout (from ReentrantLock) to avoid indefinite blocking.

        Thread t1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding Lock1...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                System.out.println("Thread 1: Waiting for Lock2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Acquired Lock2!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding Lock2...");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                System.out.println("Thread 2: Waiting for Lock1...");
                synchronized (Lock1) {
                    System.out.println("Thread 2: Acquired Lock1!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
