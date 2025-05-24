package multithreading.basics2.synch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankTransfer {
    private double balance;
    private int id;
    private String accountName;

    public BankTransfer(double balance, int id, String accountName) {
        this.balance = balance;
        this.id = id;
        this.accountName = accountName;
    }

    final Lock lock = new ReentrantLock();

    public boolean withdraw(double amount) throws InterruptedException {
        if (this.lock.tryLock()) {
            Thread.sleep(200);
            this.balance -= amount;
            this.lock.unlock();
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(double amount) throws InterruptedException {
        if (this.lock.tryLock()) {
            Thread.sleep(200);
            this.balance += amount;
            this.lock.unlock();
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(BankTransfer to, double amount) throws InterruptedException {
        if (withdraw(amount)) {
            System.out.println("withdrawing amount : " + amount + " from " + this.accountName);
            if (to.deposit(amount)) {
                System.out.println("depositing amount : " + amount + " to " + to.accountName);
                return true;
            } else {
                System.out.println("failed to acquire both locks, refunding amount to " + this.accountName);
                while (!deposit(amount)) {
                    continue;
                }
                System.out.println("refunding amount completed to " + this.accountName);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        BankTransfer student = new BankTransfer(10000, 1, "student");
        BankTransfer university = new BankTransfer(1000, 1, "university");

        System.out.println("starting balance of student account: " + student.balance);
        System.out.println("starting balance of unniversity account: " + university.balance);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +" : transfering");
            try {
                while (!student.transfer(university, 1000)) {
                    Thread.sleep(200);
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +" : transfer completed");
        });
         for (int i = 0; i < 9; i++) {
             executorService.submit(t1);
         }
        executorService.shutdown();
         try {
             while (!executorService.awaitTermination(24l, TimeUnit.MINUTES)) {
                 System.out.println("awaiting termination");
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

        System.out.println("balance of student " + student.balance);
        System.out.println("balance of university " + university.balance);

    }

}
