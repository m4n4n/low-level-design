package connectionpool;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PoolHealthChecker{

    private final long idleTimeout;
    private final ConnectionPool connectionPool;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public PoolHealthChecker(long idleTimeout, ConnectionPool connectionPool){
        this.idleTimeout = idleTimeout;
        this.connectionPool = connectionPool;
    }
    public void checkHealth(){

        System.out.println("Running health check");
        List<Connection> connectionsToRemove = connectionPool.getConnections().stream()
                        .filter(conn -> conn.getState().equals(ConnectionState.CLOSED)
                        || (conn.getState().equals(ConnectionState.IDLE)
                                && System.currentTimeMillis() - conn.getLastUsedAt() >= idleTimeout ))
                        .toList();

        for (Connection conn : connectionsToRemove) {
            System.out.println("Removing connection: " + conn.getConnectionId());
            connectionPool.getConnections().remove(conn);

            try {
                connectionPool.addNewConnection(conn);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Health check completed");

    }

    public void startHealthCheck(){
        System.out.println("Starting health check");
        scheduler.scheduleAtFixedRate(this::checkHealth, 1000, 3000, TimeUnit.MILLISECONDS);
    }

    public void stopHealthCheck(){
        System.out.println("Stopping health check");
        scheduler.shutdown();
    }
}
