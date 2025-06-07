package multithreading.basics2.synch;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            TimeUnit.MILLISECONDS.sleep(1000);
            return Thread.currentThread().getName() + " finished " + LocalDateTime.now();
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<Callable<String>> callableList = Arrays.asList(callable, callable);
        // Future<String> result = executorService.submit(callable);
        List<Future<String>> results = executorService.invokeAll(callableList);
        for (Future<String> f : results) {
            System.out.println(f.get());
        }
        executorService.shutdown();
    }
}
