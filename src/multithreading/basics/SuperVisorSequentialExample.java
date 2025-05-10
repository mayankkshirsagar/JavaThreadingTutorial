package multithreading.basics;

public class SuperVisorSequentialExample {

    public static void main(String[] args) {
        // sequential single thread, first worker1 does work, then worker2, in a sequential manner
        Worker1 w1 = new Worker1();
        Worker2 w2 = new Worker2();

        long startTime = System.currentTimeMillis();

        // run the workers
        try {
            w1.executeWork();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        w2.executeWork();

        long endTime = System.currentTimeMillis();

        System.out.println("total time in execution : " + (endTime - startTime));

    }
}

class Worker1 {
    public void executeWork() throws InterruptedException { // need to either throw exception or handle in try catch
        for(int i = 0 ;i<10; i++) {
            Thread.sleep(100);
            System.out.println("worker 1 performing task " + i);
        }
    }
}

class Worker2 {
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
}
