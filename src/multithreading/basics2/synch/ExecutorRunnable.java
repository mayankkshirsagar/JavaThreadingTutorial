package multithreading.basics2.synch;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ExecutorRunnable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " finished on time : " + LocalDateTime.now());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println("Executing task with execute()");
        executorService.submit(runnable);


        System.out.println("Executing task with submit()");
        Runnable runnable1 = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " finished on time : " + LocalDateTime.now());
        };
        Future<String> ft = executorService.submit(runnable1, "COMPLETED");
        System.out.println("Method completed with response : " + ft.get());
        executorService.shutdown();
    }
}
