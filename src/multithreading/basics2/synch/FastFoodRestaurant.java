package multithreading.basics2.synch;

public class FastFoodRestaurant {

    private String lastClientName;
    private int numberOfBurgerSold;

    public String getLastClientName() {
        return lastClientName;
    }

    public int getNumberOfBurgerSold() {
        return numberOfBurgerSold;
    }

    public static void main(String[] args) throws InterruptedException {
        FastFoodRestaurant fastFoodRestaurant = new FastFoodRestaurant();
        Thread t1 = new Thread(() -> {
            fastFoodRestaurant.buyBurgerWithSyncBlock("client1");
        });
        Thread t2 = new Thread(() -> {
            fastFoodRestaurant.buyBurgerWithSyncBlock("client2");
        });
        Thread t3 = new Thread(() -> {
            fastFoodRestaurant.buyBurgerWithSyncBlock("client3");
        });
        Thread t4 = new Thread(() -> {
            fastFoodRestaurant.buyBurgerWithSyncBlock("client4");
        });
        Thread t5 = new Thread(() -> {
            fastFoodRestaurant.buyBurgerWithSyncBlock("client5");
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println("total  burgers sold : " + fastFoodRestaurant.getNumberOfBurgerSold());
        System.out.println("last client : " + fastFoodRestaurant.getLastClientName());

    }

    public void buyBurgerWithSyncBlock(String clientName) {
        synchronized (this) {
            // lock is at object level, so only 1 thread gets access to this object lock
            longRunningProcess();
            this.lastClientName = clientName;
            numberOfBurgerSold++;
            System.out.println("client name : " + lastClientName + " , burger sold : " + numberOfBurgerSold);
        }
    }

    public synchronized void buyBurgerWithSyncMethod(String clientName) {
        // if we remove the sync keyword, burger count may not match 5
        // since numberOfBurgerSold is accessed by all thread simultaneously it may end in inconsistent state
        longRunningProcess();
        this.lastClientName = clientName;
        numberOfBurgerSold++;
        System.out.println("client name : " + lastClientName + " , burger sold : " + numberOfBurgerSold);
    }

    public void longRunningProcess() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


