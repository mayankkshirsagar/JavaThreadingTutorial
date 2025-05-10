Tutorial source : https://www.udemy.com/course/java-multi-threading-programming/

---

### ✅ Core Multithreading Topics

#### 🧵 1. **Java Thread Basics**

* `Thread`, `Runnable`, `Callable`, `Future`
* Thread life cycle: `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`
* `Thread.sleep()`, `join()`, `yield()`, `interrupt()`

---

#### 🔐 2. **Synchronization & Locks**

* `synchronized` keyword (methods, blocks)
* Intrinsic locks (monitor locks)
* Reentrant locks: `ReentrantLock`, `ReentrantReadWriteLock`
* Lock fairness
* Deadlock, livelock, and starvation — detection and avoidance
* Thread-safe design patterns

---

#### ⚙️ 3. **Concurrency Utilities (java.util.concurrent)**

* **Executors**: `ExecutorService`, `ScheduledExecutorService`
* **Thread pools**: `FixedThreadPool`, `CachedThreadPool`, `WorkStealingPool`
* `Callable`, `Future`, `invokeAll()`, `invokeAny()`
* `CompletionService`, `FutureTask`

---

#### 🧰 4. **Low-Level Concurrency Tools**

* `CountDownLatch`, `CyclicBarrier`, `Semaphore`
* `Exchanger`, `Phaser`
* `BlockingQueue` (e.g. `LinkedBlockingQueue`, `ArrayBlockingQueue`)
* Thread-safe collections: `ConcurrentHashMap`, `CopyOnWriteArrayList`, etc.

---

#### ⚡ 5. **CompletableFuture & Asynchronous Programming (Java 8+)**

* `CompletableFuture`: `supplyAsync`, `thenApply`, `thenCombine`, `exceptionally`, `allOf`, `anyOf`
* Chaining and combining tasks
* Error handling
* Using custom executors
* Non-blocking pipelines

---

#### 🧠 6. **Memory Model & Volatile**

* Java Memory Model (JMM)
* `volatile` keyword and visibility
* Happens-before relationship
* Atomic operations and `Atomic*` classes (`AtomicInteger`, `AtomicReference`, etc.)
* `Unsafe` class (low-level)

---

#### 📐 7. **Multithreading Design Patterns**

* Producer–Consumer
* Reader–Writer
* Thread-per-task vs. Thread pool
* Reactor pattern (e.g., Netty, Spring WebFlux)
* Fork/Join framework and parallel streams
* Asynchronous event-driven systems

---

#### 🐛 8. **Troubleshooting & Best Practices**

* Detecting deadlocks (`jstack`, `jvisualvm`, profilers)
* Avoiding race conditions
* Thread dumps and analysis
* When to use `ThreadLocal`
* Avoiding blocking I/O in multi-threaded environments
* Choosing the right data structures for concurrency

---

### 🔥 Bonus (Leadership/Architecture)

* Trade-offs between parallelism and thread safety
* Designing scalable multithreaded systems
* Performance tuning (e.g., thread pool sizing, GC impact)
* Integration with frameworks (e.g., Spring `@Async`, Project Reactor)

---

### 📘 Recommended Resources

* *Java Concurrency in Practice* by Brian Goetz (must-read)
* *Effective Java* (especially concurrency chapters)
* Java official docs: [`java.util.concurrent`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html)

---

