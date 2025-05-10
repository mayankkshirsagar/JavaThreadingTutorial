package lambdaexp;

public class RunnableExample {

    public static void main(String[] args) {
        // old way
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // sum of first 10 num
                int sum = 0;
                for (int i = 1; i<=10;i++)
                    sum += i;
                System.out.println("old way sum is : " + sum);
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // lambda way
        Runnable runnable2 = () -> {
            // sum of first 10 num
            int sum = 0;
            for (int i = 1; i<=10;i++)
                sum += i;
            System.out.println("new way sum is : " + sum);
        };
        Thread t2 = new Thread(runnable2);
        t2.start();

        new Thread(() -> {
            int sum = 0;
            for (int i = 1; i<=10;i++)
                sum += i;
            System.out.println("new way sum is : " + sum);
        }).start();
        // why does this work ? how does constructor know its a runnable if we did not mention it
        /**
         * The Thread class has this constructor:
         *  public Thread(Runnable target)
         *  This constructor expects an object that implements the Runnable interface.
         *  In Java, a lambda expression like this,   () -> { .. }
         *  ...is treated as an instance of a functional interface — and Runnable is a functional interface
         * A functional interface has exactly one abstract method, so Java can infer that the lambda matches Runnable.run().
         *
         * Suppose hypothetically we had 2 constructors both accepting functional interfaces
         * public Thread(Runnable r) { ... }
         * public Thread(Callable c) { ... }
         * and we had new Thread(() -> { return 42; });
         * So you'd get a compiler error: "reference to Thread is ambiguous."
         * You'd have to explicitly cast the lambda:
         * new Thread((Runnable) () -> System.out.println("Runnable"));
         */
    }
}
