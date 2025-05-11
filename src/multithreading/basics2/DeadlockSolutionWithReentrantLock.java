package multithreading.basics2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

// from chatgpt XD
public class DeadlockSolutionWithReentrantLock {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println("Thread 1: Acquired Lock1");
                    try {
                        Thread.sleep(100); // Simulate work
                        if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("Thread 1: Acquired Lock2");
                                // Critical section
                            } finally {
                                lock2.unlock();
                                System.out.println("Thread 1: Released Lock2");
                            }
                        } else {
                            System.out.println("Thread 1: Could not acquire Lock2, avoiding deadlock");
                        }
                    } finally {
                        lock1.unlock();
                        System.out.println("Thread 1: Released Lock1");
                    }
                } else {
                    System.out.println("Thread 1: Could not acquire Lock1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println("Thread 2: Acquired Lock2");
                    try {
                        Thread.sleep(100); // Simulate work
                        if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("Thread 2: Acquired Lock1");
                                // Critical section
                            } finally {
                                lock1.unlock();
                                System.out.println("Thread 2: Released Lock1");
                            }
                        } else {
                            System.out.println("Thread 2: Could not acquire Lock1, avoiding deadlock");
                        }
                    } finally {
                        lock2.unlock();
                        System.out.println("Thread 2: Released Lock2");
                    }
                } else {
                    System.out.println("Thread 2: Could not acquire Lock2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}
