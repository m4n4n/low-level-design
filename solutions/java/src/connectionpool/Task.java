package connectionpool;

import java.util.concurrent.TimeoutException;

public class Task implements Runnable{

    private final ConnectionPool pool;

    public Task(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " requesting connection ");
            Connection connection = pool.acquireConnection();
            System.out.println(Thread.currentThread().getName() + " borrowed " + connection.getConnectionId());

            Thread.sleep(4000);

            pool.releaseConnection(connection);
            System.out.println(Thread.currentThread().getName() + " released " + connection.getConnectionId());

        } catch (InterruptedException | TimeoutException e) {
            Thread.currentThread().interrupt();
        }
    }
}