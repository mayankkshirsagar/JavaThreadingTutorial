package multithreading.basics2.synch;

public class CourseNotifier {
    public static void main(String[] args) throws InterruptedException {
        Course course = new Course("java tut");
        new Thread(() -> {
            synchronized (course) {
                System.out.println(Thread.currentThread().getName() + " is waiting for course to complete : " + course.getTitle());
                try {
                    course.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("student course completed by " + Thread.currentThread().getName());
            }
        }, "Student1").start();

        new Thread(() -> {
            synchronized (course) {
                System.out.println(Thread.currentThread().getName() + " is waiting for course to complete : " + course.getTitle());
                try {
                    course.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("student course completed by " + Thread.currentThread().getName());
            }
        }, "Student2").start();

        Thread.sleep(200);
        // also adding sleep here so that both students reach the wait() before instructor does notifyAll
        // if notifyAll is done before a thread reaches wait, it will keep on waiting for the notification

        new Thread(() -> {
            synchronized (course) {
                System.out.println(Thread.currentThread().getName() + " is starting course : " + course.getTitle());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("course finished by " + Thread.currentThread().getName());
                course.notifyAll();
                // course.notify(); if we just add notify , only 1 student will be notified.
                // to use this add
                // course.wait();
                // course.notify();
                // in both the students so first notified thread will notify the second waiting thread
            }
        }, "instructor").start();
    }
}

class Course {
    private String title;
    private boolean completed;

    public Course(String title) {
        this.title = title;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
