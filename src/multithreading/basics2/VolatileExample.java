package multithreading.basics2;

public class VolatileExample {

    public static volatile boolean flag = false;
    // if we remove the volatile, thread 2 will not know or update when the flag is set to true from first thread
    // hence it will keep the old false value and keep on running without terminating

    // use volatile when :
    // You need visibility between threads, not atomicity
    // One thread updates a variable, and others must see the updated value immediately
    // No compound operations (x++, x +=, etc.) are involved.
    // You're using a flag or status variable shared between threads (like in this example)
    // You only have simple assignments, E.g., writing and reading single values (int, boolean, etc.), not modifying them.

    // do not use volatile when :
    // you perform read-modify-write operations Like x++, x = x + y, etc. These are not atomic, and volatile won't help.
    // You need mutual exclusion (locking), If threads must not interfere with each other during a critical section, use synchronized, ReentrantLock, or Atomic* classes.

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i< 2000; i++)
                System.out.println("value of i is " + i);
            flag = true;
            System.out.println("flag set as  : " + flag);
        }).start();

        new Thread(() -> {
            int i = 0;
            while (!flag) {
                i++;
            }
            System.out.println("value of i in second thread is : " + i);
        }).start();

    }
}
