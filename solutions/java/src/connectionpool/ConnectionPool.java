package connectionpool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConnectionPool {

    private final BlockingQueue<Connection> connections;
    private final String endpoint;
    private final Integer POOL_SIZE;
    private final long REQUEST_TIMEOUT;

    public ConnectionPool(Integer poolSize, String endpoint, long timeout) {
        this.connections = new ArrayBlockingQueue<>(poolSize, true);
        this.POOL_SIZE = poolSize;
        this.endpoint = endpoint;
        this.REQUEST_TIMEOUT = timeout;

        for(int i = 1; i <= poolSize; i++) {
            connections.add(new Connection("Connection-"+i, endpoint,
                    System.currentTimeMillis()));

        }
    }

    public Connection acquireConnection() throws InterruptedException, TimeoutException {

        Connection connection;
        do {
            connection = connections.poll(REQUEST_TIMEOUT, TimeUnit.MILLISECONDS);
            if(connection == null) {
                throw new TimeoutException();
            }

        } while (connection.getState() == ConnectionState.CLOSED);

        System.out.println(connection.getConnectionId()+" is ACTIVE now");
        connection.setState(ConnectionState.ACTIVE);
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        if (connection.getState().equals(ConnectionState.ACTIVE)) {
            connection.setLastUsedAt(System.currentTimeMillis());
            connection.setState(ConnectionState.IDLE);

            boolean released = connections.offer(connection, REQUEST_TIMEOUT, TimeUnit.MILLISECONDS);
            if(!released) {
                connection.setState(ConnectionState.CLOSED);
                addNewConnection(connection);
            } else {
                connection.setState(ConnectionState.IDLE);
                System.out.println(connection.getConnectionId()+" is IDLE now");
            }
        } else {
            addNewConnection(connection);
        }
    }

    public void addNewConnection(Connection closedConnection) throws InterruptedException {
        connections.put(new Connection(closedConnection.getConnectionId(),
                endpoint, System.currentTimeMillis()));
    }

    public Integer getPoolSize() {
        return this.connections.size();
    }

    public BlockingQueue<Connection> getConnections() {
        return this.connections;
    }

}
