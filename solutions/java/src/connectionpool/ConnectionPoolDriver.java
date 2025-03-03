package connectionpool;

import java.util.concurrent.TimeoutException;

public class ConnectionPoolDriver {
    public static void main(String[] args) {
        // Configuration: pool size, endpoint URL, and acquire/release timeout in milliseconds.
        int poolSize = 3;
        String endpoint = "http://example.com";
        long timeout = 5000; // 2 seconds timeout

        // Create the connection pool.
        ConnectionPool pool = new ConnectionPool(poolSize, endpoint, timeout);
        PoolHealthChecker poolHealthChecker = new PoolHealthChecker(1000, pool);

        poolHealthChecker.startHealthCheck();
        Task task1 = new Task(pool);
        Task task2 = new Task(pool);
        Task task3 = new Task(pool);
        Task task4 = new Task(pool);
        Task task5 = new Task(pool);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        Thread thread4 = new Thread(task4);
        Thread thread5 = new Thread(task5);

        try {
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();

            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();

        } catch (InterruptedException ie) {
            System.out.println("Operation interrupted.");
            Thread.currentThread().interrupt();
        }

        poolHealthChecker.stopHealthCheck();
        System.out.println("All tasks completed. Pool size: " + pool.getPoolSize());



    }
}
