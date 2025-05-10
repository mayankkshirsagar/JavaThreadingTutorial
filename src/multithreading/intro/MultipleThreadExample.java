package multithreading.intro;

public class MultipleThreadExample {
    public static void main(String[] args) {
        // create new threads
        Thread t1 = new Thread("thread1");
        Thread t2 = new Thread("thread2");
        // start the threads
        t1.start();
        t2.start();

        System.out.println("multi thread example");
        System.out.println(t1.getName());
        System.out.println(t2.getName());
    }
}
