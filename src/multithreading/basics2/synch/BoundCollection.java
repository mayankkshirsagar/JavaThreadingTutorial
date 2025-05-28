package multithreading.basics2.synch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BoundCollection {

    private Semaphore semaphore;
    private List<Integer> arrayList;

    public BoundCollection(int limit) {
        this.semaphore = new Semaphore(limit);
        this.arrayList = Collections.synchronizedList(new ArrayList<Integer>());
    }


    public void add(int i) throws InterruptedException {
        semaphore.acquire();
        arrayList.add(i);
        System.out.println("added i " + i);
    }

    public void remove(int i) throws InterruptedException {
        arrayList.remove(Integer.valueOf(i));
        System.out.println("removed i " + i);
        semaphore.release();
    }

    public static void main(String[] args) {
        BoundCollection boundCollection = new BoundCollection(10);
        new Thread(() -> {
            for (int i = 0;i < 20; i++) {
                try {
                    boundCollection.add(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 20; i++) {
                try {
                    boundCollection.remove(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
