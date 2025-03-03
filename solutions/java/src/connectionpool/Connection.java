package connectionpool;

public class Connection {

    private final String connectionId;
    private final String url;
    private ConnectionState state;
    private final long createdAt;
    private long lastUsedAt;

    public Connection(String connectionId, String url, long createdAt) {
        this.connectionId = connectionId;
        this.url = url;
        this.createdAt = createdAt;
        this.state = ConnectionState.IDLE;
        this.lastUsedAt = createdAt;
    }

    public String getConnectionId() {
        return connectionId;
    }
    public String getUrl() {
        return url;
    }
    public ConnectionState getState() {
        return state;
    }
    public void setState(ConnectionState state) {
        this.state = state;
    }
    public long getCreatedAt(){
        return createdAt;
    }
    public long getLastUsedAt() {
        return lastUsedAt;
    }
    public void setLastUsedAt(long lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

    public void executeTask() throws InterruptedException {
        Thread.sleep(2000);
    }
}
